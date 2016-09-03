package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.CupboardPlacementDAO;
import com.epam.easyshopway.model.CupboardPlacement;


public class CupboardPlacementService {
	public static int insert (CupboardPlacement cupboardPlacement){
		try(CupboardPlacementDAO cupboardPlacementDAO = new CupboardPlacementDAO()){
			return cupboardPlacementDAO.insert(cupboardPlacement);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static List<CupboardPlacement> getAll (){
		try(CupboardPlacementDAO cupboardPlacementDAO = new CupboardPlacementDAO()){
			return cupboardPlacementDAO.getAll();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static CupboardPlacement getById (Integer id){
		try(CupboardPlacementDAO cupboardPlacementDAO = new CupboardPlacementDAO()){
			return cupboardPlacementDAO.getById(id);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static int update (Integer id, CupboardPlacement cupboardPlacement){
		try(CupboardPlacementDAO cupboardPlacementDAO = new CupboardPlacementDAO()){
			return cupboardPlacementDAO.update(id, cupboardPlacement);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
