package com.epam.easyshopway.model;

import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.service.PlacementService;
import com.epam.easyshopway.service.ProductPlacementService;

public class ProductCoordinate {

	public static List<Integer> getProductCoordinatesOnMap(int productId) {
		List<Integer> coordinates = new ArrayList<Integer>();
		List<ProductPlacement> productPlacements = ProductPlacementService
				.getByProductId(productId);
		if (!productPlacements.isEmpty()) {
			for (ProductPlacement productPlacement : productPlacements) {
				List<Placement> cupboardPlacements = PlacementService
						.getCupboardPlacement(productPlacement.getCupboardId());
				if (!cupboardPlacements.isEmpty()) {
					Integer coordinateIndex = productPlacement.getPlace()
							% cupboardPlacements.size();
					Integer coordinate = cupboardPlacements
							.get(coordinateIndex).getPlace();
					coordinates.add(coordinate);
				}
			}
		}

		return coordinates;

	}
}
