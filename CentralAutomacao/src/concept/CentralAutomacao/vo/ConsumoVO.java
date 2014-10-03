package concept.CentralAutomacao.vo;

import java.sql.Date;
import concept.CentralAutomacao.util.SqlDateAdapter;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "consumo")
public class ConsumoVO extends BaseVO {
	
	@FormParam("local")
	private String local;
	@FormParam("no")
	private String no;
	@FormParam("canal")
	private String canal;
	@FormParam("consumoDiario")
	private Double consumoDiario;
	@FormParam("dataInicio")
	private Date dataInicio;
	@FormParam("dataFim")
	private Date dataFim;
	@FormParam("data")
	private Date data;
	
	public ConsumoVO() {
		        super();
	}
	
	@XmlElement(name = "local")
	public String getLocal() {
		return this.local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	@XmlElement(name = "no")
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	@XmlElement(name = "canal")
	public String getCanal() {
		return this.canal;
	}
	
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	@XmlElement(name = "consumoDiario")
	public Double getConsumoDiario() {
		return this.consumoDiario;
	}
	
	public void setConsumoDiario(Double consumoDiario) {
		this.consumoDiario = consumoDiario;
	}
	
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name = "dataInicio")
	public Date getDataInicio() {
		return this.dataInicio;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name = "dataFim")
	public Date getDataFim() {
		return this.dataFim;
	}
	@XmlJavaTypeAdapter(SqlDateAdapter.class)
	@XmlElement(name = "data")
	public Date getData() {
		return this.data;
	}
	
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
