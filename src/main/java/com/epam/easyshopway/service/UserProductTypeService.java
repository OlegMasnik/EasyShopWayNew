package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;
import com.epam.easyshopway.dto.UserProductTypeDTO;
import com.epam.easyshopway.model.UserProductType;



public class UserProductTypeService {
	@SuppressWarnings("finally")
	public static List<UserProductType> getUserProductTypes(Integer userId) {
		List<UserProductType> userProducts = null;
		try (UserProductTypeDTO dto = new UserProductTypeDTO()) {
			userProducts = dto.getUserProductTypes(userId);
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
