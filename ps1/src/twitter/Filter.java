/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.List;
import java.util.*;

import java.io.*;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> tweetsWrittenBy = new ArrayList<>();
        for(Tweet i: tweets){
            if(i.getAuthor().equals(username)){
                tweetsWrittenBy.add(i);
            }
        }
        return tweetsWrittenBy;
		
//        throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
		List<Tweet> tweetsInTimespan = new ArrayList<>();
		for(Tweet i: tweets){
			int c1 = timespan.getStart().compareTo(i.getTimestamp());
			int c2 = timespan.getEnd().compareTo(i.getTimestamp());
			if((c1 == 0 || c1 == -1) && (c2 == 0 || c2 == 1)){
				tweetsInTimespan.add(i);
			}
		}
		return tweetsInTimespan;
		
//        throw new RuntimeException("not implemented");
    }
	
	/**
	 * Check if text contain the wor
	 * 
	 * @param text
	 * 			string text
	 * @param word
	 * 			a word with no spaces
	 * @return return a boolean represent is the word in the text
	 * 		   word should be bounded by space characters
	 */
	private static boolean isContaining(String text, String word){
		text.toLowerCase();
		word.toLowerCase();
        
//        System.out.println(text);
//        System.out.println(word);
        
		for(int pos = text.indexOf(word); pos != -1; pos = text.indexOf(word, pos+word.length())){
            
//            System.out.printf("pos : %d\n",pos);
            
			if(pos != 0 && text.charAt(pos-1) != ' '){
				continue;
			}
			if(pos != text.length() && text.charAt(pos+word.length()) != ' '){
				continue;
			}
			return true;
		}
		return false;
	}

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
		List<Tweet> tweetsContaining = new ArrayList<>();
		for(Tweet i: tweets){
			boolean contains = false;
			for(String s: words){
				if(isContaining(i.getText(),s)){
					contains = true;
					break;
				}
			}
			if(contains){
				tweetsContaining.add(i);
			}
		}
		return tweetsContaining;
		
//        throw new RuntimeException("not implemented");
    }

}

