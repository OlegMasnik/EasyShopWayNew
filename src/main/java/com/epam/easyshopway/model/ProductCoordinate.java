package com.epam.easyshopway.model;

import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.service.PlacementService;
import com.epam.easyshopway.service.ProductPlacementService;

public class ProductCoordinate {

	public static List<Integer> getProductCoordinatesOnMap(int productId, int mapId) {
		List<Integer> coordinates = new ArrayList<Integer>();
		List<ProductPlacement> productPlacements = ProductPlacementService
				.getByProductIdAndMapId(productId, mapId);
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

	public static List<Integer> getProductCoordinatesOnCupboard(int productId, int cupboarId) {
		List<Integer> coordinates = new ArrayList<Integer>();
		List<ProductPlacement> productPlacements = ProductPlacementService
				.getByProductIdAndCupboardId(productId, cupboarId);
		if (!productPlacements.isEmpty()) {
			for (ProductPlacement productPlacement : productPlacements) {
				coordinates.add(productPlacement.getPlace());
			}
		}
		return coordinates;

	}
}
