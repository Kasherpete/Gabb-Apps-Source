package com.datadog.exec;

import java.util.concurrent.ThreadFactory;

public final class DaemonThreadFactory implements ThreadFactory {
    public static final DaemonThreadFactory TASK_SCHEDULER = new DaemonThreadFactory("dd-task-scheduler");
    public static final DaemonThreadFactory TRACE_PROCESSOR = new DaemonThreadFactory("dd-trace-processor");
    public static final DaemonThreadFactory TRACE_WRITER = new DaemonThreadFactory("dd-trace-writer");
    private final String threadName;

    public DaemonThreadFactory(String str) {
        this.threadName = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.threadName);
        thread.setDaemon(true);
        thread.setContextClassLoader((ClassLoader) null);
        return thread;
    }
}
