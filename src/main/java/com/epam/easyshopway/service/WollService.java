package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.WollDAO;
import com.epam.easyshopway.model.Woll;

public class WollService {
	public static int update(Integer wollId, Woll woll) {
		try (WollDAO wollDAO = new WollDAO()) {
			return wollDAO.update(wollId, woll);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Integer id) {
		try (WollDAO wollDAO = new WollDAO()) {
			return wollDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int insert(Woll woll) {
		try (WollDAO wollDAO = new WollDAO()) {
			return wollDAO.insert(woll);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Woll getById(Integer id) {
		try (WollDAO wollDAO = new WollDAO()) {
			return wollDAO.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Woll> getAll() {
		try (WollDAO wollDAO = new WollDAO()) {
			return wollDAO.getAll();
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
