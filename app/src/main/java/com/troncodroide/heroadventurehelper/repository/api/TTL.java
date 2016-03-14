package com.troncodroide.heroadventurehelper.repository.api;

public class TTL<T> {

    public static final long TTL_1MINUTE = 60000;
    public static final long TTL_1HOUR = TTL_1MINUTE * 60;
    public static final long TTL_1DAY = TTL_1HOUR * 24;
    public static final long TTL_1MONTH = TTL_1DAY * 30;
    public static final long DEFAULT_TTL = TTL_1MINUTE * 10;

    private long born = System.currentTimeMillis();
    private long ttl;
    private T data;

    public TTL(T data) {
        this(DEFAULT_TTL, data);
    }

    public TTL(long ttl, T data) {
        this.ttl = ttl;
        this.data = data;
    }

    public boolean isAlive() {
        return (data != null) && System.currentTimeMillis() < (born + ttl);
    }

    public T getData() {
        return data;
    }
}
