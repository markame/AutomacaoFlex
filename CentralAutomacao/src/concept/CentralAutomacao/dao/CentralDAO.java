package concept.CentralAutomacao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import concept.CentralAutomacao.vo.ConsumoVO;
import concept.CentralAutomacao.vo.ControleVO;
import concept.CentralAutomacao.vo.CentralVO;
import concept.CentralAutomacao.util.Constantes;
import concept.CentralAutomacao.util.Util;


public class CentralDAO extends BaseDAO {
	
	protected static String NOME_TABELA = " Central ";
	
	protected static String CAMPOS = " Central.id , Central.local , Central.canal , Central.IP , Central.no , Central.idConsumo , Central.idControle ";
	
	protected static String SELECT = " SELECT " + CAMPOS
	+ ", Consumo.local AS 'localConsumo'"
	+ ", Controle.local AS 'localControle'"
	+ " FROM " + NOME_TABELA
	+ " JOIN Consumo ON Central.idConsumo = Consumo.id "
	+ " JOIN Controle ON Central.idControle = Controle.id ";
	
	protected static String SELECT_COUNT = " SELECT COUNT(*) quantidade FROM " + NOME_TABELA
	+ " JOIN Consumo ON Central.idConsumo = Consumo.id "
	+ " JOIN Controle ON Central.idControle = Controle.id ";
	
	protected static String INSERT = " INSERT INTO " + NOME_TABELA
	+ " ( local , canal , IP , no , idConsumo , idControle ) "
	+ " VALUES "
	+ " (  ? ,  ? ,  ? ,  ? ,  ? ,  ? ) ";
	
	protected static String UPDATE = " UPDATE " + NOME_TABELA
	+ " SET local = ? , canal = ? , IP = ? , no = ? , idConsumo = ? , idControle = ? "
	+ " WHERE id = ? ";
	
	protected static String DELETE = " DELETE FROM " + NOME_TABELA;
	
	public ArrayList<CentralVO> recuperarListaCentral(CentralVO vo, int pagina)
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try{
			StringBuffer sql = new StringBuffer(SELECT);
			
			
			
			sql.append(" ORDER BY Central.local ");
			
			if(pagina >= 0){
				sql.append(" LIMIT " + (pagina * Constantes.MAX_ITENS_LISTADOS_LIMIT) + " , " + Constantes.MAX_ITENS_LISTADOS_LIMIT);
			}
			 
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			
			
			ResultSet rs = super.listar();
			ArrayList<CentralVO> lista = new ArrayList<CentralVO>();
			CentralVO item = null;
			ConsumoVO consumo = null;
			ControleVO controle = null;
			
			while (rs.next()) {
				item = new CentralVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setCanal(rs.getString("canal"));
				item.setIP(rs.getString("IP"));
				item.setNo(rs.getString("no"));
				
				consumo = new ConsumoVO();
				consumo.setId(rs.getInt("idConsumo"));
				consumo.setLocal(rs.getString("localConsumo"));
				item.setConsumo(consumo);
				
				controle = new ControleVO();
				controle.setId(rs.getInt("idControle"));
				controle.setLocal(rs.getString("localControle"));
				item.setControle(controle);
				
				
				lista.add(item);
			}
			
			rs.close();
			rs = null;
			
			return lista;
			
		} finally {
			super.fecharConexao();
		}
	}
	
	public int recuperarQuantidadeListaCentral(CentralVO vo)
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
	
	public CentralVO recuperarCentral(CentralVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(SELECT);
			
			Util.incluirClausulaNoWhere_AND(sql, " Central.id = ? ");
			Util.incluirClausulaNoWhere_AND(sql, " Central.idConsumo = ? ");
			Util.incluirClausulaNoWhere_AND(sql, " Central.idControle = ? ");
			
			super.prepararDAO(sql.toString());
			
			st.setInt(1, vo.getId());
			
			ResultSet rs = super.listar();
			CentralVO item = null;
			ConsumoVO consumo = null;
			ControleVO controle = null;
			
			while (rs.next()) {
				item = new CentralVO();
				item.setId(rs.getInt("id"));
				item.setLocal(rs.getString("local"));
				item.setCanal(rs.getString("canal"));
				item.setIP(rs.getString("IP"));
				item.setNo(rs.getString("no"));
				
				consumo = new ConsumoVO();
				consumo.setId(rs.getInt("idConsumo"));
				consumo.setLocal(rs.getString("localConsumo"));
				item.setConsumo(consumo);
				
				controle = new ControleVO();
				controle.setId(rs.getInt("idControle"));
				controle.setLocal(rs.getString("localControle"));
				item.setControle(controle);
				
			}
			
			rs.close();
			rs = null;
			
			return item;
		} finally {
			super.fecharConexao();
		}
	}
	
	public void inserir(CentralVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(INSERT);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getCanal());
			st.setString(indice++, vo.getIP());
			st.setString(indice++, vo.getNo());
			st.setInt(indice++, vo.getConsumo().getId());
			st.setInt(indice++, vo.getControle().getId());
			
			
			super.atualizar();
		} finally {
			super.fecharConexao();
		}
	}
	
	public void atualizar(CentralVO vo) 
	throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			StringBuffer sql = new StringBuffer(UPDATE);
			
			super.prepararDAO(sql.toString());
			
			int indice = 1;
			
			st.setString(indice++, vo.getLocal());
			st.setString(indice++, vo.getCanal());
			st.setString(indice++, vo.getIP());
			st.setString(indice++, vo.getNo());
			st.setInt(indice++, vo.getConsumo().getId());
			st.setInt(indice++, vo.getControle().getId());
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
