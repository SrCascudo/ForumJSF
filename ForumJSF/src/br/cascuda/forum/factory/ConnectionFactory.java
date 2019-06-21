package br.cascuda.forum.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private ConnectionFactory() {
	}

	public static Connection getInstance() {

		Connection conn = null;

		try {
			Class.forName("org.postgresql.Driver"); // BUSCA DRIVER JDBC POSTGRESQL DENTRO DA BIBLIOTECA 

			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/forum",
													"topicos1", "123456"); // ESTABELECE CONEXÃO COM O BANCO DE DADOS

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("CLASS:ConnectionFactory -> O DRIVER JDBC REFERENTE AO POSTGRE NÃO FOI ENCONTRADO :(");
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("CLASS:ConnectionFactory -> FALHA AO TENTAR CONECTAR COM O BANCO DE DADOS :(");
		}

		System.out.println("CLASS:ConnectionFactory -> CONEXÃO BEM SUCEDIDA :)");
		return conn;
	}
}
