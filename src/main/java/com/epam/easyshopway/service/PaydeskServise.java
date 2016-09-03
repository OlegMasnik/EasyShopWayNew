package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.PaydeskDAO;
import com.epam.easyshopway.model.Paydesk;

public class PaydeskServise {
	public static int update(Integer paydeskId, Paydesk paydesk) {
		try (PaydeskDAO paydeskDAO = new PaydeskDAO()) {
			return paydeskDAO.update(paydeskId, paydesk);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Integer id) {
		try (PaydeskDAO paydeskDAO = new PaydeskDAO()) {
			return paydeskDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int insert(Paydesk paydesk) {
		try (PaydeskDAO paydeskDAO = new PaydeskDAO()) {
			return paydeskDAO.insert(paydesk);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Paydesk getById(Integer id) {
		try (PaydeskDAO paydeskDAO = new PaydeskDAO()) {
			return paydeskDAO.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Paydesk> getAll() {
		try (PaydeskDAO paydeskDAO = new PaydeskDAO()) {
			return paydeskDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
