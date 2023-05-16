package com.datadog.trace.context;

import java.io.Closeable;

public interface TraceScope extends Closeable {

    public interface Continuation {
        TraceScope activate();

        void close();

        void close(boolean z);
    }

    Continuation capture();

    void close();

    boolean isAsyncPropagating();

    void setAsyncPropagation(boolean z);
}
