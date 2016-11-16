package com.wordsweeper.service.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

/**
 * Created by francisco on 10/4/16.
 */
public class WordTest {
    @Test
    public void constructor() throws Exception {
    	String content = "test";
    	
    	ArrayList<Location> locsArr = new ArrayList<Location>();
    	Location tempLoc = new Location();
    	
    	for(int i=0; i< content.length(); i++){
    		tempLoc.setRow(i);
        	tempLoc.setColumn(i);
        	locsArr.add(tempLoc);
    	}
    	
    	
        Word word = new Word("test", locsArr);
    }

}