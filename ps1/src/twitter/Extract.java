/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.*;
import java.lang.*;
import java.time.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        Instant minTime = Instant.now();
        Instant maxTime = Instant.now();
        for(Tweet i: tweets){
            if(minTime.compareTo(i.getTimestamp()) == 1){
                minTime = i.getTimestamp();
            }
            if(maxTime.compareTo(i.getTimestamp()) == -1){
                maxTime = i.getTimestamp();
            }
        }
        return new Timespan(minTime, maxTime);

//        throw new RuntimeException("not implemented");
    }

    /**
     * Check if the character is valid in a Twitter username
     *
     * @param ch
     *            character to check
     * @return return a boolean show if the character is valid
     */
    private static boolean isValidUsernameCharacter(char ch){
        if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
            return true;
        }
        if(ch >= '0' && ch <= '9'){
            return true;
        }
        if(ch == '-' || ch == '_'){
            return true;
        }
        return false;
    }

    
    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mention = new TreeSet<>();
        for(Tweet i: tweets){
            String text = i.getText();
            for(Integer pos = 0; pos < text.length(); pos++){
                if(text.charAt(pos) != '@'){
                    continue;
                }
                if((pos == 0 || !isValidUsernameCharacter(text.charAt(pos-1)))
                    && (pos == text.length()-1 || !isValidUsernameCharacter(text.charAt(pos+1)))){
                    continue;
                }

                int _pos=pos+1;
                while(_pos < text.length() && isValidUsernameCharacter(text.charAt(_pos))){
                    _pos++;
                }
                if(_pos == pos+1){
                    continue;
                }
                mention.add(text.substring(pos+1, _pos));
            }
        }
        return mention;

//        throw new RuntimeException("not implemented");
    }

}
