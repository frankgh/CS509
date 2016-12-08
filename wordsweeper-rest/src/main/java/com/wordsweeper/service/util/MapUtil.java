package com.wordsweeper.service.util;

import java.util.*;

/**
 * Utilities for Maps
 *
 * @author francisco
 */
class MapUtil {

    static int ASCENDING = 1;
    static int DESCENDING = -1;

    /**
     * Sort a map by its values in ascending order
     *
     * @param map the map
     * @param <K> the key type
     * @param <V> the value type
     * @return the sorted map in ascending order
     */
    static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return sortByValue(map, ASCENDING);
    }

    /**
     * Sort a map by its values in the specified order
     *
     * @param map   the map
     * @param order the order
     * @param <K>   the key type
     * @param <V>   the value type
     * @return the sorted map in the specified order
     */
    static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, final int order) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue()) * order;
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Sum all the values in a map
     *
     * @param map the map
     * @param <K> the key type
     * @return the sum of all the values in the map
     */
    static <K, Double> double sumValues(Map<K, java.lang.Double> map) {
        double total = 0;

        for (java.lang.Double value : map.values()) {
            total += value.doubleValue();
        }

        return total;
    }
}
