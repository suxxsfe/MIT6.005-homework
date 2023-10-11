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
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    public ConcreteVerticesGraph(){
        
    }
    
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getName().equals(vertex)){
                return false;
            }
        }
        
        vertices.add(new Vertex(vertex));
        return true;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public int set(String source, String target, int weight) {
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getName().equals(source)){
                return vertices.get(i).set(target, weight);
            }
        }
        
        return 0;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(String vertex) {
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getName().equals(vertex)){
                vertices.remove(i);
                return true;
            }
        }
        
        return false;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Set<String> vertices() {
        Set<String> ret = new HashSet<>();
        
        for(int i = 0; i < vertices.size(); i++){
            ret.add(vertices.get(i).getName());
        }
        
        return ret;
        
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> ret = new HashMap<>();
        
        for(int i = 0;i < vertices.size(); i++){
            Vertex v = vertices.get(i);
            if(v.getTargets().containsKey(target)){
                ret.put(v.getName(), v.getTargets().get(target));
            }
        }
        
        return ret;
    
//        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<String, Integer> targets(String source) {
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).getName().equals(source)){
                return vertices.get(i).getTargets();
            }
        }
        
        return new HashMap<String, Integer>();
    
//        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    
}

/**
 * TODO specification
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
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    public Vertex(){
        
    }
    public Vertex(String vertex){
        name = vertex;
    }
    
    // TODO checkRep
    
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
    
    public Map<String, Integer> getTargets(){
        return targets;
    }
    
    public String getName(){
        return name;
    }
    
    // TODO toString()
    
}
