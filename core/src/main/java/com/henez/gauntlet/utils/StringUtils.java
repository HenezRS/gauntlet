package com.henez.gauntlet.utils;

import org.apache.commons.text.WordUtils;

public class StringUtils {
    private static final String punctuation = ".?!,:;";
    private StringUtils() {

    }

    public static String toHex(int value) {
        String string = Integer.toHexString(value);
        return string.length() == 1 ? "0" + string : string;
    }

    public static int fromHex(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String decimal2(float f) {
        return String.format("%.2f", f);
    }

    public static String capitalise(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String punctuateEnd(String string) {
        char c = string.charAt(string.length()-1);
        return punctuation.indexOf(c)>-1 ? string : string+".";
    }

    public static String formatSentence(String string) {
        String s = capitalise(string);
        return punctuateEnd(s);
    }

    public static String formatTitle(String string) {
        return WordUtils.capitalizeFully(string);
    }

    public static String formatListOption(String string) {
        return formatTitle(string);
    }
}
