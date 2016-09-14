package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.MapDAO;
import com.epam.easyshopway.dao.ProductTypeDAO;
import com.epam.easyshopway.model.Map;

public class MapService {
	public static int update (Integer mapId, Map map){
		try(MapDAO mapDAO = new MapDAO()){
			return mapDAO.update(mapId, map);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int delete (Integer id){
		try(MapDAO mapDAO = new MapDAO()){
			return mapDAO.delete(id);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int insert (Map map){
		try(MapDAO mapDAO = new MapDAO()){
			return mapDAO.insert(map);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Map getById (Integer id){
		try(MapDAO mapDAO = new MapDAO()){
			return mapDAO.getById(id);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static Map getCurrentMap (){
		try (MapDAO mapDAO = new MapDAO()){
			return mapDAO.getCurrentMap();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map getLastInserted (){
		try (MapDAO mapDAO = new MapDAO()){
			return mapDAO.getLastInserted();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Map> getAll (){
		try(MapDAO mapDAO = new MapDAO()){
			return mapDAO.getAll();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean hasNameEn (String nameEn){
		try (MapDAO mapDAO = new MapDAO()) {
			return mapDAO.hasNameEn(nameEn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean hasNameUk (String nameUk){
		try (MapDAO mapDAO = new MapDAO()) {
			return mapDAO.hasNameUk(nameUk);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
