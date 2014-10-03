package concept.CentralAutomacao.fachada;

import java.util.List;

import concept.CentralAutomacao.dao.ConsumoDAO;
import concept.CentralAutomacao.util.LoggerVia;
import concept.CentralAutomacao.vo.ConsumoVO;


public class ConsumoFachada extends BaseFachada {
	
	public List<ConsumoVO> recuperarListaConsumo(ConsumoVO vo, int pagina) {
		ConsumoDAO dao = new ConsumoDAO();
		try {
			return dao.recuperarListaConsumo(vo, pagina);
		} catch(Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.recuperarListaConsumo", e);
		}
		return null;
	}
	
	public int recuperarQuantidadeListaConsumo(ConsumoVO vo) {
		ConsumoDAO dao = new ConsumoDAO();
		try {
			return dao.recuperarQuantidadeListaConsumo(vo);
		} catch(Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.recuperarQuantidadeListaConsumo", e);
		}
		return 0;
	}
	
	public ConsumoVO recuperarConsumo(ConsumoVO vo) {
		ConsumoDAO dao = new ConsumoDAO();
		try {
			return dao.recuperarConsumo(vo);
		} catch (Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.recuperarConsumo", e);
		}
		return null;
	}
	
	public String gravarConsumo(ConsumoVO vo) {
		if (vo.getId() != 0) {
			return alterar(vo);
		} else {
			return inserir(vo);
		}
	}
	
	private String inserir(ConsumoVO vo) {
		ConsumoDAO dao = new ConsumoDAO();
		try {
			dao.inserir(vo);
			return "Consumo gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.inserir", e);
		}
		return null;
	}
	
	private String alterar(ConsumoVO vo) {
		ConsumoDAO dao = new ConsumoDAO();
		try {
			dao.atualizar(vo);
			return "Consumo gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.alterar", e);
		}
		return null;
	}
	
	public String excluir(String[] listaIds){
		ConsumoDAO dao = new ConsumoDAO();
		try {
			dao.excluir(listaIds);
			return listaIds.length+"Consumo removido(s) com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ConsumoFachada.excluir(varios)", e);
		}
		return null;
	}
}
