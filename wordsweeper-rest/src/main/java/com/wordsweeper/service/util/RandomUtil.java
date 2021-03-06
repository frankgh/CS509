package com.wordsweeper.service.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * RandomUtil provides utility methods to generate random
 * characters, random integer numbers, and unique IDs.
 *
 * @author francisco
 */
public class RandomUtil {
    /**
     * The constant random.
     */
    final static SecureRandom random;

    /**
     * Initialize the SecureRandom Generator
     */
    static {
        SecureRandom sha1Random;
        try {
            SecureRandom nativeRandom = SecureRandom.getInstance("NativePRNGNonBlocking"); // assuming Unix
            byte[] seed = nativeRandom.generateSeed(55); // NIST SP800-90A suggests 440 bits for SHA1 seed
            sha1Random = SecureRandom.getInstance("SHA1PRNG");
            sha1Random.setSeed(seed);
            byte[] values = new byte[20];
            sha1Random.nextBytes(values); // SHA1PRNG, seeded properly
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
            sha1Random = new SecureRandom();
        }

        random = sha1Random;
    }

    /**
     * Get a random alphabet character
     *
     * @return a random character from the alphabet
     */
    public static char getRandomCharacter() {
        return WordTable.getLetterByFrequency(random.nextDouble());
    }

    /**
     * Returns a pseudorandom, uniformly distributed {@code int} value
     * between 0 (inclusive) and the specified value (exclusive)
     *
     * @param n the bound on the random number to be returned.  Must be          positive.
     * @return the next pseudorandom, uniformly distributed {@code int} value between {@code 0} (inclusive) and {@code n} (exclusive) from this random number generator's sequence
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
