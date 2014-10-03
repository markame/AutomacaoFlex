package concept.CentralAutomacao.vo;

import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlElement;

public class BaseVO {
	
	@FormParam("id")
	protected int id;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	@XmlElement(name = "id")	
	public int getId() {
		return this.id;
	}

}