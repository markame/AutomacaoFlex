package concept.CentralAutomacao.vo;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jboss.resteasy.annotations.Form;


@XmlRootElement(name = "central")
public class CentralVO extends BaseVO {
	
	@FormParam("local")
	private String local;
	@FormParam("canal")
	private String canal;
	@FormParam("IP")
	private String IP;
	@FormParam("no")
	private String no;
	@Form(prefix = "consumo")
	private ConsumoVO consumo;
	@Form(prefix = "controle")
	private ControleVO controle;
	
	public CentralVO() {
		        super();
	}
	
	@XmlElement(name = "local")
	public String getLocal() {
		return this.local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	@XmlElement(name = "canal")
	public String getCanal() {
		return this.canal;
	}
	
	public void setCanal(String canal) {
		this.canal = canal;
	}
	
	@XmlElement(name = "IP")
	public String getIP() {
		return this.IP;
	}
	
	public void setIP(String IP) {
		this.IP = IP;
	}
	
	@XmlElement(name = "no")
	public String getNo() {
		return this.no;
	}
	
	public void setNo(String no) {
		this.no = no;
	}
	
	public ConsumoVO getConsumo() {
		return this.consumo;
	}
	
	public void setConsumo(ConsumoVO consumo) {
		this.consumo = consumo;
	}
	
	public ControleVO getControle() {
		return this.controle;
	}
	
	public void setControle(ControleVO controle) {
		this.controle = controle;
	}
	
	
}
