package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {// classe pra conectar e desconectar com o banco de dados
	
	private static Connection conn = null;
	
	public static Connection getConnection() {// metodo para conectar com o DB
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
	}
	
	// Método pra carregar as propriedas salvas no File db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// metodo para fechar o ST
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// metodo para fechar o RS
		public static void closeResultSet(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				}
				catch(SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}

}
