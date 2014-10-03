package concept.CentralAutomacao.servlet;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import concept.CentralAutomacao.fachada.ConsumoFachada;
import concept.CentralAutomacao.vo.ConsumoVO;
import concept.CentralAutomacao.vo.Resultado;

@Provider
@Path("/Consumo")
public class ConsumoServlet {
	
	@POST
	@Path("/Recuperar")
	@Produces("application/xml")
	public ConsumoVO recuperar(@Form ConsumoVO vo) {
		ConsumoFachada fachada = new ConsumoFachada();
		return fachada.recuperarConsumo(vo);
	}
	
	@POST
	@Path("/Gravar")
	public String gravar(@Form ConsumoVO vo) {
		ConsumoFachada fachada = new ConsumoFachada();
		return fachada.gravarConsumo(vo);
	}
	
	@POST
	@Path("/Remover")
	public String remover(@FormParam("listaIds") String listaIds) {
		ConsumoFachada fachada = new ConsumoFachada();
		return fachada.excluir(listaIds.split(","));
	}
	
	@POST
	@Path("/Listar")
	@Produces("application/xml")
	@Wrapped(element = "Consumos")
	public Resultado listar(@Form ConsumoVO vo,
	@FormParam("pagina") int pagina) {
		try {
			ConsumoFachada fachada = new ConsumoFachada();
			Resultado resultado = new Resultado();
			
			resultado.setLista(fachada.recuperarListaConsumo(vo, pagina));
			resultado.setQuantidade(fachada.recuperarQuantidadeListaConsumo(vo));
			
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
