import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import flash.ui.Keyboard;
import flash.utils.setInterval;

import mx.collections.ArrayList;
import mx.controls.Alert;
import mx.events.CloseEvent;
import mx.events.ListEvent;
import mx.managers.PopUpManager;
import mx.rpc.events.ResultEvent;

import view.complementos.PopUpEscolhaConsumo;
import view.complementos.PopUpEscolhaControle;




include "Generico.as";
include "Constantes.as";

[Bindable] private var pagina:int = 0;
[Bindable] private var totalPaginas:int = 0;
[Bindable] private var totalCentral:int = 0;
[Bindable] private var xmlListaCentral:XMLList = null;
[Bindable] private var idsCentralMarcados : ArrayList;
[Bidable] private var popUpEscolhaConsumo:PopUpEscolhaConsumo;
[Bidable] private var popUpEscolhaControle:PopUpEscolhaControle
[Bindable] private var selectedIndex:int;

private function init():void {
	Alert.yesLabel = "Sim";
	Alert.noLabel = "NÃ£o";
	Alert.okLabel = "Ok"; 
	Alert.cancelLabel = "Cancelar";
	nsPagina.addEventListener(KeyboardEvent.KEY_DOWN, onKeyPressed);
	pesquisarListaCentral();
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
txtCanalCadastro.text = "";
txtIPCadastro.text = "";
txtNoCadastro.text = "";
lblIdConsumoCadastro.text = "0";
txtConsumoCadastro.text = "";
lblIdControleCadastro.text = "0";
txtControleCadastro.text = "";

	btnRemover.includeInLayout = false;
	btnRemover.visible = false;
}

private function exibirTelaPesquisa(): void {
	vs.selectedIndex = 0;
}

private function limparCamposPesquisa():void{
	
}

private function visibilidadeBtnRemoverVarios(event:MouseEvent):void{
	if(dgCentral.selectedIndices.length > 0){
		btnRemoverVarios.visible = true;
	}
	else{
		btnRemoverVarios.visible = false;
	}
}

private function pesquisarListaCentral(): void {
	exibirPopUpMensagem("Aguarde ...");
	hsListarCentral.cancel();	
	var params:Object = {};	
	params["pagina"] = pagina;

	hsListarCentral.send(params);
}

private function receberListaCentral(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	xmlListaCentral = new XMLList(event.result.lista.item);
	
	totalCentral = event.result.quantidade;
	totalPaginas = Math.ceil(totalCentral / MAX_ITENS_LISTADOS_LIMIT);
	nsPagina.value = pagina + 1;
	nsPagina.maximum = totalPaginas;
	lblPagina.text = "de " + totalPaginas;
	
	dgCentral.dataProvider = xmlListaCentral;
	
	if(totalPaginas <= 1) {
		if(xmlListaCentral.length() == 0){
			lblEncontrados.text = "Nenhum central encontrado";
		} else if(xmlListaCentral.length() == 1){
			lblEncontrados.text = "1 central encontrado";
		} else {
			lblEncontrados.text = xmlListaCentral.length()+" central encontrados";
		}
		gPaginacao.setVisible(false);
		gPaginacao.height = 0;
		paginacao.setVisible(false);
	} else {
		lblEncontrados.text = totalCentral + " centrals encontrados " + xmlListaCentral.length() + " nesta pÃ¡gina";
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
	pesquisarListaCentral();
}

private function paginaAnterior():void{
	pagina --;
	pesquisarListaCentral();
}

private function proximaPagina():void{
	pagina ++;
	pesquisarListaCentral();
}

private function ultimaPagina():void{
	pagina = totalPaginas - 1;
	pesquisarListaCentral();
}

private function recuperarProximoCentral():void {
	recuperarCentralDaPosicao(++selectedIndex);
}

private function recuperarCentralAnterior():void {
	recuperarCentralDaPosicao(--selectedIndex);
}

private function recuperarCentralDaPosicao(posicao:int):void {
	exibirPopUpMensagem("Aguarde ...");
	hsRecuperarCentral.cancel();
	var params:Object = {};		
	params["id"] = dgCentral.dataProvider[posicao].id;
	hsRecuperarCentral.send(params);
	exibirTelaCadastro();
	btnRemover.includeInLayout = true;
	btnRemover.visible = true;
}

private function recuperarCentral(event:ListEvent):void {
	if(event.columnIndex != 0) {
		selectedIndex = event.rowIndex;
		recuperarCentralDaPosicao(selectedIndex);
	}
}

private function receberCentral(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	var central:XML = new XML(event.result.toString());
	
	txtId.text = central.id;
	txtLocalCadastro.text = central.local;
txtCanalCadastro.text = central.canal;
txtIPCadastro.text = central.IP;
txtNoCadastro.text = central.no;
lblIdConsumoCadastro.text = central.consumo.id;
txtConsumoCadastro.text = central.consumo.local;
lblIdControleCadastro.text = central.controle.id;
txtControleCadastro.text = central.controle.local;

	
	if(xmlListaCentral.length() > 1){
		gNavegacao.visible = true;
		lblRegistro.text = (selectedIndex + 1) + " de " + xmlListaCentral.length();
	} else {
		gNavegacao.visible = false;
	}
	btnCentralAnterior.enabled = !(selectedIndex == 0);
	btnProximoCentral.enabled = !(selectedIndex + 1 == xmlListaCentral.length());
}



private function gravarCentral():void {
	if (dadosValidos()) {
		hsGravarCentral.cancel();
		var params:Object = {};
		
		
		params["id"] = txtId.text;	
		params["local"] = txtLocalCadastro.text;
params["canal"] = txtCanalCadastro.text;
params["IP"] = txtIPCadastro.text;
params["no"] = txtNoCadastro.text;
params["consumo.id"] = lblIdConsumoCadastro.text;
params["controle.id"] = lblIdControleCadastro.text;

		
		hsGravarCentral.send(params);
		exibirPopUpMensagem("Gravando este central ...");
	}
}

private function dadosValidos(): Boolean {
	var mensagens: String = "";
	
	if(lblIdConsumoCadastro.text == "" || lblIdConsumoCadastro.text == "0") {
mensagens += "Favor informar a consumo.\n";
}
if(lblIdControleCadastro.text == "" || lblIdControleCadastro.text == "0") {
mensagens += "Favor informar a controle.\n";
}

	
	if (mensagens != "") {
		fecharPopUpMensagem();
		Alert.show(mensagens, "AtenÃ§Ã£o", Alert.OK, this);
		return false;
	}
	return true;
}

private function removerCentral(): void {
	Alert.show("Deseja realmente remover este central?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder);
}

private function removerVarios():void{
	if(dgCentral.selectedItems.length > 1){
		Alert.show("Deseja realmente remover estes "+dgCentral.selectedItems.length+" centrals?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
	}
	else{		
		if(dgCentral.selectedItems.length == 1){
			Alert.show("Deseja realmente remover este central?", "AtenÃ§Ã£o", Alert.YES | Alert.NO, this, acaoAoResponder2);
		}
		else{
			Alert.show("Selecione algum central", "AtenÃ§Ã£o", Alert.OK, this);
		}
	}
}

private function acaoAoResponder(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverCentral.cancel();
		var params : Object = {};
		params["listaIds"] = txtId.text;	
		hsRemoverCentral.send(params);
		exibirPopUpMensagem("Revomendo este bairro ...");
	}
}


private function acaoAoResponder2(event:CloseEvent):void {
	if (event.detail == Alert.YES) { 
		hsRemoverCentral.cancel();
		var params:Object = {};
		var enviar:String = new String;
		
		for(var count: int = 0; count < dgCentral.selectedItems.length; count++){
			if(count != dgCentral.selectedItems.length - 1){
				enviar = enviar + dgCentral.selectedItems[count].id + ",";
			}
			else{
				enviar = enviar + dgCentral.selectedItems[count].id;
			}
		}
		
		params["listaIds"] = enviar;
		
		hsRemoverCentral.send(params);
		exibirPopUpMensagem("Revomendo este(s) bairro(s) ...");
	}
}

private function receberResultado(event:ResultEvent): void {
	//	isClienteAutenticado(event);
	fecharPopUpMensagem();
	Alert.show(event.result.toString(), "AtenÃ§Ã£o", Alert.OK, this);
	exibirTelaPesquisa();
	pesquisarListaCentral();	
}


private function selecionarPagina(): void {
	pagina = nsPagina.value - 1;
	pesquisarListaCentral();
}

private function limparConsumo():void {
txtConsumoCadastro.text = "";
lblIdConsumoCadastro.text = "0";
}

private function exibirPopUpSelecionarConsumo():void {
popUpEscolhaConsumo = PopUpEscolhaConsumo(PopUpManager.createPopUp(this, PopUpEscolhaConsumo, true));
popUpEscolhaConsumo["btnFechar"].addEventListener(MouseEvent.CLICK, fecharPopUpEscolhaConsumo);
popUpEscolhaConsumo["btnFecharComESC"].addEventListener(MouseEvent.CLICK, fecharPopUpEscolhaConsumoComESC);
setInterval(animarProgressBar, 2500);
}

private function fecharPopUpEscolhaConsumo(event:MouseEvent): void {
if (popUpEscolhaConsumo != null) {
if (popUpEscolhaConsumo.dataGrid.selectedItem != null) {
txtConsumoCadastro.text = popUpEscolhaConsumo.dataGrid.selectedItem.local;
lblIdConsumoCadastro.text = popUpEscolhaConsumo.dataGrid.selectedItem.id;
}
PopUpManager.removePopUp(popUpEscolhaConsumo);
popUpEscolhaConsumo = null;
}
}

private function fecharPopUpEscolhaConsumoComESC(event:MouseEvent): void {
if (popUpEscolhaConsumo != null) {
PopUpManager.removePopUp(popUpEscolhaConsumo);
popUpEscolhaConsumo = null;
}
}

private function limparControle():void {
txtControleCadastro.text = "";
lblIdControleCadastro.text = "0";
}

private function exibirPopUpSelecionarControle():void {
popUpEscolhaControle = PopUpEscolhaControle(PopUpManager.createPopUp(this, PopUpEscolhaControle, true));
popUpEscolhaControle["btnFechar"].addEventListener(MouseEvent.CLICK, fecharPopUpEscolhaControle);
popUpEscolhaControle["btnFecharComESC"].addEventListener(MouseEvent.CLICK, fecharPopUpEscolhaControleComESC);
setInterval(animarProgressBar, 2500);
}

private function fecharPopUpEscolhaControle(event:MouseEvent): void {
if (popUpEscolhaControle != null) {
if (popUpEscolhaControle.dataGrid.selectedItem != null) {
txtControleCadastro.text = popUpEscolhaControle.dataGrid.selectedItem.local;
lblIdControleCadastro.text = popUpEscolhaControle.dataGrid.selectedItem.id;
}
PopUpManager.removePopUp(popUpEscolhaControle);
popUpEscolhaControle = null;
}
}

private function fecharPopUpEscolhaControleComESC(event:MouseEvent): void {
if (popUpEscolhaControle != null) {
PopUpManager.removePopUp(popUpEscolhaControle);
popUpEscolhaControle = null;
}
}

