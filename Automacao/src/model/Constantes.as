import mx.collections.ArrayCollection;

public var MAX_ITENS_LISTADOS_LIMIT:int = 200;

public const DIAS_SEMANA:Array = ["D", "S", "T", "Q", "Q", "S", "S"];

public const MESES:Array = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];

public const SEXO:Array = [
	{label:"Masculino", data:"MASCULINO"},
	{label:"Feminino", data:"FEMININO"}
	];

[Bindable] public var sexoAC:ArrayCollection = new ArrayCollection(SEXO);

public const ESTADO_CIVIL:Array = [
	{label: "Solteiro", data: "SOLTEIRO"},
	{label: "Casado", data: "CASADO"},
	{label: "Divorciado", data: "DIVORCIADO"},
	{label: "Viúvo", data: "VIUVO"},
	{label: "União Estável", data: "UNIAO_ESTAVEL"}
];

[Bindable] public var estadoCivilAC:ArrayCollection = new ArrayCollection(ESTADO_CIVIL);

public const CATEGORIA_IMOVEL:Array = [
	{label:"Imóvel Avulso", data:"IMOVEL_AVULSO"},
	{label:"Empreendimento", data:"EMPREENDIMENTO"}
];

[Bindable] public var categoriaImovelAC:ArrayCollection = new ArrayCollection(CATEGORIA_IMOVEL);

public const OPERACAO:Array = [
	{label: "Aluguel", data: "ALUGUEL"},
	{label: "Venda", data: "VENDA"}
];

[Bindable] public var operacaoAC:ArrayCollection = new ArrayCollection(OPERACAO);

public const STATUS_IMOVEL:Array = [
	{label: "Á venda", data: "A_VENDA"},
	{label: "Vendido", data: "VENDIDO"}
];

[Bindable] public var statusImovelAC:ArrayCollection = new ArrayCollection(STATUS_IMOVEL);

public const ESTADOS:Array = [
	{label: "PI", data: "1"}
];

[Bindable] public var estadosAC:ArrayCollection = new ArrayCollection(ESTADOS);

public const TIPO_COMPROMISSO:Array = [
	{label: "Visita", data: "VISITA"},
	{label: "Outro", data: "OUTRO"}
];

[Bindable] public var tipoCompromissoAC:ArrayCollection = new ArrayCollection(TIPO_COMPROMISSO);

public const ACAO_PROPOSTA:Array = [
	{label: "Proposta Inicial", data: "PROPOSTA_INICAL"},
	{label: "Atualizar Proposta", data: "ATUALIZAR_PROPOSTA"},
	{label: "Concluir Proposta", data: "CONCLUIR_PROPOSTA"}	
];

[Bindable] public var acaoPropostaAC:ArrayCollection = new ArrayCollection(ACAO_PROPOSTA);

public const HORAS:Array = [
	{label: "01", data: "01"},
	{label: "02", data: "02"},
	{label: "03", data: "03"},
	{label: "04", data: "04"},
	{label: "05", data: "05"},
	{label: "06", data: "06"},
	{label: "07", data: "07"},
	{label: "08", data: "08"},
	{label: "09", data: "09"},
	{label: "10", data: "10"},
	{label: "11", data: "11"},
	{label: "12", data: "12"},
	{label: "13", data: "13"},
	{label: "14", data: "14"},
	{label: "15", data: "15"}
];

[Bindable] public var horasAC:ArrayCollection = new ArrayCollection(HORAS);

public const MINUTOS:Array = [
	{label: "00", data: "00"},
	{label: "10", data: "10"},
	{label: "20", data: "20"},
	{label: "30", data: "30"},
	{label: "40", data: "40"},
	{label: "50", data: "50"}
	
];
	
[Bindable] public var minutosAC:ArrayCollection = new ArrayCollection(MINUTOS);