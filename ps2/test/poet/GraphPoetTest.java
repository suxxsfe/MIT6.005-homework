/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGraphPoet() throws IOException {
        final GraphPoet nimoy = new GraphPoet(new File("corpus.txt"));
        final String input = "Seek to explore new and exciting synergies!";
        final String output = "Seek to explore strange new life and exciting synergies!";
        
        assertEquals("Wrong poem", output, nimoy.poem(input));
    }
    
}
