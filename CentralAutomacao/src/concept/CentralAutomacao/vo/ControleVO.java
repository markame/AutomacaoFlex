package concept.CentralAutomacao.vo;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "controle")
public class ControleVO extends BaseVO {
	
	@FormParam("local")
	private String local;
	@FormParam("tipo")
	private String tipo;
	@FormParam("canal")
	private String canal;
	@FormParam("no")
	private String no;
	
	public ControleVO() {
		        super();
	}
	
	@XmlElement(name = "local")
	public String getLocal() {
		return this.local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	@XmlElement(name = "tipo")
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@XmlElement(name = "canal")
	public String getCanal() {
		return this.canal;
	}
	
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	@XmlElement(name = "no")
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	
}
