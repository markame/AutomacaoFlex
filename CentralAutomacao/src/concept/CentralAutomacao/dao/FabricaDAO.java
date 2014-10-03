package concept.CentralAutomacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import concept.CentralAutomacao.util.LoggerVia;

/**
 * Fabrica de DAO
 */
public class FabricaDAO {

	private static String urlMYSQL = "";

	private static String nomeUsuarioMySQL = "";

	private static String senhaUsuarioMySQL = "";

	private static InitialContext ctx;

	private static Context envCtx;

	private static DataSource ds;

	private static boolean datasourceDown = false;

	private static boolean isDeamon = false;

	public static boolean isDeamon() {
		return isDeamon;
	}

	public static void setDeamon(boolean isDeamon) {
		FabricaDAO.isDeamon = isDeamon;
	}

	public static Connection criarConexao() throws SQLException {
		if (!isDeamon && !datasourceDown) {
			if (ctx == null) {
				try {
					ctx = new InitialContext();
					envCtx = (Context) ctx.lookup("java:comp/env");
					ds = (DataSource) envCtx.lookup("jdbc/CentralAutomacaoBD");
				} catch (NamingException e) {
					e.printStackTrace();
					datasourceDown = true;
				}
			}
		}

		if (isDeamon()) {
			return criarConexaoSimples();
		}

		return ds.getConnection();
	}

	public static Connection criarConexaoSimples() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			return DriverManager.getConnection(urlMYSQL, nomeUsuarioMySQL,
					senhaUsuarioMySQL); // Producao
		} catch (Exception e) {
			LoggerVia.logError("Erro ao criar conexï¿½ï¿½ï¿½ï¿½o", e);
		}
		return null;
	}

	public static void setarParametrosConexao(String pUrlMysql,
			String pNomeUsuario, String pSenhaUsuario) {
		urlMYSQL = pUrlMysql;
		nomeUsuarioMySQL = pNomeUsuario;
		senhaUsuarioMySQL = pSenhaUsuario;
		isDeamon = true;
	}
}