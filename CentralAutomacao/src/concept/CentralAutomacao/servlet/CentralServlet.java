package concept.CentralAutomacao.servlet;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import concept.CentralAutomacao.fachada.CentralFachada;
import concept.CentralAutomacao.vo.CentralVO;
import concept.CentralAutomacao.vo.Resultado;

@Provider
@Path("/Central")
public class CentralServlet {
	
	@POST
	@Path("/Recuperar")
	@Produces("application/xml")
	public CentralVO recuperar(@Form CentralVO vo) {
		CentralFachada fachada = new CentralFachada();
		return fachada.recuperarCentral(vo);
	}
	
	@POST
	@Path("/Gravar")
	public String gravar(@Form CentralVO vo) {
		CentralFachada fachada = new CentralFachada();
		return fachada.gravarCentral(vo);
	}
	
	@POST
	@Path("/Remover")
	public String remover(@FormParam("listaIds") String listaIds) {
		CentralFachada fachada = new CentralFachada();
		return fachada.excluir(listaIds.split(","));
	}
	
	@POST
	@Path("/Listar")
	@Produces("application/xml")
	@Wrapped(element = "Centrals")
	public Resultado listar(@Form CentralVO vo,
	@FormParam("pagina") int pagina) {
		try {
			CentralFachada fachada = new CentralFachada();
			Resultado resultado = new Resultado();
			
			resultado.setLista(fachada.recuperarListaCentral(vo, pagina));
			resultado.setQuantidade(fachada.recuperarQuantidadeListaCentral(vo));
			
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
