/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices, edges) = a graph with vertices with label in L,
    //                          and edges in List edges.
    // Representation invariant:
    //   Elements in List edges are unique, and two vertices of each edge must be contained in Set vertices.
    // Safety from rep exposure:
    //   All fields are private;
    //   edges is mutable List, but no function will use it as the return result;
    //   vertices is mutable Set, so vertices() makes defensive copies.
    
    public ConcreteEdgesGraph(){
        
    }
    
    private void checkRep(){
        Set<Edge<L>> set = new HashSet<>();
        for(Edge<L> i: edges){
            assert !set.contains(i);
            set.add(i);
            assert vertices.contains(i.getSource());
            assert vertices.contains(i.getTarget());
        }
    }
    
    @Override public boolean add(L vertex) {
        if(vertices.contains(vertex)){
            return false;
        }
        vertices.add(vertex);
        return true;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
        if(!vertices.contains(source) || !vertices.contains(target)){
            return 0;
        }
        
        int ret = 0;
        for(Edge<L> i: edges){
            if(i.getSource().equals(source) && i.getTarget().equals(target)){
                ret = i.getWeight();
                edges.remove(i);
                break;
            }
        }
        
        if(weight != 0){
            edges.add(new Edge<L>(source, target, weight));
        }
        return ret;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
        if(vertices.contains(vertex)){
            vertices.remove(vertex);
            return true;
        }
        return false;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<L>(vertices);
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L, Integer> source = new HashMap<>();
        
        for(Edge<L> i: edges){
            if(i.getTarget().equals(target)){
                source.put(i.getSource(), i.getWeight());
            }
        }
        return source;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L, Integer> target = new HashMap<>();
        
        for(Edge<L> i: edges){
            if(i.getSource().equals(source)){
                target.put(i.getTarget(), i.getWeight());
            }
        }
        return target;
        
//        throw new RuntimeException("not implemented");
    }
    
    /**
     * Give a human-readable representation of the graph.
     * 
     * @return a L, representing the graph
     */
    @Override
    public String toString(){
        String graph = "";
        graph+="Weighted directed graph with "+vertices.size()+" vertices\n";
        for(Edge<L> i: edges){
            graph+=i.toString();
        }
        return graph;
    }
    
}

/**
 * A directed weighted edge bewteen two vertices with label in L.
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    private L source, target;
    private int weight;
    
    // Abstraction function:
    //   AF(u,v) = a edge between two vertices, which have label in L u and v
    // Representation invariant:
    //   u != v
    //   weight != 0
    // Safety from rep exposure:
    //   All fields are private;
    //   source and target are immutable and private,
    
    public Edge(){
        
    }
    public Edge(L _source, L _target, int _weight){
        source=_source;
        target=_target;
        weight=_weight;
    }
    
    private void checkRep(){
        assert !source.equals(target);
        assert weight != 0;
    }
    
    /**
     * Get source of the edge
     * 
     * @return a L, label of source
     */
    public L getSource(){
        return source;
    }
    
    /**
     * Get target of the edge
     * 
     * @return a L, label of target
     */
    public L getTarget(){
        return target;
    }
    
    /**
     * Get weight of the edge
     * 
     * @return int, weight of the edge
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * Get a human-readable representation of the edge
     * 
     * @return a L, representing the edge
     */
    public String toString(){
        return "edge: \""+source+"\" -> \""+target+"\" with weight "+weight+"\n";
    }
    
}
