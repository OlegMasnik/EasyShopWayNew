package com.epam.easyshopway.astar;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighborhood {

	private List<List<Long>> products;
	private List<Long> paydesks;
	private Cell start = null;
	private Cell buffCell;
	public Set<Long> path = new LinkedHashSet<>();
	public List<Integer> visited = new ArrayList<>();
	private AStar a;

	private int width;
	private int height;

	public NearestNeighborhood(int width, int height, List<Long> blocked, List<Long> paydesks,
			List<List<Long>> products, List<Long> cupboards) {
		super();
		this.width = width;
		this.height = height;
		this.paydesks = paydesks;
		this.products = products;
		blocked.addAll(cupboards);
		blocked.addAll(paydesks);
		this.a = new AStar(height, width, blocked);
	}

	private int getBest(List<List<Long>> prod) {
		// System.out.println("Start cell: " + this.start);
		this.buffCell = null;
		List<Long> l = null;
		int index = 0;
		for (int i = 0; i < prod.size(); i++) {
			System.out.println(prod);
			for (int j = 0; j < prod.get(i).size(); j++) {
				a.init();
				a.setStart(this.start.x * width + this.start.y);
				a.setEnd(prod.get(i).get(j));
				System.out.println("target " + prod.get(i).get(j));
				a.findPath();
				System.out.println("Path: " + a.path);
				if (a.path.size() > 0 && (this.buffCell == null || a.end.finalCost < this.buffCell.finalCost)) {
					this.buffCell = a.end;
					index = i;
					l = a.path;
				}
			}
		}
		if(buffCell != null){
			this.start = buffCell.parent;
			this.visited.add(buffCell.x * width + buffCell.y);
		}
		// this.a.printBefore();
		// this.a.printAfter();
		if (l != null && l.size() > 0) {
			l.remove(l.size() - 1);
			this.path.addAll(l);
		}
		// System.out.println("Path = " + this.path);
		return index;
	}

	public void start(int start) {
		int removeIndex;
		this.start = new Cell(start / width, start % width);
		while (!this.products.isEmpty()) {
			// System.out.println(this.products);
			removeIndex = getBest(this.products);
			System.out.println(removeIndex);
			this.products.remove(removeIndex);
		}
		if (path.size() > 0) {
			getBestPaydesk();
		} else {
			System.out.println("Not path");
		}
		System.out.println("Finish");
	}

	private void getBestPaydesk() {
		this.buffCell = null;
		List<Long> l = null;
		for (int i = 0; i < paydesks.size(); i++) {
			a.init();
			a.setStart(this.start.x * width + this.start.y);
			a.setEnd(paydesks.get(i));
			a.findPath();
			if (this.buffCell == null || a.end.finalCost < this.buffCell.finalCost) {
				this.buffCell = a.end;
				l = a.path;
			}
		}
		if (l.size() > 0) {
			l.remove(l.size() - 1);
			this.path.addAll(l);
		}
		this.start = buffCell.parent;
		System.out.println("Path = " + this.path);
	}
}
