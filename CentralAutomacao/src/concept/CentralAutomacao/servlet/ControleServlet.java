package concept.CentralAutomacao.servlet;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.Form;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import concept.CentralAutomacao.fachada.ControleFachada;
import concept.CentralAutomacao.vo.ControleVO;
import concept.CentralAutomacao.vo.Resultado;

@Provider
@Path("/Controle")
public class ControleServlet {
	
	@POST
	@Path("/Recuperar")
	@Produces("application/xml")
	public ControleVO recuperar(@Form ControleVO vo) {
		ControleFachada fachada = new ControleFachada();
		return fachada.recuperarControle(vo);
	}
	
	@POST
	@Path("/Gravar")
	public String gravar(@Form ControleVO vo) {
		ControleFachada fachada = new ControleFachada();
		return fachada.gravarControle(vo);
	}
	
	@POST
	@Path("/Remover")
	public String remover(@FormParam("listaIds") String listaIds) {
		ControleFachada fachada = new ControleFachada();
		return fachada.excluir(listaIds.split(","));
	}
	
	@POST
	@Path("/Listar")
	@Produces("application/xml")
	@Wrapped(element = "Controles")
	public Resultado listar(@Form ControleVO vo,
	@FormParam("pagina") int pagina) {
		try {
			ControleFachada fachada = new ControleFachada();
			Resultado resultado = new Resultado();
			
			resultado.setLista(fachada.recuperarListaControle(vo, pagina));
			resultado.setQuantidade(fachada.recuperarQuantidadeListaControle(vo));
			
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
