package com.troncodroide.heroadventurehelper.utils;

public class FlagUtil {
    public static boolean passFilter(int flag, int filter) {
        return (flag & filter) == filter;
    }
}

