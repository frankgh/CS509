package com.wordsweeper.service.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class RandomUtilTest {
	
	@Test
	public void constructor() throws Exception {
		RandomUtil rUtil = new RandomUtil();
        assertNotNull(rUtil.random.getSeed(20));
        
        char rand1, rand2, rand3;
        rand1 = rUtil.getRandomCharacter();
        
        rUtil = new RandomUtil();
        rand2 = rUtil.getRandomCharacter();
        
        rUtil = new RandomUtil();
        rand3 = rUtil.getRandomCharacter();
        assertFalse(rand1 == rand2 && rand2 == rand3);
        
        int num1, num2, num3;
        num1 = rUtil.nextInt(1000);
        num2 = rUtil.nextInt(1000);
        num3 = rUtil.nextInt(1000);
        
        assertFalse(num1 > 1000 || num2 > 1000 || num3 > 1000);
        assertFalse(num1 == num2 && num2 == num3);
        
        String id1, id2;
        id1 = rUtil.nextUniqueId();
        assertFalse(id1.length() > 32);

        id2 = rUtil.nextUniqueId();
        assertFalse(id1.equals(id2));
	}
	
	

}