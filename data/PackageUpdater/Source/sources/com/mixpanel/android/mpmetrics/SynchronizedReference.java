package com.mixpanel.android.mpmetrics;

class SynchronizedReference<T> {
    private T mContents = null;

    public synchronized void set(T t) {
        this.mContents = t;
    }

    public synchronized T getAndClear() {
        T t;
        t = this.mContents;
        this.mContents = null;
        return t;
    }

    public synchronized T get() {
        return this.mContents;
    }
}
