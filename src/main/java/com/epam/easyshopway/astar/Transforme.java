package com.epam.easyshopway.astar;

public class Transforme {
	
	public static int two2one(int x, int y, int width){
		return x * width + y;
	}
	
	public static int[] onne2two(int x, int width){
		int[] a = {(x / width), (x % width)};
		return a;
	}

}
