/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * TODO: Description
 */
public class BoardTest {
    
    // TODO: Testing strategy
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    Board b = new Board(4, 3);
    
    @Test
    public void testInit(){
        String s = b.getBoard();
        
        assertEquals("---- ---- ---- ", s, "- - - -\n- - - -\n- - - -\n");
    }
    
    @Test
    public void testFlag(){
        b.flag(0, 0);
        b.flag(1, 2);
        String s = b.getBoard();
        
        assertEquals("F--- ---- -F-- ", s, "F - - -\n- - - -\n- F - -\n");
    }
    
    @Test
    public void testDeflag(){
        b.flag(0, 0);
        b.flag(1, 2);
        b.deflag(0, 0);
        String s = b.getBoard();
        
        assertEquals("---- ---- -F--", s, "- - - -\n- - - -\n- F - -\n");
    }
    
    @Test
    public void testDigBomb(){
        boolean bombs[][] = {
            {false, false, true, true},
            {false, true, true, true},
            {false, false, true, false}};
        
        Board bb = new Board(4, 3, bombs);
        boolean ret = bb.dig(1, 1);
        String s = bb.getBoard();
        
        assertEquals("return true if dig a bomb", ret, true);
        assertEquals("---- -3-- ----", s, "- - - -\n- 3 - -\n- - - -\n");
    }
    
    @Test
    public void testDigBlank(){
        boolean bombs[][] = {
            {false, false, false, false},
            {false, false, false, true},
            {true, false, true, false}};
        
        Board bb = new Board(4, 3, bombs);
        boolean ret = bb.dig(0, 0);
        String s = bb.getBoard();
        
        assertEquals("return false if no bomb here", ret, false);
        assertEquals("  1- 122- ----", s, "    1 -\n1 2 2 -\n- - - -\n");
    }
}
