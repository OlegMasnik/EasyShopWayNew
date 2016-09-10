package com.epam.easyshopway.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
	private static final int COUNT_CONNECTION = 50;
	private BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(
			COUNT_CONNECTION);

	ConnectionPool(String url, String user, String password) {
		try {
			for (int i = 0; i < COUNT_CONNECTION; i++) {
				Class.forName("com.mysql.jdbc.Driver");
				connections.add(DriverManager
						.getConnection(url, user, password));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		try {
			return connections.poll(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			return null;
		}
	}

	public void putConnection(Connection connection) {
		connections.add(connection);
	}

}
