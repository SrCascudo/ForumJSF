package br.cascuda.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cascuda.forum.model.Email;
import br.cascuda.forum.model.Phone;
import br.cascuda.forum.model.UserServer;

public class UserServerDao extends Dao<UserServer> {

	@Override
	public void create(UserServer obj) {
		// TODO Auto-generated method stub
		createClient(obj);// INSERE USUARIO NA ENTIDADE CLIENT
		try {
			final ResultSet result = stat.getGeneratedKeys();
			while (result.next()) {
				obj.setId(result.getInt("idclient"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createPhone(obj);// INSERE PHONE INFORMADO PELO CLIENT CADASTRADO
		createEmail(obj);// INSERE EMAIL INFORMADO PELO CLIENT CADASTRADO
		encerrarConexao();
	}

	@Override
	public void update(UserServer obj) {
		// TODO Auto-generated method stub
		try {
			stat = conn.prepareStatement(" UPDATE client " + " SET name = ? , " + " nick = ? , " + " typeuser = ? "
					+ " WHERE idclient = ? ");

			stat.setString(1, obj.getName());
			stat.setString(2, obj.getNick());
			stat.setInt(3, obj.getTypeUser().getValue());
			stat.setInt(4, obj.getId());
			stat.execute();

			stat.clearParameters();

			stat = conn
					.prepareStatement(" UPDATE phone" + " SET ddd = ? , " + " number = ? " + " WHERE deviceowner = ? ");

			stat.setString(1, obj.getPhone().getDdd());
			stat.setString(2, obj.getPhone().getNumber());
			stat.setInt(3, obj.getId());
			stat.execute();

			stat.clearParameters();

			stat = conn
					.prepareStatement(" UPDATE email " + " SET login = ? , " + " password = ? " + " WHERE owner = ? ");

			stat.setString(1, obj.getEmail().getLogIn());
			stat.setString(2, obj.getEmail().getPassword());
			stat.setInt(3, obj.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(UserServer obj) {
		// TODO Auto-generated method stub
		try {
			stat = conn.prepareStatement(" DELETE FROM client " + " WHERE idclient = ? ");
			stat.setInt(1, obj.getId());
			stat.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<UserServer> registry() {
		// TODO Auto-generated method stub
		ResultSet result;
		List<UserServer> listUser = new ArrayList<UserServer>();
		try {
			stat = conn.prepareStatement("SELECT * FROM client");
			result = stat.executeQuery();
			while (result.next()) {
				UserServer user = new UserServer();
				user.setId(result.getInt("idclient"));
				user.setName(result.getString("name"));
				user.setNick(result.getString("nick"));
				user.getTypeUser().setValue(result.getInt("typeuser"));
				user.setPhone(takePhone(user.getId()));
				user.setEmail(takeEmail(user.getId()));
				listUser.add(user);
			}
			return listUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Phone takePhone(int id) {
		ResultSet result;
		try {
			stat = conn.prepareStatement("SELECT * FROM phone WHERE deviceowner = ?");
			stat.setInt(1, id);
			result = stat.executeQuery();

			while (result.next()) {
				Phone phone = new Phone();
				phone.setDdd(result.getString("ddd"));
				phone.setNumber(result.getString("number"));
				return phone;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR TELEFONE");
		}
		return null;
	}

	private Email takeEmail(int id) {
		ResultSet result;
		try {
			stat = conn.prepareStatement("SELECT * FROM email WHERE owner = ?");
			stat.setInt(1, id);
			result = stat.executeQuery();

			while (result.next()) {
				Email email = new Email();
				email.setLogIn(result.getString("login"));
				email.setPassword(result.getString("password"));
				return email;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERRO AO BUSCAR EMAIL");
		}
		return null;
	}

	private void createClient(UserServer obj) {
		try {
			stat = conn.prepareStatement(" INSERT INTO client ( name , nick , typeuser ) " 
									   + " VALUES ( ? , ? , ? ) " , Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getNick());
			stat.setInt(3, obj.getTypeUser().getValue());
			stat.execute();
			System.out.println("CLASS:UserServerDao -> CADASTRO CLIENT BEM SUCEDIDO :)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CLASS:UserServerDao -> FALHA AO CADASTRAR CLIENT :(");
		}
	}

	private void createPhone(UserServer obj) {
		try {
			stat = conn
					.prepareStatement(" INSERT INTO phone ( deviceowner , ddd , number ) " + " VALUES ( ? , ? , ? )");
			stat.setInt(1, obj.getId());
			stat.setString(2, obj.getPhone().getDdd());
			stat.setString(3, obj.getPhone().getNumber());
			stat.execute();
			System.out.println("CLASS:UserServerDao -> CADASTRO PHONE BEM SUCEDIDO :)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CLASS:UserServerDao -> FALHA AO CADASTRAR PHONE :(");
		}
	}

	private void createEmail(UserServer obj) {
		try {
			stat = conn.prepareStatement(" INSERT INTO email ( owner , login , password ) " + " VALUES ( ? , ? , ? )");
			stat.setInt(1, obj.getId());
			stat.setString(2, obj.getEmail().getLogIn());
			stat.setString(3, obj.getEmail().getPassword());
			stat.execute();
			System.out.println("CLASS:UserServerDao -> CADASTRO EMAIL BEM SUCEDIDO :)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CLASS:UserServerDao -> FALHA AO CADASTRAR EMAIL :(");
		}
	}

	public UserServer takeUserById(int id) {
		ResultSet result = null;
		try {
			stat = conn.prepareStatement("SELECT * FROM client WHERE idclient = ? ");
			stat.setInt(1, id);
			result = stat.executeQuery();

			while (result.next()) {
				UserServer aux = new UserServer();// USUARIO CRIADO UNICAMENTE PARA PREENCHER A LISTA A CADA LAÇO
				aux.setId(result.getInt("idclient"));
				aux.setName(result.getString("name"));
				aux.setNick(result.getString("nick"));
				aux.getTypeUser().setValue(result.getInt("typeuser"));
				aux.setEmail(takeEmail(aux.getId())); // UTILIZA MÉTODO TAKE PARA BUSCAR EMAIL REFERENTE AO ID
				aux.setPhone(takePhone(aux.getId())); // UTILIZA MÉTODO TAKE PARA BUSCAR PHONE REFERENTE AO ID
				return aux;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * AO UTILIZAR ESSE MÉTODO ***select*** INFORME A TABELA E A COLUNA PELA QUAL
	 * DESEJA PROCURAR RESPECTIVAMENTE; LOGO APÓS INFORME A COLUNA DE COMPARAÇÃO E A
	 * CONDIÇÃO DA BUSCA PARA O COMANDO SQL ***WHERE*** QUE SERÁ CONSTRUIDO
	 * INTERNAMENTE DA SEGUINTE FORMA ---> ***** WHERE condicaoElementoA =
	 * condicaoElementoB; ******
	 */
	private ResultSet select(String tabela, String coluna, String condicaoElementoA, String operador,
			String condicaoElementoB) {
		ResultSet resultado = null;
		try {
			stat = conn.prepareStatement(
					" SELECT " + coluna + " FROM " + tabela + " WHERE " + condicaoElementoA + operador + " ? ");
			stat.setString(1, condicaoElementoB);
			resultado = stat.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("CLASS: UserServerDao -> FALHA AO REALIZAR A BUSCA");
		}
		return resultado;
	}
	// ------------------------------------------------------------------------------------------------------------------------

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
