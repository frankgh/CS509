package com.wordsweeper.service.util;

import com.wordsweeper.service.model.Location;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods
 *
 * @author francisco
 */
public class Util {

    /**
     * Parse a list of pipe-separated cell positions. Where a position is
     * a comma-separated x and y value
     *
     * @param cellPositions the pipe separated positions
     * @return the list of positions if valid, null or empty when null or empty
     */
    public static List<Location> parseCellPositions(String cellPositions) {
        if (cellPositions == null) {
            return null;
        }

        if (StringUtils.isBlank(cellPositions)) {
            return new ArrayList<>();
        }

        String[] cellPositionSplit = cellPositions.split("|");

        if (cellPositionSplit.length == 0) {
            return new ArrayList<>();
        }

        List<Location> locationList = new ArrayList<>();

        for (String cellPosition : cellPositionSplit) {
            if (cellPosition == null) continue;

            String[] loc = cellPosition.split(",");
            if (loc.length != 2) continue;

            Integer row = parseInteger(loc[0]);
            Integer column = parseInteger(loc[1]);

            if (row != null && column != null) {
                locationList.add(new Location(row, column));
            }
        }

        return locationList;
    }

    /**
     * Parse string into an Integer
     *
     * @param s The value where parsing is being performed on.
     * @return The parsed Integer if valid or null if invalid.
     */
    private static Integer parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
