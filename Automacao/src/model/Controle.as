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
[Bindable] private var totalControle:int = 0;
[Bindable] private var xmlListaControle:XMLList = null;
[Bindable] private var idsControleMarcados : ArrayList;

[Bindable] private var selectedIndex:int;

private function init():void {
	Alert.yesLabel = "Sim";
	Alert.noLabel = "NÃ£o";
	Alert.okLabel = "Ok"; 
	Alert.cancelLabel = "Cancelar";
	nsPagina.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPressed);
	pesquisarListaControle();
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
	txtTipoCadastro.text = "";
	txtCanalCadastro.text = "";
	txtNoCadastro.text = "";
	
	btnRemover.includeInLayout = false;
	btnRemover.visible = false;
}

private function exibirTelaPesquisa(): void {
	vs.selectedIndex = 0;
}

private function limparCamposPesquisa():void{
	
}

private function visibilidadeBtnRemoverVarios(event:MouseEvent):void{
	if(dgControle.selectedIndices.length > 0){
		btnRemoverVarios.visible = true;
	}
	else{
		btnRemoverVarios.visible = false;
	}
}

private function pesquisarListaControle(): void {
	exibirPopUpMensagem("Aguarde ...");
	hsListarControle.cancel();	
	var params:Object = {};	
	params["pagina"] = pagina;
	
	hsListarControle.send(params);
}

private function receberListaControle(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	xmlListaControle = new XMLList(event.result.lista.item);
	
	totalControle = event.result.quantidade;
	totalPaginas = Math.ceil(totalControle / MAX_ITENS_LISTADOS_LIMIT);
	nsPagina.value = pagina + 1;
	nsPagina.maximum = totalPaginas;
	lblPagina.text = "de " + totalPaginas;
	
	dgControle.dataProvider = xmlListaControle;
	
	if(totalPaginas <= 1) {
		if(xmlListaControle.length() == 0){
			lblEncontrados.text = "Nenhum controle encontrado";
		} else if(xmlListaControle.length() == 1){
			lblEncontrados.text = "1 controle encontrado";
		} else {
			lblEncontrados.text = xmlListaControle.length()+" controle encontrados";
		}
		gPaginacao.setVisible(false);
		gPaginacao.height = 0;
		paginacao.setVisible(false);
	} else {
		lblEncontrados.text = totalControle + " controles encontrados " + xmlListaControle.length() + " nesta pÃ¡gina";
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
	pesquisarListaControle();
}

private function paginaAnterior():void{
	pagina --;
	pesquisarListaControle();
}

private function proximaPagina():void{
	pagina ++;
	pesquisarListaControle();
}

private function ultimaPagina():void{
	pagina = totalPaginas - 1;
	pesquisarListaControle();
}

private function recuperarProximoControle():void {
	recuperarControleDaPosicao(++selectedIndex);
}

private function recuperarControleAnterior():void {
	recuperarControleDaPosicao(--selectedIndex);
}

private function recuperarControleDaPosicao(posicao:int):void {
	exibirPopUpMensagem("Aguarde ...");
	hsRecuperarControle.cancel();
	var params:Object = {};		
	params["id"] = dgControle.dataProvider[posicao].id;
	hsRecuperarControle.send(params);
	exibirTelaCadastro();
	btnRemover.includeInLayout = true;
	btnRemover.visible = true;
}

private function recuperarControle(event:ListEvent):void {
	if(event.columnIndex != 0) {
		selectedIndex = event.rowIndex;
		recuperarControleDaPosicao(selectedIndex);
	}
}

private function receberControle(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	var controle:XML = new XML(event.result.toString());
	
	txtId.text = controle.id;
	txtLocalCadastro.text = controle.local;
	txtTipoCadastro.text = controle.tipo;
	txtCanalCadastro.text = controle.canal;
	txtNoCadastro.text = controle.no;
	
	
	if(xmlListaControle.length() > 1){
		gNavegacao.visible = true;
		lblRegistro.text = (selectedIndex + 1) + " de " + xmlListaControle.length();
	} else {
		gNavegacao.visible = false;
	}
	btnControleAnterior.enabled = !(selectedIndex == 0);
	btnProximoControle.enabled = !(selectedIndex + 1 == xmlListaControle.length());
}



private function gravarControle():void {
	if (dadosValidos()) {
		hsGravarControle.cancel();
		var params:Object = {};
		
		
		params["id"] = txtId.text;	
		params["local"] = txtLocalCadastro.text;
		params["tipo"] = txtTipoCadastro.text;
		params["canal"] = txtCanalCadastro.text;
		params["no"] = txtNoCadastro.text;
		
		
		hsGravarControle.send(params);
		exibirPopUpMensagem("Gravando este controle ...");
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

private function removerControle(): void {
	Alert.show("Deseja realmente remover este controle?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder);
}

private function removerVarios():void{
	if(dgControle.selectedItems.length > 1){
		Alert.show("Deseja realmente remover estes "+dgControle.selectedItems.length+" controles?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
	}
	else{		
		if(dgControle.selectedItems.length == 1){
			Alert.show("Deseja realmente remover este controle?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
		}
		else{
			Alert.show("Selecione algum controle", "AtenÃ§Ã£o", Alert.OK, this);
		}
	}
}

private function acaoAoResponder(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverControle.cancel();
		var params : Object = {};
		params["listaIds"] = txtId.text;	
		hsRemoverControle.send(params);
		exibirPopUpMensagem("Revomendo este bairro ...");
	}
}


private function acaoAoResponder2(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverControle.cancel();
		var params:Object = {};
		var enviar:String = new String;
		
		for(var count: int = 0; count < dgControle.selectedItems.length; count++){
			if(count != dgControle.selectedItems.length - 1){
				enviar = enviar + dgControle.selectedItems[count].id + ",";
			}
			else{
				enviar = enviar + dgControle.selectedItems[count].id;
			}
		}
		
		params["listaIds"] = enviar;
		
		hsRemoverControle.send(params);
		exibirPopUpMensagem("Revomendo este(s) bairro(s) ...");
	}
}

private function receberResultado(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	Alert.show(event.result.toString(), "AtenÃ§Ã£o", Alert.OK, this);
	exibirTelaPesquisa();
	pesquisarListaControle();	
}


private function selecionarPagina(): void {
	pagina = nsPagina.value - 1;
	pesquisarListaControle();
}

