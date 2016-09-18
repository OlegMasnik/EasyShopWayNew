package com.epam.easyshopway.astar;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class NearestNeighborhood {

	private List<List<Long>> products;
	private List<Long> paydesks;
	private List<Long> cupboards;
	private Cell start = null;
	private Cell buffCell;
	public Set<Long> path = new LinkedHashSet<>();
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
		this.cupboards = cupboards;
		blocked.addAll(cupboards);
		blocked.addAll(paydesks);
		this.a = new AStar(height, width, blocked);
	}

	private int getBest(List<List<Long>> prod) {
//		System.out.println("Start cell: " + this.start);
		this.buffCell = null;
		List<Long> l = null;
		int index = 0;
		for (int i = 0; i < prod.size(); i++) {
			for (int j = 0; j < prod.get(i).size(); j++) {
				a.init();
				a.setStart(this.start.x * width + this.start.y);
				a.setEnd(prod.get(i).get(j));
				a.findPath();
				if (this.buffCell == null || a.end.finalCost < this.buffCell.finalCost) {
					this.buffCell = a.end;
					index = i;
					l = a.path;
				}
			}
		}
		l.remove(l.size() - 1);
		this.start = buffCell.parent;
//		this.a.printBefore();
//		this.a.printAfter();
		this.path.addAll(l);
//		System.out.println("Path = " + this.path);
		return index;
	}

	public void start(int start) {
		int removeIndex;
		this.start = new Cell(start / width, start % width);
		while (!this.products.isEmpty()) {
//			System.out.println(this.products);
			removeIndex = getBest(this.products);
			this.products.remove(removeIndex);
		}
		getBestPaydesk();
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
		l.remove(l.size() - 1);
		this.start = buffCell.parent;
		this.path.addAll(l);
		System.out.println("Path = " + this.path);
	}
}
