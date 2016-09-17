package com.epam.easyshopway.astar;

public class Cell {
	int heuristicCost = 0;
    int finalCost = 0;
    int x, y;
    Cell parent; 
    
    Cell(long i, long j){
        this.x = (int) i;
        this.y = (int) j; 
    }
    
    @Override
    public String toString(){
        return "["+this.x+", "+this.y+"]";
    }
}
