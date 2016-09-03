package com.epam.easyshopway.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.connection.ConnectionManager;
import com.epam.easyshopway.dao.transformer.Transformer;

public abstract class AbstractDAO<E> implements AutoCloseable {

	protected Connection connection;

	public AbstractDAO() {
		super();
		connection = ConnectionManager.getInstance().getConnectionPool().getConnection();
	}

	public abstract int insert(E el) throws SQLException;

	public abstract int update(Integer id, E el) throws SQLException;

	public abstract int delete(Integer id) throws SQLException;

	public abstract List<E> getAll() throws SQLException, IllegalAccessException, InstantiationException;

	public abstract E getById(Integer id) throws SQLException, IllegalAccessException, InstantiationException;

	@Override
	public void close() throws Exception {
		ConnectionManager.getInstance().getConnectionPool().putConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
