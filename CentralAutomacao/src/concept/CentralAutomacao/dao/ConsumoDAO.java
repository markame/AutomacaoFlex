package concept.CentralAutomacao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import concept.CentralAutomacao.vo.ConsumoVO;
import concept.CentralAutomacao.util.Constantes;
import concept.CentralAutomacao.util.Util;


public class ConsumoDAO extends BaseDAO {
	
	protected static String NOME_TABELA = " Consumo ";
	
	protected static String CAMPOS = " Consumo.id , Consumo.local , Consumo.no , Consumo.canal , Consumo.consumoDiario , Consumo.data ";
	
	protected static String SELECT = " SELECT " + CAMPOS
	+ " FROM " + NOME_TABELA;
	
	protected static String SELECT_COUNT = " SELECT COUNT(*) quantidade FROM " + NOME_TABELA;
	
	protected static String INSERT = " INSERT INTO " + NOME_TABELA
	+ " ( local , no , canal , consumoDiario , data ) "
	+ " VALUES "
	+ " (  ? ,  ? ,  ? ,  ? ,  ? ) ";
	
	protected static String UPDATE = " UPDATE " + NOME_TABELA
	+ " SET local = ? , no = ? , canal = ? , consumoDiario = ? , data = ? "
	+ " WHERE id = ? ";
	
	protected static String DELETE = " DELETE FROM " + NOME_TABELA;
	
	public ArrayList<ConsumoVO> recuperarListaConsumo(ConsumoVO vo, int pagina)
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try{
			StringBuffer sql = new StringBuffer(SELECT);
			
			
			
			sql.append(" ORDER BY Consumo.local ");
			
			if(pagina >= 0){
				sql.append(" LIMIT " + (pagina * Constantes.MAX_ITENS_LISTADOS_LIMIT) + " , " + Constantes.MAX_ITENS_LISTADOS_LIMIT);
			}
			 
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			
			
			ResultSet rs = super.listar();
			ArrayList<ConsumoVO> lista = new ArrayList<ConsumoVO>();
			ConsumoVO item = null;
			
			while (rs.next()) {
				item = new ConsumoVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setNo(rs.getString("no"));
				item.setCanal(rs.getString("canal"));
				item.setConsumoDiario(rs.getDouble("consumoDiario"));
				item.setData(rs.getDate("data"));
				
				
				lista.add(item);
			}
			
			rs.close();
			rs = null;
			
			return lista;
			
		} finally {
			super.fecharConexao();
		}
	}
	
	public int recuperarQuantidadeListaConsumo(ConsumoVO vo)
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
	
	public ConsumoVO recuperarConsumo(ConsumoVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(SELECT);
			
			Util.incluirClausulaNoWhere_AND(sql, " Consumo.id = ? ");
			
			super.prepararDAO(sql.toString());
			
			st.setInt(1, vo.getId());
			
			ResultSet rs = super.listar();
			ConsumoVO item = null;
			
			while (rs.next()) {
				item = new ConsumoVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setNo(rs.getString("no"));
				item.setCanal(rs.getString("canal"));
				item.setConsumoDiario(rs.getDouble("consumoDiario"));
				item.setData(rs.getDate("data"));
				
			}
			
			rs.close();
			rs = null;
			
			return item;
		} finally {
			super.fecharConexao();
		}
	}
	
	public void inserir(ConsumoVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(INSERT);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getNo());
			st.setString(indice++, vo.getCanal());
			st.setDouble(indice++, vo.getConsumoDiario());
			st.setDate(indice++, vo.getData());
			
			
			super.atualizar();
		} finally {
			super.fecharConexao();
		}
	}
	
	public void atualizar(ConsumoVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(UPDATE);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getNo());
			st.setString(indice++, vo.getCanal());
			st.setDouble(indice++, vo.getConsumoDiario());
			st.setDate(indice++, vo.getData());
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
