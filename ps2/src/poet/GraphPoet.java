/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.Map;
import java.util.List;
import javafx.util.Pair;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   AF(graph) = a poem generator implemented by graph.
    // Representation invariant:
    //   
    // Safety from rep exposure:
    //   All fields are private
    //   function poem() returns immutable String.
    
    private Pair<String, Integer> nextWord(String text, int pos){
        while(pos < text.length() && (text.charAt(pos) == ' ' || text.charAt(pos) == '\n')){
            pos++;
        }
        if(pos == text.length()){
            return new Pair<String, Integer>("", pos);
        }
        
        int begin = pos;
        while(pos < text.length() && text.charAt(pos) != ' ' && text.charAt(pos) != '\n'){
            pos++;
        }
        return new Pair<String, Integer>(text.substring(begin, pos), pos);
    }
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        List<String> lines = Files.readAllLines(corpus.toPath());
        String word, pre = "";
        
        for(String line: lines){
            int pos = 0;
            
            while(pos < line.length()){
                Pair<String, Integer> next = nextWord(line, pos);
                word = next.getKey();
                pos = next.getValue();
                if(word.equals(" ")){
                    break;
                }
                if(pre == ""){
                    pre = word;
                    continue;
                }
                
                graph.add(pre);
                graph.add(word);
                int preWeight = graph.set(pre, word, 0);
                graph.set(pre, word, preWeight+1);
                pre = word;
            }
        }
        
//        throw new RuntimeException("not implemented");
    }
    
    private void checkRep(){
        
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        int pos = 0;
        String word, pre = nextWord(input, pos).getKey();
        pos = pre.length();
        String output = pre;
        
        while(pos < input.length()){
            Pair<String, Integer> next = nextWord(input, pos);
            word = next.getKey();
            pos = next.getValue();
            if(word.equals(" ")){
                break;
            }
            
            Map<String, Integer> target = graph.targets(pre.toLowerCase());
            Map<String, Integer> source = graph.sources(word.toLowerCase());
            int maxWeight = 0, nowWeight;
            String bridge = "";
            for(String i: target.keySet()){
                if(!source.containsKey(i)){
                    continue;
                }
                nowWeight = target.get(i)+source.get(i);
                if(maxWeight > nowWeight){
                    continue;
                }
                bridge = i;
                maxWeight = nowWeight;
            }
            
            if(maxWeight > 0){
                output+=" "+bridge+" "+word;
            }
            else{
                output+=" "+word;
            }
            pre = word;
        }
        
        return output;
        
//        throw new RuntimeException("not implemented");
    }
    
    public String toString(){
        return graph.toString();
    }
    
}
