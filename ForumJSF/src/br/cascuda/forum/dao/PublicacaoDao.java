package br.cascuda.forum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.cascuda.forum.model.Publicacao;

public class PublicacaoDao extends Dao<Publicacao> {

	@Override
	public void create(Publicacao obj) {
		// TODO Auto-generated method stub
		
	}

	public void publicar(Publicacao obj, int id) {
		
		try {
			stat = conn.prepareStatement(" INSERT INTO publicacoes ( descricao , data_publicado , client ) "
									    +" VALUES ( ? , (select current_date) , ?) ");
			stat.setString(1, obj.getDescricao());
			stat.setInt(2, id);
			
			stat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	void update(Publicacao obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void delete(Publicacao obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Publicacao> registry() {
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		ResultSet resultado;
		try {
			stat = conn.prepareStatement("select * from publicacoes");
			resultado = stat.executeQuery();
			while (resultado.next()) {
				Publicacao publicacao = new Publicacao();
				publicacao.setDescricao(resultado.getString("descricao"));
				publicacao.setNickQuemPublicou(takeNickUser(resultado.getInt("client")));
				publicacao.setId(resultado.getInt("idpublicacao"));
				publicacao.setQuandoPublicado(resultado.getDate("data_publicado").toLocalDate());
				publicacoes.add(publicacao);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return publicacoes;
	}
	
	private String takeNickUser(int id) {
		ResultSet result = null;
		try {
			stat = conn.prepareStatement("SELECT nick FROM client WHERE idclient = ?");
			stat.setInt(1, id);
			result = stat.executeQuery();
			
			while(result.next()) {
				return result.getString("nick");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Publicacao> takeComentarios(int id){
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		ResultSet resultado;
		try {
			stat = conn.prepareStatement("SELECT * FROM comentarios WHERE idpublicacao = ?");
			stat.setInt(1, id);
			resultado = stat.executeQuery();
			
			while(resultado.next()) {
				Publicacao publicacao = new Publicacao();
				publicacao.setDescricao(resultado.getString("descricao"));
				publicacao.setNickQuemPublicou(takeNickUser(resultado.getInt("client")));
				publicacao.setQuandoPublicado(resultado.getDate("data_publicado").toLocalDate());
				publicacoes.add(publicacao);
				return publicacoes;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void encerrarConexao() {
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Publicacao> takePublicaoUser(int id) {
		ResultSet result = null;
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		try {
			stat = conn.prepareStatement("SELECT * FROM publicacoes WHERE client = ?");
			stat.setInt(1, id);
			result = stat.executeQuery();
			
			while(result.next()) {
				Publicacao publicacao = new Publicacao();
				publicacao.setDescricao(result.getString("descricao"));
				publicacao.setNickQuemPublicou(takeNickUser(result.getInt("client")));
				publicacao.setId(result.getInt("idpublicacao"));
				publicacao.setQuandoPublicado(result.getDate("data_publicado").toLocalDate());
				publicacoes.add(publicacao);
			}
			return publicacoes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Publicacao> consultarPorData(String date) {
		ResultSet result = null;
		List<Publicacao> publicacoes = new ArrayList<Publicacao>();
		try {
			stat = conn.prepareStatement("SELECT * FROM publicacoes WHERE data_publicado = " + date);
			result = stat.executeQuery();
			
			while(result.next()) {
				Publicacao publicacao = new Publicacao();
				publicacao.setDescricao(result.getString("descricao"));
				publicacao.setNickQuemPublicou(takeNickUser(result.getInt("client")));
				publicacao.setId(result.getInt("idpublicacao"));
				publicacao.setQuandoPublicado(result.getDate("data_publicado").toLocalDate());
				publicacoes.add(publicacao);
			}
			return publicacoes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}