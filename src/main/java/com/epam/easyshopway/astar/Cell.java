package com.epam.easyshopway.astar;

public class Cell {
	int heuristicCost = 0;
    int finalCost = 0;
    int x, y;
    Cell parent; 
    
    Cell(int i, int j){
        this.x = i;
        this.y = j; 
    }
    
    @Override
    public String toString(){
        return "["+this.x+", "+this.y+"]";
    }
}
