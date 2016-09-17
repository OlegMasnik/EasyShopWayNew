package com.epam.easyshopway.astar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class NearestNeighborhood {
	
	private List<List<Integer>> products = new ArrayList<>();	
	private Cell start = null;
	private Cell buffCell;
	private List<Integer> path = new ArrayList<>();

	private int width;
	private int height;
	
	public NearestNeighborhood(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	private Cell getStart(int start, List<List<Integer>> prod){
		List<Integer> l = null;
		AStar a = new AStar(height, width, new int[][]{});
		int index = 0;
		for(int i = 0; i < prod.size(); i++){
			for(int j = 0; j < prod.get(i).size(); j++){
				a.setStart(start);
				a.setEnd(prod.get(i).get(j));
				a.init();
				a.findPath();
				if(this.start == null || a.end.finalCost < this.start.finalCost){
					this.start = a.end;
					index = i;
					l = a.path;
				}
			}
			System.out.println("Iter " + i);
		}
		System.out.println(l);
		this.path.addAll(l);
		System.out.println(this.start);
		prod.remove(index);
		return this.start;
	}
	
	private Cell getNext() {
		List<Integer> l = null;
		AStar a = new AStar(height, width, new int[][]{});
		int index = 0;
		for(int i = 0; i < prod.size(); i++){
			for(int j = 0; j < prod.get(i).size(); j++){
				a.setStart(start);
				a.setEnd(prod.get(i).get(j));
				a.init();
				a.findPath();
				if(this.start == null || a.end.finalCost < this.start.finalCost){
					this.start = a.end;
					index = i;
					l = a.path;
				}
			}
			System.out.println("Iter " + i);
		}
		System.out.println(l);
		this.path.addAll(l);
		System.out.println(this.start);
		prod.remove(index);
		return this.start;
	}
	
	private Cell findStart(int start, List<List<Integer>> prod){
		return getStart(start, prod);
	}
	
	public static void main(String[] args) {
		System.out.println("Start");
		int start = 0;
		NearestNeighborhood n = new NearestNeighborhood(5 , 5);
		n.products.add(new ArrayList(Arrays.asList(11, 4, 5)));
		n.products.add(new ArrayList(Arrays.asList(1, 10, 15)));
		n.products.add(new ArrayList(Arrays.asList(9, 13, 17)));
		System.out.println("Srart cell: " + n.findStart(start, n.products));
		System.out.println("Path: ");
		System.out.println(n.path);
	}
	
	
	

}
