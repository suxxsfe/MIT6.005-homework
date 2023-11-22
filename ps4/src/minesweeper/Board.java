/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.lang.Math;

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
    //
    // Grid is not thread safe
       
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
    
    // Abstraction function:
    //   AF(sizeX, sizeY, board) = a game board with size sizeX(horizontal) * sizeY(vertical)
    // Rep invariant:
    //   sizeX > 0, sizeY > 0
    //   board[i][j] != null
    // Safety from rep exposure:
    //   All fields are private and final
    //   nothing in rep will be returned as result
    // Thread safety argument:
    //   all accessed to board happen within Board public method, which are all guarded by Board's lock
    
    private final int sizeX, sizeY;
    private final Grid[][] board;
    static final int dNum = 8;
    static final int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static final int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static final double rate = 0.25;
    
    public Board(int _sizeX, int _sizeY){
        sizeX = _sizeX;
        sizeY = _sizeY;
        board = new Grid[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                board[i][j] = new Grid(Math.random() <= rate);
            }
        }
    }
    
    public Board(int _sizeX, int _sizeY, boolean[][] bombs){
        sizeX = _sizeX;
        sizeY = _sizeY;
        board = new Grid[sizeX][sizeY];
        for(int i = 0; i < sizeX; i++){
            for(int j = 0; j < sizeY; j++){
                board[i][j] = new Grid(bombs[j][i]);
            }
        }
    }
    
    private boolean checkGrid(int x, int y){
        return x >= 0 && y >= 0 && x < sizeX && y < sizeY;
    }
    
    private char countBombs(int x, int y){
        int cnt = 0;
        for(int k = 0; k < dNum; k++){
            int newX = x+dx[k], newY = y+dy[k];
            if(checkGrid(newX, newY) && board[newX][newY].isBomb()){
                cnt++;
            }
        }
        return cnt == 0 ? ' ' : (char)(cnt+'0');
    }
    
    public synchronized String getBoard(){
        String res = "";
        for(int j = 0; j < sizeY; j++){
            for(int i = 0; i < sizeX; i++){
                char grid = board[i][j].getStatus();
                res+=(grid == ' ' ? countBombs(i, j) : grid);
                res+= (i == sizeX-1) ? '\n' : ' ';
            }
        }
        return res;
    }
    
    public synchronized void flag(int x, int y){
        if(checkGrid(x, y)){
            board[x][y].flag();
        }
    }
    
    public synchronized void deflag(int x, int y){
        if(checkGrid(x, y)){
            board[x][y].deflag();
        }
    }
    
    private void digRec(int x, int y){
        if(countBombs(x, y) != ' '){
            return;
        }
        for(int k = 0; k < dNum; k++){
            int newX = x+dx[k], newY = y+dy[k];
            if(checkGrid(newX, newY) && board[newX][newY].getStatus() != ' '){
                board[newX][newY].dig();
                digRec(newX, newY);
            }
        }
    }
    
    public synchronized boolean dig(int x, int y){
        if(!checkGrid(x, y) || board[x][y].getStatus() != '-'){
            return false;
        }
        board[x][y].dig();
        if(board[x][y].isBomb()){
            board[x][y].clearBomb();
            return true;
        }
        digRec(x, y);
        return false;
    }
    
}
