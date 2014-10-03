package concept.CentralAutomacao.fachada;

import java.util.List;

import concept.CentralAutomacao.dao.ControleDAO;
import concept.CentralAutomacao.util.LoggerVia;
import concept.CentralAutomacao.vo.ControleVO;


public class ControleFachada extends BaseFachada {
	
	public List<ControleVO> recuperarListaControle(ControleVO vo, int pagina) {
		ControleDAO dao = new ControleDAO();
		try {
			return dao.recuperarListaControle(vo, pagina);
		} catch(Exception e) {
			LoggerVia.logError("Erro em ControleFachada.recuperarListaControle", e);
		}
		return null;
	}
	
	public int recuperarQuantidadeListaControle(ControleVO vo) {
		ControleDAO dao = new ControleDAO();
		try {
			return dao.recuperarQuantidadeListaControle(vo);
		} catch(Exception e) {
			LoggerVia.logError("Erro em ControleFachada.recuperarQuantidadeListaControle", e);
		}
		return 0;
	}
	
	public ControleVO recuperarControle(ControleVO vo) {
		ControleDAO dao = new ControleDAO();
		try {
			return dao.recuperarControle(vo);
		} catch (Exception e) {
			LoggerVia.logError("Erro em ControleFachada.recuperarControle", e);
		}
		return null;
	}
	
	public String gravarControle(ControleVO vo) {
		if (vo.getId() != 0) {
			return alterar(vo);
		} else {
			return inserir(vo);
		}
	}
	
	private String inserir(ControleVO vo) {
		ControleDAO dao = new ControleDAO();
		try {
			dao.inserir(vo);
			return "Controle gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ControleFachada.inserir", e);
		}
		return null;
	}
	
	private String alterar(ControleVO vo) {
		ControleDAO dao = new ControleDAO();
		try {
			dao.atualizar(vo);
			return "Controle gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ControleFachada.alterar", e);
		}
		return null;
	}
	
	public String excluir(String[] listaIds){
		ControleDAO dao = new ControleDAO();
		try {
			dao.excluir(listaIds);
			return listaIds.length+"Controle removido(s) com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em ControleFachada.excluir(varios)", e);
		}
		return null;
	}
}
