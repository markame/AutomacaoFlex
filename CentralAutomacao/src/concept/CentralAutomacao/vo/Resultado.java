package concept.CentralAutomacao.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "resultado")
@XmlSeeAlso(value = {CentralVO.class, ConsumoVO.class, ControleVO.class})
public class Resultado {

	private int quantidade;

	private List<?> lista;

	@XmlElementWrapper(name = "lista")
	@XmlElement(name = "item")
	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	@XmlElement
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}