package com.epam.easyshopway.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dto.DiagramShopCountDTO;
import com.epam.easyshopway.model.DiagramShopCount;

public class DiagramShopCountService {

	public static List<DiagramShopCount> getShopCount(
			Date startDate, Date endDate) {
		List<DiagramShopCount> shopCounts = new ArrayList<>();
		try (DiagramShopCountDTO dto = new DiagramShopCountDTO()) {
			shopCounts = dto.getShopCountAdmin(startDate, endDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return shopCounts;
		}
	}

	public static List<DiagramShopCount> getShopCountByUserId(Integer userId, Date startDate, Date endDate) {
		List<DiagramShopCount> shopCounts = new ArrayList<>();
		try (DiagramShopCountDTO dto = new DiagramShopCountDTO()) {
			shopCounts = dto.getShopCountByUserId(userId, startDate, endDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return shopCounts;
		}
	}
	
	public static int getTotalShopCount(Date startDate, Date endDate){
		Integer count = 0;
		try (DiagramShopCountDTO dto = new DiagramShopCountDTO()) {
			count = dto.getTotalShopCount(startDate, endDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return count;
		}
	}
	
	public static int getTotalShopCountByUserId(Integer userId, Date startDate, Date endDate){
		Integer count = 0;
		try (DiagramShopCountDTO dto = new DiagramShopCountDTO()) {
			count = dto.getTotalShopCountByUserId(userId, startDate, endDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return count;
		}
	}

}
