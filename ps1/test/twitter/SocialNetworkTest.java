/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.Instant;

import java.util.HashSet;
import java.util.Arrays;

//import java.io.*;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
 
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?   ", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype @alyssa", d2);
    private static final Tweet tweet3 = new Tweet(3, "cccc", "rivest talk in 30 minutes #hype @bbitdiddle ", d2);
    private static final Tweet tweet4 = new Tweet(4, "alyssa", "rivest talk in 30 minutes #hype asdfsf@cccccccccccc", d2);
    private static final Tweet tweet5 = new Tweet(5, "bbitdiddle", "rivest talk in 30 minutes #hype @cccc", d2);
    private static final Tweet tweet6 = new Tweet(6, "dabc", "rivest talk in 30 minutes #hype @cccc @alyssa @bbitdiddle", d2);
    private static final Tweet tweet7 = new Tweet(7, "cccc", "rivest talk in 30 minutes #hype @alyssa", d2);
    
    private static void makeFollowsGraphCorrect(Map<String, Set<String>> followsGraphCorrect){
        followsGraphCorrect.put("alyssa", new HashSet<String>(){
        });
        followsGraphCorrect.put("bbitdiddle", new HashSet<String>(){
            {
                add("alyssa");
                add("cccc");
            }
        });
        followsGraphCorrect.put("cccc", new HashSet<String>(){
            {
                add("bbitdiddle");
                add("alyssa");
            }
        });
        followsGraphCorrect.put("dabc", new HashSet<String>(){
            {
                add("alyssa");
                add("bbitdiddle");
                add("cccc");
            }
        });
    }
    
    @Test
    public void testGuessFollowsGraph(){
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7));
        
        Map<String, Set<String>> followsGraphCorrect = new HashMap<>();
        makeFollowsGraphCorrect(followsGraphCorrect);
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(followsGraph);
        
        assertTrue("wrong follow Graph", followsGraphCorrect.equals(followsGraph));
    }
    
    @Test
    public void testInfluencers(){
        Map<String, Set<String>> followsGraph = new HashMap<>();
        makeFollowsGraphCorrect(followsGraph);
        List<String> influencers = SocialNetwork.influencers(followsGraph);
       
        List<String> influencers = new ArrayList<>();

    }
 
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
