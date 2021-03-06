package com.norconex.collector.http.delay.impl;

public class ThreadDelay extends AbstractDelay {

    private static final long serialVersionUID = -8757454203476804459L;

    private ThreadLocal<Long> threadLastHitNanos;
    
    public void delay(long expectedDelayNanos, String url) {
        if (threadLastHitNanos == null) {
            threadLastHitNanos = new ThreadLocal<Long>();
            threadLastHitNanos.set(System.nanoTime());
        }
        long lastHitNanos = threadLastHitNanos.get();
        delay(expectedDelayNanos, lastHitNanos);
        threadLastHitNanos.set(System.nanoTime());
    }
}
