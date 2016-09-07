package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.PlacementDAO;
import com.epam.easyshopway.model.Placement;

public class PlacementService {
	public static int update(Integer placementId, Placement placement) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.update(placementId, placement);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Integer id) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int insert(Placement placement) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.insert(placement);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Placement getById(Integer id) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Placement> getAll() {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Placement> getCupboardPlacement(Integer id) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.getcCupboardPlacement(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<Placement> getcPlacementByMapId(Integer id) {
		try (PlacementDAO placementDAO = new PlacementDAO()) {
			return placementDAO.getcPlacementByMaoId(id);
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
