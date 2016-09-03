package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.EntryDAO;
import com.epam.easyshopway.model.Entry;

public class EntryService {
	public static int update (Integer entryId, Entry entry){
		try(EntryDAO entryDAO = new EntryDAO()){
			return entryDAO.update(entryId, entry);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int delete (Integer id){
		try(EntryDAO entryDAO = new EntryDAO()){
			return entryDAO.delete(id);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int insert (Entry entry){
		try(EntryDAO entryDAO = new EntryDAO()){
			return entryDAO.insert(entry);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Entry getById (Integer id){
		try(EntryDAO entryDAO = new EntryDAO()){
			return entryDAO.getById(id);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Entry> getAll (){
		try(EntryDAO entryDAO = new EntryDAO()){
			return entryDAO.getAll();
		}catch (SQLException e){
			e.printStackTrace();
		}catch (IllegalAccessException | InstantiationException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
