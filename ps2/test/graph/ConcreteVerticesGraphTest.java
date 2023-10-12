/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    @Test
    public void testToString(){
        Graph<String> graph =emptyInstance();
        
        graph.add("abcd");
        graph.add("1234");
        graph.add(" ");
        graph.set("abcd", "1234", 1234);
        graph.set("1234", " ", 111);
        graph.set(" ", "1234", 999);
        
        String ret = graph.toString();
        
        String correct = "Weighted directed graph with 3 vertices\n";
        correct+="Vertex \"abcd\":\n";
        correct+="\"abcd\" -> \"1234\"   with weight 1234\n";
        correct+="Vertex \"1234\":\n";
        correct+="\"1234\" -> \" \"   with weight 111\n";
        correct+="Vertex \" \":\n";
        correct+="\" \" -> \"1234\"   with weight 999\n";
        
        assertEquals("wrong toString()", correct, ret);
    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    @Test
    public void testGetName(){
        Vertex vertex = new Vertex("abcd");
        
        assertEquals("expected name abcd", "abcd", vertex.getName());
    }
    
    @Test
    public void testSet(){
        Vertex vertex = new Vertex("abcd");
        int ret;
        
        ret = vertex.set("1234", 123);
        assertEquals("expected 0 for create a new edge", 0, ret);
        
        ret = vertex.set("1234", 321);
        assertEquals("expected 123, the previous weight of the edge", 123, ret);
        
        vertex.set("1234", 0);
        ret = vertex.set("1234", 1);
        assertEquals("expected 0 for create a new edge(the edge has been removed before)", 0, ret);
    }
    
    @Test
    public void testGetTargets(){
        Vertex vertex = new Vertex("abcd");
        Map<String, Integer> map = new HashMap<>();
        
        vertex.set("1234", 123);
        vertex.set(" ", 321);
        
        map = vertex.getTargets();
        assertEquals("expected to have target \"1234\"(weight 123) and \" \"(weight 321)", new HashMap<String, Integer>(){
            {
                put("1234", 123);
                put(" ", 321);
            }
        }, map);
    }
    // TODO tests for operations of Vertex
    
}
