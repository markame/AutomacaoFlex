include "urlServidor.as";

import flash.net.URLRequest;
import flash.net.navigateToURL;
import flash.utils.setInterval;

import mx.collections.ArrayCollection;
import mx.collections.IList;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.core.UIComponent;
import mx.events.FlexEvent;
import mx.formatters.CurrencyFormatter;
import mx.formatters.DateFormatter;
import mx.formatters.NumberFormatter;
import mx.formatters.PhoneFormatter;
import mx.managers.CursorManager;
import mx.managers.PopUpManager;
import mx.rpc.events.FaultEvent;
import mx.rpc.events.ResultEvent;
import mx.utils.StringUtil;

import spark.components.BorderContainer;
import spark.components.DropDownList;
import spark.components.Label;
import spark.components.TextInput;
import spark.components.gridClasses.GridColumn;
import spark.events.TextOperationEvent;

import view.complementos.PopUpAguarde;
import view.complementos.PopUpMensagem

[Bindable] private var popUpMensagem:PopUpMensagem;
[Bindable] private var popUpAguarde:PopUpAguarde;

public function handleFault(event:FaultEvent):void {
	fecharPopUpMensagem();
	fecharPopUpAguarde();
	trace(event);
}

private function exibirPopUpAguarde(): void {
	popUpAguarde = PopUpAguarde(PopUpManager.createPopUp(this, PopUpAguarde, true));
	PopUpManager.centerPopUp(popUpAguarde);
//	CursorManager.setBusyCursor();
}

private function fecharPopUpAguarde(): void {
	if (popUpAguarde != null) {
		PopUpManager.removePopUp(popUpAguarde);
		popUpAguarde = null;
	}
}

private function exibirPopUpMensagem(mensagem:String): void {
	popUpMensagem = PopUpMensagem(PopUpManager.createPopUp(this,PopUpMensagem,true));
	popUpMensagem.pb.label = mensagem;
	PopUpManager.centerPopUp(popUpMensagem);
	setInterval(animarProgressBar, 2500);
	CursorManager.setBusyCursor();	
}

private function obterStringDeData(data:Date):String{
	var formatter:DateFormatter = new DateFormatter();
	formatter.formatString = "DD/MM/YYYY";
	return formatter.format(data);
}

private function animarProgressBar():void {
	if (popUpMensagem != null) {
		if (popUpMensagem.pb.percentComplete == 100) {
			popUpMensagem.pb.setProgress(0,100); 
		} else {
			popUpMensagem.pb.setProgress(popUpMensagem.pb.percentComplete+10,100);
		}   	
	}
}

private function fecharPopUpMensagem(): void {
	if (popUpMensagem != null) {
		PopUpManager.removePopUp(popUpMensagem);
		popUpMensagem = null;
		CursorManager.removeBusyCursor();
	}
}

private function formatar2Digitos(pValor: Number):String {
	if (pValor < 10) {
		return "0" + pValor;
	}
	return String(pValor);
}

private function sair(): void {
	navigateToURL(new URLRequest("index.html"),"_self");
}

private function isClienteAutenticado(mensagem:ResultEvent): void {
	if (mensagem.result.hasOwnProperty("codigo") && mensagem.result.codigo == '99') {
		sair();
	}
}

private function selecionarItemComboBox(valorDoItem:String, cb:DropDownList):Object {
	for(var cont:int = 0; cont < cb.dataProvider.length; cont++) {
		if(StringUtil.trim(cb.dataProvider[cont]['data']) == StringUtil.trim(valorDoItem)) {
			return cb.dataProvider[cont];
		}
	}
	return null;
}

private function selecionarIndice(valorDoItem:String, data: IList):int {
	for (var cont:int = 0; cont < data.length; cont++ ) {
		if (StringUtil.trim(data[cont].data) == StringUtil.trim(valorDoItem)) {
			return cont;
		}
	}
	return 0;
}

private function formatarData(item:Object, column:GridColumn):String{
	var formatter:DateFormatter = new DateFormatter();
	formatter.formatString = "DD/MM/YYYY";
	
	return formatter.format(item[column.dataField].toString());
}

private function formatarTimeStampComSegundos(item:Object, column:DataGridColumn):String{
	if(item[column.dataField].toString() != ""){
		var formatter:DateFormatter = new DateFormatter();
		formatter.formatString = "DD/MM/YYYY";
		
		var teste:String = item[column.dataField].toString();
		
		var resultado:Array = teste.split(" ");
		resultado[0] = formatter.format(resultado[0]);
		
		var hora:Array = resultado[1].split(".");
		
		return resultado[0] + " " + hora[0];
	}
		
	else{
		return "";
	}
}

private function formatarTimeStampSemSegundos(item:Object, column:DataGridColumn):String{
	if(item[column.dataField].toString() != ""){
		var formatter:DateFormatter = new DateFormatter();
		formatter.formatString = "DD/MM/YYYY";
		
		var teste:String = item[column.dataField].toString();
		
		var resultado:Array = teste.split(" ");
		resultado[0] = formatter.format(resultado[0]);
		
		var hora:Array = resultado[1].split(":");
		
		return resultado[0] + " " + hora[0] + ":" + hora[1];
	}
	else{
		return "";
	}
}

private function formatarMoedaDataGrid(item:Object, column:DataGridColumn):String{
	var formatter:CurrencyFormatter = new CurrencyFormatter();
	formatter.currencySymbol = "R$ ";
	formatter.decimalSeparatorTo = ",";
	formatter.thousandsSeparatorTo = ".";
	formatter.precision = 2;
	
	return formatter.format(item[column.dataField].toString());
}

private function formatarNumeroDecimalParaEnvio(valorOriginal:String):String {
	var formatter:NumberFormatter = new NumberFormatter;
	formatter.thousandsSeparatorFrom = ".";
	formatter.thousandsSeparatorTo = "";
	formatter.decimalSeparatorFrom = ",";
	formatter.decimalSeparatorTo = ".";
	formatter.precision = 2;
	
	return formatter.format(valorOriginal);
}

private function formatarNumeroDecimalRecebido(valorOriginal:String):String {
	var formatter:NumberFormatter = new NumberFormatter;
	formatter.thousandsSeparatorFrom = "";
	formatter.thousandsSeparatorTo = ".";
	formatter.decimalSeparatorFrom = ".";
	formatter.decimalSeparatorTo = ",";
	formatter.precision = 2;
	
	return formatter.format(valorOriginal);
}

	

private function formatarDataDataGrid(item:Object, column:DataGridColumn):String{
	var formatter:DateFormatter = new DateFormatter();
	formatter.formatString = "DD/MM/YYYY";
	
	return formatter.format(item[column.dataField].toString());
}

private function formatarTimeStampComSegundosDataGrid(item:Object, column:DataGridColumn):String{
	if(item[column.dataField].toString() != ""){
		var formatter:DateFormatter = new DateFormatter();
		formatter.formatString = "DD/MM/YYYY";
		
		var timeStampComSegundos:String = item[column.dataField].toString();
		
		var resultado:Array = timeStampComSegundos.split(" ");
		resultado[0] = formatter.format(resultado[0]);
		
		var hora:Array = resultado[1].split(".");
		
		return resultado[0] + " " + hora[0];
	}
		
	else{
		return "";
	}
}

private function formatarTimeStampSemSegundosDataGrid(item:Object, column:GridColumn):String{
	if(item[column.dataField].toString() != ""){
		var formatter:DateFormatter = new DateFormatter();
		formatter.formatString = "DD/MM/YYYY";
		
		var timeStampSemSegundos:String = item[column.dataField].toString();
		
		var resultado:Array = timeStampSemSegundos.split(" ");
		resultado[0] = formatter.format(resultado[0]);
		
		var hora:Array = resultado[1].split(":");
		
		return resultado[0] + " " + hora[0] + ":" + hora[1];
	}
	else{
		return "";
	}
}

private function newBrowserWindow(url:String):void{
	var urlRequest:URLRequest = new URLRequest(url);
	navigateToURL(urlRequest, "_blank");
}

public static function alterarVisibilidade(componente:UIComponent, estado:Boolean):void {
	componente.includeInLayout = estado;
	componente.visible = estado;
}

private function modificarVisibilidadeBC(label:Label, borderContainer:BorderContainer):void {
	borderContainer.includeInLayout = !borderContainer.includeInLayout;
	borderContainer.visible = !borderContainer.visible;
	if(borderContainer.visible) {
		label.text = label.text.replace("+", "-");
	}
	else {
		label.text = label.text.replace("-", "+");
	}
}

public static function getLabelDataGrid(valores:ArrayCollection):Function {
	var retf:Function;
	retf = function  (item:Object, column:DataGridColumn):String {
		var coluna:String = column.dataField.toString();
		var colunas:Array = new Array();
		var cont:int = 0;
		var cont2:int = 0;
		colunas = coluna.split(".");
		
		for(cont = 0; cont < colunas.length; cont++){
			item = item.descendants(colunas[cont].toString());
		}
		for(cont = 0; cont < valores.length; cont++){
			if(item.toString() == valores[cont]['data']){
				return valores[cont]['label'];
			}
		}
		return null;
	}
	return retf;
}

private function formatarDataParaEnvio(data:Date):String {
	return data.getFullYear() + "-" + (formatar2Digitos(data.getMonth() + 1)) + "-" + formatar2Digitos(data.getDate());
}

