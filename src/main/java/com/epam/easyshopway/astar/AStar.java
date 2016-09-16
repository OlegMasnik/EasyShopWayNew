package com.epam.easyshopway.astar;

import java.util.PriorityQueue;

public class AStar {

	private final int DIAGONAL_COST = 14;
	private final int V_H_COST = 10;

	private Cell[][] grid;
	private PriorityQueue<Cell> open;
	private boolean closed[][];
	private Cell start;
	private Cell end;
	private int width, height;

	private void setBlocked(int i, int j) {
		grid[i][j] = null;
	}

	private void setStart(int i, int j) {
		this.start = new Cell(i, j);
	}

	private void setEnd(int i, int j) {
		this.end = new Cell(i, j);
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

	private void findPath() {		
		this.open.add(this.grid[start.x][start.y]);

		Cell current;

		while (true) {
			current = open.poll();
			if (current == null)
				break;
			closed[current.x][current.y] = true;

			if (current.equals(grid[end.x][end.y])) {
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

	public AStar(int height, int width, int si, int sj, int ei, int ej, int[][] blocked) {
		this.height = height;
		this.width = width;
		this.grid = new Cell[height][width];
		this.closed = new boolean[height][width];
		this.open = new PriorityQueue<>((Object o1, Object o2) -> {
			Cell c1 = (Cell) o1;
			Cell c2 = (Cell) o2;

			return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
		});
		setStart(si, sj);
		setEnd(ei, ej);
		for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i-end.x)+Math.abs(j-end.y);
            }
         }
         grid[si][sj].finalCost = 0;
         for (int i = 0; i < blocked.length; ++i) {
        	 setBlocked(blocked[i][0], blocked[i][1]);
         }
	}
	
	
	public void printBefore(){
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
	
	public void printAfter(){
		System.out.println("\nScores for cells: ");
        for(int i=0;i< this.height;++i){
            for(int j=0;j< this.height;++j){
                if(this.grid[i][j]!=null)System.out.printf("%-3d ", this.grid[i][j].finalCost);
                else System.out.print("BL  ");
            }
            System.out.println();
        }
        System.out.println();
	}
	
	
	public void printPath(){
		if(this.closed[this.end.x][this.end.y]){
            //Trace back the path 
            System.out.println("Path: ");
            Cell current = this.grid[this.end.x][this.end.y];
            System.out.print(current);
            while(current.parent!=null){
                System.out.print(" -> "+current.parent);
                current = current.parent;
            } 
            System.out.println();
       }else System.out.println("No possible path");
	}

	public static void main(String[] args) throws Exception {
		AStar aStar = new AStar(5, 5, 0, 0, 3, 2, new int[][] { { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 } });
//		aStar.printBefore();
		aStar.findPath();
		aStar.printPath();
//		aStar.printAfter();
	}

}
