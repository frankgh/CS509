package com.wordsweeper.service.util;

import java.security.SecureRandom;

/**
 * Created by francisco on 9/20/16.
 */
public class RandomUtil {
    final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static SecureRandom random = new SecureRandom();

    public static char getRandomCharacter() {
        return alphabet.charAt(nextInt(alphabet.length()));
    }

    public static int nextInt(int n) {
        return random.nextInt(n);
    }
}
