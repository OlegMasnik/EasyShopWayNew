package com.epam.easyshopway.astar;

public class Main {
	
	public static void main(String[] args) throws Exception {
		AStar aStar = new AStar(7, 7, new int[] { 4, 43, 13});
		aStar.init();
		aStar.setStart(0);
		aStar.setEnd(7*7 - 1);
		aStar.findPath();
//		aStar.printBefore();
//		aStar.printAfter();
		System.out.println("Path weight " + aStar.end.finalCost);
		System.out.println("Path:" + aStar.path);
		
//		System.out.println("**************************");
//		
//		aStar.init();
//		aStar.setStart(0);
//		aStar.setEnd(2);
//		aStar.findPath();
//		aStar.printBefore();
//		aStar.printAfter();
//		System.out.println("Path weight " + aStar.end.finalCost);
//		System.out.println("Path:" + aStar.path);
	}
}
