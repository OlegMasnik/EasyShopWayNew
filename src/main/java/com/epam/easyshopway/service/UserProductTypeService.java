package com.epam.easyshopway.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.epam.easyshopway.dto.UserProductTypeDTO;
import com.epam.easyshopway.model.UserProductType;



public class UserProductTypeService {
	@SuppressWarnings("finally")
	public static List<UserProductType> getUserProductTypes(Integer userId, Date startDate, Date endDate) {
		List<UserProductType> userProducts = new ArrayList<>();
		try (UserProductTypeDTO dto = new UserProductTypeDTO()) {
			userProducts = dto.getUserProductTypes(userId, startDate, endDate);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			return userProducts;
		}
		
	}
}
