package com.epam.easyshopway.astar;

public class Main {
	
	public static void main(String[] args) throws Exception {
		AStar aStar = new AStar(5, 5, new int[][] { { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 } });
		aStar.init();
		aStar.setStart(0);
		aStar.setEnd(17);
		aStar.findPath();
		aStar.printAfter();
		
		aStar.init();
		aStar.setStart(0);
		aStar.setEnd(2);
		aStar.findPath();
		aStar.printAfter();
		System.out.println("Path weight " + aStar.end.finalCost);
		System.out.println("Path:" + aStar.path);
	}
}
