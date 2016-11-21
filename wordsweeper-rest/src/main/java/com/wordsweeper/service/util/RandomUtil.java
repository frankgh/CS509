package com.wordsweeper.service.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RandomUtil provides utility methods to generate random
 * characters, random integer numbers, and unique IDs.
 *
 * @author francisco
 */
public class RandomUtil {
    final static String alphabet = "AAABCDEEEFGHIIIJKLMNOOOPQRSTUUUVWXYZ";
    final static SecureRandom random = new SecureRandom();

    /**
     * Get a random alphabet character
     *
     * @return a random character from the alphabet
     */
    public static char getRandomCharacter() {
        return alphabet.charAt(nextInt(alphabet.length()));
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * between 0 (inclusive) and the specified value (exclusive)
     *
     * @param n the bound on the random number to be returned.  Must be
     *          positive.
     * @return the next pseudorandom, uniformly distributed {@code int}
     * value between {@code 0} (inclusive) and {@code n} (exclusive)
     * from this random number generator's sequence
     */
    public static int nextInt(int n) {
        return random.nextInt(n);
    }

    /**
     * Generate a unique id
     *
     * @return the unique id
     */
    public static String nextUniqueId() {
        return new BigInteger(130, random).toString(32);
    }
}
