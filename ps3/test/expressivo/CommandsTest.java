/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testDifferentiate(){
        //numbers
        assertEquals("expected (1)' = 0", Commands.differentiate("1", "variable"), "0");
        
        //variables
        assertEquals("expected (abcd)_{abcd}' = 1", Commands.differentiate("abcd", "abcd"), "1");
        assertEquals("expected (abcd)_{abc}' = 0", Commands.differentiate("abcd", "abc"), "0");
        
        //+
        assertEquals("expected (1 + abcd)_{abcd}' = ( 0 + 1 )", Commands.differentiate("1+abcd", "abcd"), "( 0 + 1 )");
        
        //*
        assertEquals("expected (abcd * abc)_{abcd}' = 0", Commands.differentiate("abcd*abc", "abcd"), "( 1 * abc + abcd * 0 )");
    }
    
    @Test
    public void testSimplify(){
        Map<String, Double> environment = new HashMap<String, Double>();
        
        environment.put("x", 2.0);
        assertEquals("x*x*x with x=2 should be simplified to 8", "8", Commands.simplify("x*x*x", environment));
        
        environment.put("x", 0.0);
        assertEquals("x*y*y+0+0+0*zzz+0*12345 with x=0 should be simplified to 0", "0", Commands.simplify("x*y*y+0+0+0*zzz+0*12345", environment));
        
        environment.put("x", 1.0);
        assertEquals("x*y*1+0 with x=1 should be simplified to y", "y", Commands.simplify("x*y*1+0", environment));
    }
    
}
