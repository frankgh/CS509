package com.wordsweeper.service.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * The table of words
 *
 * @author George Heineman
 * @author francisco
 */
public class WordTable {
    /**
     * The Table.
     */
    static Hashtable<String, Boolean> table;

    /**
     * The Letter frequency.
     */
    static LetterFrequency letterFrequency;

    /**
     * Default word table.
     */
    private static final String wordTable = "WordTable.sort";

    /**
     * The constant resourcesFolder.
     */
    private static final String resourcesFolder = "com/wordsweeper/service";

    /**
     * Load up word table. Note that there may be superfluous spaces throughout for formatting
     * reasons, and these are excised before being added to the table.
     *
     * @throws IOException if unable to find file
     */
    public static void loadWordTable() throws IOException {
        table = new Hashtable<>();
        letterFrequency = new LetterFrequency();

        ClassLoader classLoader = WordTable.class.getClassLoader();
        String resourcePath = resourcesFolder.replace("/", getPathDelim()) + getPathDelim() + wordTable;
        resourcePath = classLoader.getResource(resourcePath).getFile();
        resourcePath = URLDecoder.decode(resourcePath, "UTF-8");

        Iterator<String> it = new StringFileIterator(new File(resourcePath));
        while (it.hasNext()) {
            String word = it.next();
            word = word.trim();
            table.put(word, Boolean.TRUE);
            letterFrequency.feedWord(word);
        }

        letterFrequency.build();
    }

    /**
     * Converts word to lowercase and checks whether exists within table.
     *
     * @param s the s
     * @return <code>true</code> if a word in the table; <code>false</code> otherwise.
     */
    public static boolean isWord(String s) {
        if (!ensureInitialized()) {
            return false;
        }

        s = s.toLowerCase();
        return table.containsKey(s);
    }

    /**
     * Ensure table is initialized
     *
     * @return true if initialized, false otherwise
     */
    private static boolean ensureInitialized() {
        if (table == null) {
            try {
                synchronized (WordTable.class) {
                    if (table == null) {
                        loadWordTable();
                    }
                }
            } catch (IOException ioe) {
                System.err.println("Word Table Not Yet Initialized!");
                return false;
            }
        }
        return true;
    }

    /**
     * Return the letter by the frequency range
     *
     * @param value the value between 0 and 1
     * @return the character in the frequency
     */
    public static char getLetterByFrequency(double value) {
        ensureInitialized();
        return letterFrequency.getCharacter(value);
    }

    /**
     * Gets the correct delimiter
     *
     * @return the path delim
     */
    private static String getPathDelim() {
        return System.getProperty("file.separator", "/");
    }
}
