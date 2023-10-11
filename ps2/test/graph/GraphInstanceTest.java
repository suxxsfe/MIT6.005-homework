/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    @Test
    public void testAddAndRemove(){
        Graph<String> graph = emptyInstance();
        Set<String> set = Collections.emptySet();
        
        graph.add("abcd");
        graph.add("1234");
        graph.add(" ");
        set.add("abcd");
        set.add("1234");
        set.add(" ");
        assertEquals("expected graph to have \"abcd\", \"1234\", and \" \"", set, graph);
        
        graph.remove("abcd");
        set.remove("abcd");
        assertEquals("expected graph to have \"1234\", and \" \"", set, graph);
        
        graph.remove("1234");
        graph.remove(" ");
        assertEquals("expected graph to have no vertices", Collections.emptySet(), graph);
    }
    
    @Test
    public void testSet(){
        Graph<String> graph = emptyInstance();
        int ret;
        
        graph.add("abcd");
        graph.add(" ");
        graph.add("1234");
        ret = graph.set("abcd", " ", 1);
        assertEquals("expected 0 for create a new edge", 0, ret);
        
        graph.set("abcd", " ", 2);
        assertEquals("expected 1, the previous weight of the edge", 1, ret);
        
        graph.set("1234", "abcd", 1234);
        assertEquals("expected 0 for create a new edge", 0, ret);
        
        graph.set("1234", "abcd", 0);
        assertEquals("expected 1234, the previous weight of the edge", 0, ret);
        
        graph.set("1234", "abcd", 1234);
        assertEquals("expected 0 for create a new edge(the edge has been removed before)", 0, ret);
    }
    
    @Test
    public void testSourcesAndgraph(){
        Graph<String> graph = emptyInstance();
        Map<String, Integer> map = Collections.emptyMap();
        
        graph.add("abcd");
        graph.add(" ");
        graph.add("1234");
        graph.set("abcd", "1234", 1);
        graph.set(" ", "1234", 1234);
        graph.set("1234", " ", 321);
        
        map = graph.sources("1234");
        assertEquals("expected to hava sources \"abcd\" and \" \"", new HashMap<String, Integer>(){
            {
                put(" ", 1234);
                put("abcd", 1);
            }
        }, map);
        
        map = graph.targets(" ");
        assertEquals("expected to have targets \"1234\"", new HashMap<String, Integer>(){
            {
                put("1234", 321);
            }
        }, map);
        
        graph.set("abcd", "1234", 0);
        
        map = graph.sources("1234");
        assertEquals("expected to hava sources \" \"", new HashMap<String, Integer>(){
            {
                put(" ", 1234);
            }
        }, map);
        
        map = graph.targets("abcd");
        assertEquals("expected to have no targets", Collections.emptyMap(), map);
    }
    
}
