package concept.CentralAutomacao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import concept.CentralAutomacao.vo.ControleVO;
import concept.CentralAutomacao.util.Constantes;
import concept.CentralAutomacao.util.Util;


public class ControleDAO extends BaseDAO {
	
	protected static String NOME_TABELA = " Controle ";
	
	protected static String CAMPOS = " Controle.id , Controle.local , Controle.tipo , Controle.canal , Controle.no ";
	
	protected static String SELECT = " SELECT " + CAMPOS
	+ " FROM " + NOME_TABELA;
	
	protected static String SELECT_COUNT = " SELECT COUNT(*) quantidade FROM " + NOME_TABELA;
	
	protected static String INSERT = " INSERT INTO " + NOME_TABELA
	+ " ( local , tipo , canal , no ) "
	+ " VALUES "
	+ " (  ? ,  ? ,  ? ,  ? ) ";
	
	protected static String UPDATE = " UPDATE " + NOME_TABELA
	+ " SET local = ? , tipo = ? , canal = ? , no = ? "
	+ " WHERE id = ? ";
	
	protected static String DELETE = " DELETE FROM " + NOME_TABELA;
	
	public ArrayList<ControleVO> recuperarListaControle(ControleVO vo, int pagina)
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try{
			StringBuffer sql = new StringBuffer(SELECT);
			
			
			
			sql.append(" ORDER BY Controle.local ");
			
			if(pagina >= 0){
				sql.append(" LIMIT " + (pagina * Constantes.MAX_ITENS_LISTADOS_LIMIT) + " , " + Constantes.MAX_ITENS_LISTADOS_LIMIT);
			}
			 
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			
			
			ResultSet rs = super.listar();
			ArrayList<ControleVO> lista = new ArrayList<ControleVO>();
			ControleVO item = null;
			
			while (rs.next()) {
				item = new ControleVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setTipo(rs.getString("tipo"));
				item.setCanal(rs.getString("canal"));
				item.setNo(rs.getString("no"));
				
				
				lista.add(item);
			}
			
			rs.close();
			rs = null;
			
			return lista;
			
		} finally {
			super.fecharConexao();
		}
	}
	
	public int recuperarQuantidadeListaControle(ControleVO vo)
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(SELECT_COUNT);
			
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			ResultSet rs = super.listar();
			int quantidade = 0;
			while (rs.next()) {
				quantidade = rs.getInt("quantidade");
			}
			
			rs.close();
			rs = null;
			return quantidade;
			
		} finally {
			super.fecharConexao();
		}
	}
	
	public ControleVO recuperarControle(ControleVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(SELECT);
			
			Util.incluirClausulaNoWhere_AND(sql, " Controle.id = ? ");
			
			super.prepararDAO(sql.toString());
			
			st.setInt(1, vo.getId());
			
			ResultSet rs = super.listar();
			ControleVO item = null;
			
			while (rs.next()) {
				item = new ControleVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setTipo(rs.getString("tipo"));
				item.setCanal(rs.getString("canal"));
				item.setNo(rs.getString("no"));
				
			}
			
			rs.close();
			rs = null;
			
			return item;
		} finally {
			super.fecharConexao();
		}
	}
	
	public void inserir(ControleVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(INSERT);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getTipo());
			st.setString(indice++, vo.getCanal());
			st.setString(indice++, vo.getNo());
			
			
			super.atualizar();
		} finally {
			super.fecharConexao();
		}
	}
	
	public void atualizar(ControleVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(UPDATE);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getTipo());
			st.setString(indice++, vo.getCanal());
			st.setString(indice++, vo.getNo());
			st.setInt(indice++, vo.getId());
			
			
			super.atualizar();
		} finally {
			super.fecharConexao();
		}
	}
	
	public void excluir(String[] ids) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(DELETE);
			
			for (int i = 0; i < ids.length; i++) {
				Util.incluirClausulaNoWhere_OR(sql, "id = ?");
			}
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			for (int i = 0; i < ids.length; i++) {
				st.setInt(indice++, Integer.parseInt(ids[i]));
			}
			
			super.atualizar();
		} finally {
			super.fecharConexao();
		}
	}
	
}
