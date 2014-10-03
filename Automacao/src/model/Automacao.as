include "Generico.as";

public var usuario:XML = null;

public function entrar():void {
	//mlLogin.unloadModule();
	mainScreen.selectedIndex = 1;
}

protected function init():void {
	carregarModuloCentral();
}

public function carregarModuloCentral():void {
	ml.url = "view/Central.swf";
}

public function carregarModuloConsumo():void {
	ml.url = "view/Consumo.swf";
}

public function carregarModuloControle():void {
	ml.url = "view/Controle.swf";
}

