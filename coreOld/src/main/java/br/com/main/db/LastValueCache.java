package br.com.main.db;

import java.util.HashMap;
import java.util.Map;

public class LastValueCache {

    private static final Map<Integer, Integer> LAST_INT = new HashMap<>();
    private static final Map<Integer, Boolean> LAST_BOOL = new HashMap<>();

    public static boolean intChanged(int tagId, int value) {
        Integer last = LAST_INT.get(tagId);
        if (last == null || last != value) {
            LAST_INT.put(tagId, value);
            return true;
        }
        return false;
    }

    public static boolean boolChanged(int tagId, boolean value) {
        Boolean last = LAST_BOOL.get(tagId);
        if (last == null || last != value) {
            LAST_BOOL.put(tagId, value);
            return true;
        }
        return false;
    }
}
