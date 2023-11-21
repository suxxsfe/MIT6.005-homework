/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

class Grid{
    private boolean containsBomb;
    private char status;
    
    // Abstraction function:
    //   AF(containsBomb, status) = a grid in board,
    //      contains a bomb if containsBomb is ture,
    //      untouched if status is '-'
    //      flagged if status is 'F'
    //      dug if status is ' '
    // Rep invariant:
    //   status == '-' || status == 'F' || status == ' '
    // Safety from rep exposure:
    //   All fields are private
    //   All fields are basic data type
       
    private boolean checkRep(){
        return status == '-' || status == 'F' || status == ' ';
    }
    
    public Grid(boolean bomb){
        containsBomb = bomb;
        status = '-';
    }
    
    public boolean isBomb(){
        return containsBomb;
    }
    
    public void clearBomb(){
        containsBomb = false;
    }
    
    public void dig(){
        status = ' ';
    }
    
    public void flag(){
        status = 'F';
    }
    
    public void deflag(){
        status = '-';
    }
       
    public char getStatus(){
        return status;
    }
}

/**
 * TODO: Specification
 */
public class Board {
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    int sizeX, sizeY;
    private final Grid[][] board;
    static final int dNum = 8;
    static final int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public Board(int _sizeX, int _sizeY){
        sizeX = _sizeX;
        sizeY = _sizeY;
        board = new Grid[sizeY][sizeX];
    }
    
    private boolean checkGrid(int x, int y){
        return x >= 0 && y >= 0 && x < sizeX && y < sizeY;
    }
    
    private char countBombs(int x, int y){
        int cnt = 0;
        for(int k = 0; k < dNum; k++){
            int newX = x+dx[k], newY = y+dy[k];
            if(checkGrid(newX, newY) && board[newY][newX].isBomb()){
                cnt++;
            }
        }
        return cnt == 0 ? ' ' : (char)(cnt+'0');
    }
    
    public String[] getBoard(){
        String res[] = new String[sizeY];
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
                char grid = board[j][i].getStatus();
                res[j]+=(grid == ' ' ? countBombs(i, j) : grid);
            }
        }
        return res;
    }
    
    public void flag(int x, int y){
        if(checkGrid(x, y)){
            board[y][x].flag();
        }
    }
    
    public void deflag(int x, int y){
        if(checkGrid(x, y)){
            board[y][x].deflag();
        }
    }
    
    private void digRec(int x, int y){
        if(countBombs(x, y) != ' '){
            return;
        }
        board[y][x].dig();
        for(int k = 0; k < dNum; k++){
            int newX = x+dx[k], newY = y+dy[k];
            if(checkGrid(newX, newY) && board[newY][newX].getStatus() != ' '){
                digRec(newX, newY);
            }
        }
    }
    
    public boolean dig(int x, int y){
        if(!checkGrid(x, y) || board[y][x].getStatus() != '-'){
            return false;
        }
        board[y][x].dig();
        if(board[y][x].isBomb()){
            board[y][x].clearBomb();
            return true;
        }
        digRec(x, y);
        return false;
    }
    
}
