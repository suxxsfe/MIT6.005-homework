/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = 
    // Representation invariant:
    //   all elements in vertices are different
    // Safety from rep exposure:
    //   All fields are private;
    //   vertices is mutable List, but no function will use it as the return result;
    
    public ConcreteVerticesGraph(){
        
    }
    
    private void checkRep(){
        Set<String> vertex = new HashSet<>();
        
        for(Vertex i: vertices){
            assert vertex.contains(i.getName()) == false;
            vertex.add(i.getName());
        }
    }
    
    @Override public boolean add(String vertex) {
        for(Vertex i: vertices){
            if(i.getName().equals(vertex)){
                return false;
            }
        }
        
        vertices.add(new Vertex(vertex));
        return true;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(String source, String target, int weight) {
        for(Vertex i: vertices){
            if(i.getName().equals(source)){
                return i.set(target, weight);
            }
        }
        
        return 0;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(String vertex) {
        for(Vertex i: vertices){
            if(i.getName().equals(vertex)){
                vertices.remove(i);
                return true;
            }
        }
        
        return false;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<String> vertices() {
        Set<String> ret = new HashSet<>();
        
        for(Vertex i: vertices){
            ret.add(i.getName());
        }
        
        return ret;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> ret = new HashMap<>();
        
        for(Vertex i: vertices){
            if(i.getTargets().containsKey(target)){
                ret.put(i.getName(), i.getTargets().get(target));
            }
        }
        
        return ret;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> targets(String source) {
        for(Vertex i: vertices){
            if(i.getName().equals(source)){
                return i.getTargets();
            }
        }
        
        return new HashMap<String, Integer>();
    
//        throw new RuntimeException("not implemented");
    }
    
    /**
     * Give a human-readable representation of the graph.
     * 
     * @return a String, representing the graph
     */
    public String toString(){
        String ret = "";
        ret+="Weighted directed graph with "+vertices.size()+" vertices\n";
        for(Vertex i: vertices){
            ret+=i.toString()+"\n";
        }
        
        return ret;
    }
    
}

/**
 * A vertex with label in a directed graph.
 * Vertex have a label of String
 * Contains its label, and all vertices which have a edge from this vertex.
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    private String name;
    private final Map<String, Integer> targets = new HashMap<>();
    
    // Abstraction function:
    //   AF(name, targets) = a vertex in a graph, with a label name,
    //                      hava edges to every keys in targets, with weight of values in targets
    // Representation invariant:
    //   no values in targets euqal to 0
    // Safety from rep exposure:
    //   All fields are private;
    //   name in String is immutable;
    //   targets is mutable Map, so getTargets() makes defensive copies to avoid sharing rep's Map object;

    public Vertex(){
        
    }
    public Vertex(String vertex){
        name = vertex;
    }
    
    private void checkRep(){
        for(String target: targets.keySet()){
            assert targets.get(target) != 0;
        }
    }
    
    /**
     * Add, change or remove a weighted edge from this to vertex.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * If weight is zero, remove the edge if it exists.
     * 
     * @param vertex label of the target vertex
     * @param weight weight of the edge
     * @return the previous weight of the edge, or zero if there was no such edge
     */
    public int set(String vertex, int weight){
        int ret = 0;
        
        if(targets.containsKey(vertex)){
            ret = targets.get(vertex);
        }
        if(weight != 0){
            targets.put(vertex, weight);
        }
        else if(ret != 0){
            targets.remove(vertex);
        }
        
        return ret;
    }
    
    /**
     * Get the targets vertices with a directed edge from this vertex
     * 
     * @return a map where the key set is the set of labels of the vertices,
     *         and the value of each key is the weight of the edge
     */
    public Map<String, Integer> getTargets(){
        return new HashMap<String, Integer>(targets);
    }
    
    /**
     * Get the name of this vertex
     * 
     * @return a String, the name of this vertex
     */
    public String getName(){
        return name;
    }
    
    /**
     * Give a human-readable representation of the vertex.
     * 
     * @return a String, representing the vertex
     */
    public String toString(){
        String ret = "";
        ret+="Vertex \""+name+"\":\n";
        for(String target: targets.keySet()){
            ret+="\""+name+"\" -> \""+target+"\"   with weight "+targets.get(target)+"\n";
        }
        
        return ret;
    }
    
}
