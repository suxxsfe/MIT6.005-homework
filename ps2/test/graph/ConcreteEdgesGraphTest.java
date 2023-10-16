/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   
    
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
        correct+="edge: \"abcd\" -> \"1234\" with weight 1234\n";
        correct+="edge: \"1234\" -> \" \" with weight 111\n";
        correct+="edge: \" \" -> \"1234\" with weight 999\n";
        
        assertEquals("wrong toString()", correct, ret);
  
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    @Test
    public void testGetSourceTargetAndWeight(){
        Edge<String> edge = new Edge<>(" ", "abcd", 1234);
        
        assertEquals("expected \" \" for source", " ", edge.getSource());
        assertEquals("expected \"abcd\" for target", "abcd", edge.getTarget());
        assertEquals("expected 1234 for source", 1234, edge.getWeight());
    }
    
}
