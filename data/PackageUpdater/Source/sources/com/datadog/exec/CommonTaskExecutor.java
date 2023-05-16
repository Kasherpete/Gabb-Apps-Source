package com.datadog.exec;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class CommonTaskExecutor extends AbstractExecutorService {
    public static final CommonTaskExecutor INSTANCE = new CommonTaskExecutor();
    private static final long SHUTDOWN_WAIT_SECONDS = 5;
    private final ScheduledExecutorService executorService;

    public interface Task<T> {
        void run(T t);
    }

    private CommonTaskExecutor() {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(DaemonThreadFactory.TASK_SCHEDULER);
        this.executorService = newSingleThreadScheduledExecutor;
        try {
            Runtime.getRuntime().addShutdownHook(new ShutdownCallback(newSingleThreadScheduledExecutor));
        } catch (IllegalStateException unused) {
        }
    }

    public <T> ScheduledFuture<?> scheduleAtFixedRate(Task<T> task, T t, long j, long j2, TimeUnit timeUnit, String str) {
        Task<T> task2 = task;
        T t2 = t;
        if (!INSTANCE.isShutdown()) {
            try {
                PeriodicTask periodicTask = new PeriodicTask(task, t);
                ScheduledFuture<?> scheduleAtFixedRate = this.executorService.scheduleAtFixedRate(new PeriodicTask(task, t), j, j2, timeUnit);
                periodicTask.setFuture(scheduleAtFixedRate);
                return scheduleAtFixedRate;
            } catch (RejectedExecutionException unused) {
            }
        }
        return new UnscheduledFuture(str);
    }

    public void shutdown() {
        this.executorService.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.executorService.shutdownNow();
    }

    public boolean isShutdown() {
        return this.executorService.isShutdown();
    }

    public boolean isTerminated() {
        return this.executorService.isTerminated();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.executorService.awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        this.executorService.execute(runnable);
    }

    private static final class ShutdownCallback extends Thread {
        private final ScheduledExecutorService executorService;

        private ShutdownCallback(ScheduledExecutorService scheduledExecutorService) {
            super("dd-exec-shutdown-hook");
            this.executorService = scheduledExecutorService;
        }

        public void run() {
            this.executorService.shutdown();
            try {
                if (!this.executorService.awaitTermination(CommonTaskExecutor.SHUTDOWN_WAIT_SECONDS, TimeUnit.SECONDS)) {
                    this.executorService.shutdownNow();
                }
            } catch (InterruptedException unused) {
                this.executorService.shutdownNow();
            }
        }
    }

    private static class PeriodicTask<T> implements Runnable {
        private volatile ScheduledFuture<?> future = null;
        private final WeakReference<T> target;
        private final Task<T> task;

        public PeriodicTask(Task<T> task2, T t) {
            this.target = new WeakReference<>(t);
            this.task = task2;
        }

        public void run() {
            Object obj = this.target.get();
            if (obj != null) {
                this.task.run(obj);
            } else if (this.future != null) {
                this.future.cancel(false);
            }
        }

        public void setFuture(ScheduledFuture<?> scheduledFuture) {
            this.future = scheduledFuture;
        }
    }

    private static class UnscheduledFuture implements ScheduledFuture<Object> {
        private final String name;

        public boolean cancel(boolean z) {
            return false;
        }

        public int compareTo(Delayed delayed) {
            return 0;
        }

        public Object get() {
            return null;
        }

        public Object get(long j, TimeUnit timeUnit) {
            return null;
        }

        public long getDelay(TimeUnit timeUnit) {
            return 0;
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return false;
        }

        public UnscheduledFuture(String str) {
            this.name = str;
        }
    }
}
