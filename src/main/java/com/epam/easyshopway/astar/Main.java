package com.epam.easyshopway.astar;

public class Main {
	
	public static void main(String[] args) throws Exception {
		AStar aStar = new AStar(5, 5, 0, 0, 3, 2, new int[][] { { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 } });
		aStar.printBefore();
		aStar.findPath();
		aStar.printPath();
		aStar.printAfter();
		System.out.println("Path weight " + aStar.end.finalCost);
		System.out.println("Path:" + aStar.path);
		
	}

}
