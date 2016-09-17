package com.epam.easyshopway.astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighborhood {
	
	private List<List<Integer>> products = new ArrayList<>();	
	private Cell start = null;
	private Cell buffCell;
	private Set<Integer> path = new LinkedHashSet<>();
	private AStar a;

	private int width;
	private int height;
	
	public NearestNeighborhood(int width, int height, int[] blocked) {
		super();
		this.width = width;
		this.height = height;
		this.a = new AStar(height, width, blocked);
	}

	private int getBest(List<List<Integer>> prod){
		System.out.println("Start cell: " + this.start);
		this.buffCell = null;
		List<Integer> l = null;
		int index = 0;
		for(int i = 0; i < prod.size(); i++){
			for(int j = 0; j < prod.get(i).size(); j++){
				a.init();
				a.setStart(this.start.x * width + this.start.y);
				a.setEnd(prod.get(i).get(j));
				a.findPath();
				if(this.buffCell == null || a.end.finalCost < this.buffCell.finalCost){
					this.buffCell = a.end;
					index = i;
					l = a.path;
				}
			}
		}
		l.remove(l.size() - 1);
		this.start = buffCell.parent;
		this.a.printBefore();
		this.a.printAfter();
		this.path.addAll(l);
		System.out.println("Path = " + this.path);
		return index;
	}
	
	private void start(int start)  {
		int removeIndex;
		this.start = new Cell(start / width, start % width);
		while(!this.products.isEmpty()){
			System.out.println(this.products);
			removeIndex = getBest(this.products);
			this.products.remove(removeIndex);
		}
		System.out.println("Finish");
	}
	
	public static void main(String[] args) {
		System.out.println("Start");
		int start = 0;
		NearestNeighborhood n = new NearestNeighborhood(5 , 5, new int[]{1, 6});
		n.products.add(new ArrayList(Arrays.asList(3, 2)));
		n.products.add(new ArrayList(Arrays.asList(17)));
		n.products.add(new ArrayList(Arrays.asList(24)));
		n.start(start);
		n.a.printAfter();
		System.out.println("Path: ");
		System.out.println(n.path);
	}
	
	
	

}
