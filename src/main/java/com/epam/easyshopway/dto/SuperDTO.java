package com.epam.easyshopway.dto;

import java.sql.Connection;

import com.epam.easyshopway.connection.ConnectionManager;

public class SuperDTO implements AutoCloseable{
	protected Connection connection;

	public SuperDTO() {
		super();
		connection = ConnectionManager.getInstance().getConnectionPool().getConnection();
	}

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
