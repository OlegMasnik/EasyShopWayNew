package com.epam.easyshopway.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

	public List<Integer> path;
	private final int DIAGONAL_COST = 14;
	private final int V_H_COST = 10;
	private Cell[][] grid;
	private int[][] blocked;
	private PriorityQueue<Cell> open;
	private boolean closed[][];
	public Cell start;
	public Cell end;
	public int width, height;

	private void setBlocked(int i, int j) {
		grid[i][j] = null;
	}

	public void setStart(int i) {
		this.start = new Cell(i / width, i % width);
	}

	public void setEnd(int i) {
		this.end = new Cell(i / width, i % width);
	}

	private void checkAndUpdateCost(Cell current, Cell t, int cost) {
		if (t == null || closed[t.x][t.y])
			return;
		int t_final_cost = t.heuristicCost + cost;

		boolean inOpen = open.contains(t);
		if (!inOpen || t_final_cost < t.finalCost) {
			t.finalCost = t_final_cost;
			t.parent = current;
			if (!inOpen)
				open.add(t);
		}
	}

	public void findPath() {
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				grid[i][j] = new Cell(i, j);
				grid[i][j].heuristicCost = Math.abs(i - end.x) + Math.abs(j - end.y);
			}
		}
		this.open.add(this.grid[start.x][start.y]);
		grid[start.x][start.y].finalCost = 0;
		Cell current;

		while (true) {
			current = open.poll();
			if (current == null)
				break;
			closed[current.x][current.y] = true;

			if (current.equals(grid[end.x][end.y])) {
				this.end = current;
				generatePath();
				return;
			}

			Cell t;
			if (current.x - 1 >= 0) {
				t = grid[current.x - 1][current.y];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

				if (current.y - 1 >= 0) {
					t = grid[current.x - 1][current.y - 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}

				if (current.y + 1 < grid[0].length) {
					t = grid[current.x - 1][current.y + 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}
			}

			if (current.y - 1 >= 0) {
				t = grid[current.x][current.y - 1];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
			}

			if (current.y + 1 < grid[0].length) {
				t = grid[current.x][current.y + 1];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
			}

			if (current.x + 1 < grid.length) {
				t = grid[current.x + 1][current.y];
				checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

				if (current.y - 1 >= 0) {
					t = grid[current.x + 1][current.y - 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}

				if (current.y + 1 < grid[0].length) {
					t = grid[current.x + 1][current.y + 1];
					checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
				}
			}
		}
	}

	public AStar(int height, int width, int[][] blocked) {
		this.height = height;
		this.width = width;
		this.blocked = blocked;
	}

	public void init() {
		path = new ArrayList<>();
		this.grid = new Cell[height][width];
		this.closed = new boolean[height][width];
		this.open = new PriorityQueue<>((Object o1, Object o2) -> {
			Cell c1 = (Cell) o1;
			Cell c2 = (Cell) o2;

			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
		for (int i = 0; i < this.blocked.length; ++i) {
			setBlocked(this.blocked[i][0], this.blocked[i][1]);
		}
	}

	public void printBefore() {
		System.out.println("Grid: ");
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if (i == start.x && j == start.y)
					System.out.print("SO  "); // Source
				else if (i == end.x && j == end.y)
					System.out.print("DE  "); // Destination
				else if (grid[i][j] != null)
					System.out.printf("%-3d ", 0);
				else
					System.out.print("BL  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printAfter() {
		System.out.println("\nScores for cells: ");
		for (int i = 0; i < this.height; ++i) {
			for (int j = 0; j < this.height; ++j) {
				if (this.grid[i][j] != null)
					System.out.printf("%-3d ", this.grid[i][j].finalCost);
				else
					System.out.print("BL  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void generatePath(){
		if(this.closed[this.end.x][this.end.y]){
            Cell current = this.grid[this.end.x][this.end.y];
            path.add(current.x * this.width + current.y);
            while(current.parent!=null){
                path.add(0, current.parent.x * this.width + current.parent.y);
                current = current.parent;
            } 
       }else{
    	   System.out.println("No possible path");
       }
	}

}
