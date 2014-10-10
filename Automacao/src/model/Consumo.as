import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.ui.Keyboard;
import flash.utils.setInterval;

import mx.collections.ArrayList;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.managers.PopUpManager;
import mx.printing.FlexPrintJob;
import mx.rpc.events.ResultEvent;



include "Generico.as";
include "Constantes.as";

[Bindable] private var pagina:int = 0;
[Bindable] private var totalPaginas:int = 0;
[Bindable] private var totalConsumo:int = 0;
[Bindable] private var xmlListaConsumo:XMLList = null;
[Bindable] private var idsConsumoMarcados : ArrayList;

[Bindable] private var selectedIndex:int;

private function init():void {
	Alert.yesLabel = "Sim";
	Alert.noLabel = "NÃ£o";
	Alert.okLabel = "Ok"; 
	Alert.cancelLabel = "Cancelar";
	nsPagina.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPressed);
	pesquisarListaConsumo();
}

private function onKeyPressed(event:KeyboardEvent):void {
	if (event.keyCode == Keyboard.ENTER) {
		selecionarPagina();
	}
}

private function exibirTelaCadastro(): void {
	vs.selectedIndex = 1;
	txtId.text = "0";
	txtLocalCadastro.text = "";
	txtNoCadastro.text = "";
	txtCanalCadastro.text = "";
	txtConsumoDiarioCadastro.text = "";
	dfDataCadastro.selectedDate = null;
	
	btnRemover.includeInLayout = false;
	btnRemover.visible = false;
}

private function exibirTelaPesquisa(): void {
	vs.selectedIndex = 0;
}

private function limparCamposPesquisa():void{
	
}

private function visibilidadeBtnRemoverVarios(event:MouseEvent):void{
	if(dgConsumo.selectedIndices.length > 0){
		btnRemoverVarios.visible = true;
	}
	else{
		btnRemoverVarios.visible = false;
	}
}

private function pesquisarListaConsumo(): void {
	exibirPopUpMensagem("Aguarde ...");
	hsListarConsumo.cancel();	
	var params:Object = {};	
	params["pagina"] = pagina;
	
	hsListarConsumo.send(params);
}

private function receberListaConsumo(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	xmlListaConsumo = new XMLList(event.result.lista.item);
	
	totalConsumo = event.result.quantidade;
	totalPaginas = Math.ceil(totalConsumo / MAX_ITENS_LISTADOS_LIMIT);
	nsPagina.value = pagina + 1;
	nsPagina.maximum = totalPaginas;
	lblPagina.text = "de " + totalPaginas;
	
	dgConsumo.dataProvider = xmlListaConsumo;
	
	if(totalPaginas <= 1) {
		if(xmlListaConsumo.length() == 0){
			lblEncontrados.text = "Nenhum consumo encontrado";
		} else if(xmlListaConsumo.length() == 1){
			lblEncontrados.text = "1 consumo encontrado";
		} else {
			lblEncontrados.text = xmlListaConsumo.length()+" consumo encontrados";
		}
		gPaginacao.setVisible(false);
		gPaginacao.height = 0;
		paginacao.setVisible(false);
	} else {
		lblEncontrados.text = totalConsumo + " consumos encontrados " + xmlListaConsumo.length() + " nesta pÃ¡gina";
		gPaginacao.setVisible(true);
		gPaginacao.height = 25;
		paginacao.setVisible(true);
	}
	
	if(pagina == 0){
		btnAnterior.enabled = false;
		btnPrimeira.enabled = false;
	}
	else{
		btnAnterior.enabled = true;
		btnPrimeira.enabled = true;
	}
	
	if(totalPaginas > 1){
		btnProxima.enabled = true;
		btnUltima.enabled = true;
	}else{
		btnProxima.enabled = false;
		btnUltima.enabled = false;
	}
	if(pagina + 1 >= totalPaginas){
		btnProxima.enabled = false;
		btnUltima.enabled = false;
	}
	
	btnRemoverVarios.visible = false;
}

// paginacao
private function primeiraPagina():void{
	pagina = 0;
	pesquisarListaConsumo();
}

private function paginaAnterior():void{
	pagina --;
	pesquisarListaConsumo();
}

private function proximaPagina():void{
	pagina ++;
	pesquisarListaConsumo();
}

private function ultimaPagina():void{
	pagina = totalPaginas - 1;
	pesquisarListaConsumo();
}

private function recuperarProximoConsumo():void {
	recuperarConsumoDaPosicao(++selectedIndex);
}

private function recuperarConsumoAnterior():void {
	recuperarConsumoDaPosicao(--selectedIndex);
}

private function recuperarConsumoDaPosicao(posicao:int):void {
	exibirPopUpMensagem("Aguarde ...");
	hsRecuperarConsumo.cancel();
	var params:Object = {};		
	params["id"] = dgConsumo.dataProvider[posicao].id;
	hsRecuperarConsumo.send(params);
	exibirTelaCadastro();
	btnRemover.includeInLayout = true;
	btnRemover.visible = true;
}

private function recuperarConsumo(event:ListEvent):void {
	if(event.columnIndex != 0) {
		selectedIndex = event.rowIndex;
		recuperarConsumoDaPosicao(selectedIndex);
	}
}

private function receberConsumo(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	var consumo:XML = new XML(event.result.toString());
	
	txtId.text = consumo.id;
	txtLocalCadastro.text = consumo.local;
	txtNoCadastro.text = consumo.no;
	txtCanalCadastro.text = consumo.canal;
	txtConsumoDiarioCadastro.text = consumo.consumoDiario;
	if(consumo.toString().indexOf("data") != -1) {
		dfDataCadastro.selectedDate = new Date(Date.parse((consumo.data.toString()).replace(/-/g, "/")));
	}
	
	
	if(xmlListaConsumo.length() > 1){
		gNavegacao.visible = true;
		lblRegistro.text = (selectedIndex + 1) + " de " + xmlListaConsumo.length();
	} else {
		gNavegacao.visible = false;
	}
	btnConsumoAnterior.enabled = !(selectedIndex == 0);
	btnProximoConsumo.enabled = !(selectedIndex + 1 == xmlListaConsumo.length());
}



private function gravarConsumo():void {
	if (dadosValidos()) {
		hsGravarConsumo.cancel();
		var params:Object = {};
		
		
		params["id"] = txtId.text;	
		params["local"] = txtLocalCadastro.text;
		params["no"] = txtNoCadastro.text;
		params["canal"] = txtCanalCadastro.text;
		params["consumoDiario"] = txtConsumoDiarioCadastro.text;
		var data:Date = dfDataCadastro.selectedDate;
		if(data != null) {
			params["data"] = data.getFullYear() + "-" + (formatar2Digitos(data.getMonth() + 1)) + "-" + formatar2Digitos(data.getDate());
		}
		
		
		hsGravarConsumo.send(params);
		exibirPopUpMensagem("Gravando este consumo ...");
	}
}

private function dadosValidos(): Boolean {
	var mensagens: String = "";
	
	
	
	if (mensagens != "") {
		fecharPopUpMensagem();
		Alert.show(mensagens, "AtenÃ§Ã£o", Alert.OK, this);
		return false;
	}
	return true;
}

private function removerConsumo(): void {
	Alert.show("Deseja realmente remover este consumo?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder);
}

private function removerVarios():void{
	if(dgConsumo.selectedItems.length > 1){
		Alert.show("Deseja realmente remover estes "+dgConsumo.selectedItems.length+" consumos?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
	}
	else{		
		if(dgConsumo.selectedItems.length == 1){
			Alert.show("Deseja realmente remover este consumo?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
		}
		else{
			Alert.show("Selecione algum consumo", "AtenÃ§Ã£o", Alert.OK, this);
		}
	}
}

private function acaoAoResponder(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverConsumo.cancel();
		var params : Object = {};
		params["listaIds"] = txtId.text;	
		hsRemoverConsumo.send(params);
		exibirPopUpMensagem("Revomendo este bairro ...");
	}
}


private function acaoAoResponder2(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverConsumo.cancel();
		var params:Object = {};
		var enviar:String = new String;
		
		for(var count: int = 0; count < dgConsumo.selectedItems.length; count++){
			if(count != dgConsumo.selectedItems.length - 1){
				enviar = enviar + dgConsumo.selectedItems[count].id + ",";
			}
			else{
				enviar = enviar + dgConsumo.selectedItems[count].id;
			}
		}
		
		params["listaIds"] = enviar;
		
		hsRemoverConsumo.send(params);
		exibirPopUpMensagem("Revomendo este(s) bairro(s) ...");
	}
}

private function receberResultado(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	Alert.show(event.result.toString(), "AtenÃ§Ã£o", Alert.OK, this);
	exibirTelaPesquisa();
	pesquisarListaConsumo();	
}


private function selecionarPagina(): void {
	pagina = nsPagina.value - 1;
	pesquisarListaConsumo();
}

