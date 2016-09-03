package com.epam.easyshopway.connection;

public class ConnectionManager {
	private String url = "jdbc:mysql://localhost:3306/easy_shop_way?useUnicode=true&characterEncoding=UTF-8";
	private String user = "root";
	private String password = "root";
	private ConnectionPool connectionPool;

	private static ConnectionManager instanse = new ConnectionManager();

	public static ConnectionManager getInstance() {
		return instanse;
	}

	private ConnectionManager() {
		connectionPool = new ConnectionPool(url, user, password);
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}
}
