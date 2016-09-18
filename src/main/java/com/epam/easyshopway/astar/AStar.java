package com.epam.easyshopway.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class AStar {

	public List<Long> path;
	private final int DIAGONAL_COST = 14;
	private final int V_H_COST = 10;
	private Cell[][] grid;
	private List<Long> blocked;
	private PriorityQueue<Cell> open;
	private boolean closed[][];
	public Cell start;
	public Cell end;
	public int width, height;

	private void setBlocked(long i, long j) {
		if (i == end.x && j == end.y) {
			
		} else {
			grid[(int) i][(int) j] = null;
		}
	}

	public void setStart(int i) {
		this.start = new Cell(i / width, i % width);
	}

	public void setEnd(Long long1) {
		this.end = new Cell(long1 / width, long1 % width);
	}

	public AStar(int height, int width, List<Long> blocked) {
		this.height = height;
		this.width = width;
		this.blocked = blocked;
		this.grid = new Cell[height][width];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				grid[i][j] = new Cell(i, j);
			}
		}
//		for (int i = 0; i < this.blocked.size(); ++i) {
//			setBlocked(this.blocked.get(i) / width, this.blocked.get(i) % width);
//		}
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
		for (int i = 0; i < this.blocked.size(); ++i) {
			setBlocked(this.blocked.get(i) / width, this.blocked.get(i) % width);
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

	public void init() {
		path = new ArrayList<>();
		this.closed = new boolean[height][width];
		this.open = new PriorityQueue<>((Object o1, Object o2) -> {
			Cell c1 = (Cell) o1;
			Cell c2 = (Cell) o2;

			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
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
			for (int j = 0; j < this.width; ++j) {
				if (this.grid[i][j] != null)
					System.out.printf("%-3d ", this.grid[i][j].finalCost);
				else
					System.out.print("BL  ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void generatePath() {
		if (this.closed[this.end.x][this.end.y]) {
			Cell current = this.grid[this.end.x][this.end.y];
			path.add((long) (current.x * this.width + current.y));
			while (current.parent != null) {
				path.add(0, (long) (current.parent.x * this.width + current.parent.y));
				current = current.parent;
			}
		} else {
			System.out.println("No possible path");
		}
	}

	private boolean cotains(int[] arr, int value) {
		return IntStream.of(arr).anyMatch(x -> x == value);
	}

}
