package com.epam.easyshopway.astar;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws Exception {
		List<Long> blocked = new ArrayList<Long>();
		blocked.add((long) 3);
		blocked.add((long) 8);
		blocked.add((long) 9);
		AStar aStar = new AStar(5, 5, blocked);
		aStar.init();
		aStar.setStart(0);
		aStar.setEnd((long) 4);
		aStar.printBefore();
		aStar.findPath();
		aStar.printAfter();
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
