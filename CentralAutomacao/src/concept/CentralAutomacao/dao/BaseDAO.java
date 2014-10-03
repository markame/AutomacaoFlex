package concept.CentralAutomacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  
 */
public class BaseDAO {

	
	protected Connection conexao;

	protected PreparedStatement st;

	private int linhasAfetadas;

	protected void prepararDAOEmTransacao(String instrucaoSQL)
			throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if (conexao == null) {
			conexao = FabricaDAO.criarConexao();
			conexao.setAutoCommit(false);
		}
		st = conexao.prepareStatement(instrucaoSQL);
	}

	protected void prepararDAO(String instrucaoSQL) throws SQLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		conexao = FabricaDAO.criarConexao();
		st = conexao.prepareStatement(instrucaoSQL);
	}

	protected void prepararDAOSemConexao(String instrucaoSQL)
			throws SQLException, ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		st = conexao.prepareStatement(instrucaoSQL);
	}

	protected int atualizar() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		this.fecharConexao();
		return linhasAfetadas;
	}

	protected int atualizarERetornarID() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		ResultSet chave = st.getGeneratedKeys();
		int valorChave = 0;
		while (chave.next()) {
			valorChave = chave.getInt(1);
		}
		chave.close();
		chave = null;
		this.fecharConexao();
		return valorChave;
	}

	protected int atualizarERetornarIDEmTransacao() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		ResultSet chave = st.getGeneratedKeys();
		int valorChave = 0;
		while (chave.next()) {
			valorChave = chave.getInt(1);
		}
		chave.close();
		chave = null;
		return valorChave;
	}

	protected void atualizarLote() throws SQLException {
		linhasAfetadas = st.executeUpdate();
	}

	protected int atualizarEmTransacao() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		return linhasAfetadas;
	}

	protected int atualizarEmTransacaoERetornarID() throws SQLException {
		linhasAfetadas = st.executeUpdate();
		ResultSet chave = st.getGeneratedKeys();
		int valorChave = 0;
		while (chave.next()) {
			valorChave = chave.getInt(1);
		}
		chave.close();
		chave = null;
		return valorChave;
	}

	protected ResultSet listar() throws SQLException {
		return st.executeQuery();

	}

	/**
	 * @return the conexao
	 */
	protected Connection getConexao() {
		return conexao;
	}

	/**
	 * @param conexao
	 *            the conexao to set
	 */
	protected void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	/**
	 * @return the st
	 */
	protected PreparedStatement getSt() {
		return st;
	}

	/**
	 * @param st
	 *            the st to set
	 */
	protected void setSt(PreparedStatement st) {
		this.st = st;
	}

	/**
	 * @return the linhasAfetadas
	 */
	protected int getLinhasAfetadas() {
		return linhasAfetadas;
	}

	/**
	 * @param linhasAfetadas
	 *            the linhasAfetadas to set
	 */
	protected void setLinhasAfetadas(int linhasAfetadas) {
		this.linhasAfetadas = linhasAfetadas;
	}

	protected void fecharConexao() {
		try {
			if (st != null) {
				st.close();
			}
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			st = null;
			conexao = null;
		}
	}
}
