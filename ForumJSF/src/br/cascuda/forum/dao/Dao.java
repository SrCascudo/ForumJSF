package br.cascuda.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import br.cascuda.forum.factory.ConnectionFactory;

public abstract class Dao <T> {
	
	Connection conn = ConnectionFactory.getInstance();
	PreparedStatement stat = null;

	abstract void create(T obj);
	
	abstract void update(T obj);
	
	abstract void delete(T obj);
	
	abstract List<T> registry();
}
