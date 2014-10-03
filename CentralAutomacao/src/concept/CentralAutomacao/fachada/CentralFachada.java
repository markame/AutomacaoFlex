package concept.CentralAutomacao.fachada;

import java.util.List;

import concept.CentralAutomacao.dao.CentralDAO;
import concept.CentralAutomacao.util.LoggerVia;
import concept.CentralAutomacao.vo.CentralVO;


public class CentralFachada extends BaseFachada {
	
	public List<CentralVO> recuperarListaCentral(CentralVO vo, int pagina) {
		CentralDAO dao = new CentralDAO();
		try {
			return dao.recuperarListaCentral(vo, pagina);
		} catch(Exception e) {
			LoggerVia.logError("Erro em CentralFachada.recuperarListaCentral", e);
		}
		return null;
	}
	
	public int recuperarQuantidadeListaCentral(CentralVO vo) {
		CentralDAO dao = new CentralDAO();
		try {
			return dao.recuperarQuantidadeListaCentral(vo);
		} catch(Exception e) {
			LoggerVia.logError("Erro em CentralFachada.recuperarQuantidadeListaCentral", e);
		}
		return 0;
	}
	
	public CentralVO recuperarCentral(CentralVO vo) {
		CentralDAO dao = new CentralDAO();
		try {
			return dao.recuperarCentral(vo);
		} catch (Exception e) {
			LoggerVia.logError("Erro em CentralFachada.recuperarCentral", e);
		}
		return null;
	}
	
	public String gravarCentral(CentralVO vo) {
		if (vo.getId() != 0) {
			return alterar(vo);
		} else {
			return inserir(vo);
		}
	}
	
	private String inserir(CentralVO vo) {
		CentralDAO dao = new CentralDAO();
		try {
			dao.inserir(vo);
			return "Central gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em CentralFachada.inserir", e);
		}
		return null;
	}
	
	private String alterar(CentralVO vo) {
		CentralDAO dao = new CentralDAO();
		try {
			dao.atualizar(vo);
			return "Central gravado com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em CentralFachada.alterar", e);
		}
		return null;
	}
	
	public String excluir(String[] listaIds){
		CentralDAO dao = new CentralDAO();
		try {
			dao.excluir(listaIds);
			return listaIds.length+"Central removido(s) com sucesso.";
		} catch (Exception e) {
			LoggerVia.logError("Erro em CentralFachada.excluir(varios)", e);
		}
		return null;
	}
}
