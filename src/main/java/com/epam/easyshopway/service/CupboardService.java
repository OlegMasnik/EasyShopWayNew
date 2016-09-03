package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.CupbordDAO;
import com.epam.easyshopway.model.Cupboard;

public class CupboardService {
	public static int insert(Cupboard cupboard) {
		try (CupbordDAO cupboardDAO = new CupbordDAO()) {
			return cupboardDAO.insert(cupboard);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(int index) {
		try (CupbordDAO cupboardDAO = new CupbordDAO()) {
			return cupboardDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Cupboard getById(int index) {
		try (CupbordDAO cupboardDAO = new CupbordDAO()) {
			return cupboardDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Cupboard> getAll() {
		try (CupbordDAO cupboardDAO = new CupbordDAO()) {
			return cupboardDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer id, Cupboard cupboard) {
		try (CupbordDAO cupboardDAO = new CupbordDAO()) {
			return cupboardDAO.update(id, cupboard);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
