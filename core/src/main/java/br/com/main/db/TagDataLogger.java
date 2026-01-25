package br.com.main.db;

public class TagDataLogger {

    public static void logIntIfChanged(int tagId, int value) {
        if (LastValueCache.intChanged(tagId, value)) {
            JdbcLogger.logInt(tagId, value);
        }
    }

    public static void logBoolIfChanged(int tagId, boolean value) {
        if (LastValueCache.boolChanged(tagId, value)) {
            JdbcLogger.logBool(tagId, value);
        }
    }
}
