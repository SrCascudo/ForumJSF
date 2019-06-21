package br.cascuda.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cascuda.forum.model.UserServer;

public class LoginDao extends Dao<UserServer>{

	@Override
	public void create(UserServer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserServer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UserServer obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserServer> registry() {
		// TODO Auto-generated method stub
		ResultSet result;
		List<UserServer> listUser = new ArrayList<UserServer>();
		result = select("email", "*");			
		try {
			while(result.next()) {
				UserServer aux = new UserServer();
				aux.getEmail().setLogIn(result.getString("login"));
				aux.getEmail().setPassword(result.getString("password"));
				aux.setId(result.getInt("owner"));
				listUser.add(aux);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUser;
	}

	/***
	 * AO UTILIZAR ESSE MÉTODO ***select*** INFORME A TABELA E A COLUNA PELA QUAL DESEJA PROCURAR RESPECTIVAMENTE; 
	 * */
	private ResultSet select(String tabela, String coluna) {
		ResultSet resultado = null;
		try {
			stat = conn.prepareStatement(" SELECT " + coluna + " FROM " + tabela);
			resultado = stat.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CLASS: LoginDao -> FALHA AO REALIZAR A BUSCA");
		}
		return resultado;
	}
	//------------------------------------------------------------------------------------------------------------------------
	
	public void encerrarConexao() {
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
