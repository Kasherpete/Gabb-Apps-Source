package p006io.opentracing.util;

import java.util.Objects;
import java.util.concurrent.Callable;
import p006io.opentracing.Scope;
import p006io.opentracing.ScopeManager;
import p006io.opentracing.Span;
import p006io.opentracing.SpanContext;
import p006io.opentracing.Tracer;
import p006io.opentracing.noop.NoopTracerFactory;
import p006io.opentracing.propagation.Format;

/* renamed from: io.opentracing.util.GlobalTracer */
public final class GlobalTracer implements Tracer {
    private static final GlobalTracer INSTANCE = new GlobalTracer();
    private static volatile boolean isRegistered = false;
    private static volatile Tracer tracer = NoopTracerFactory.create();

    private GlobalTracer() {
    }

    public static Tracer get() {
        return INSTANCE;
    }

    public static boolean isRegistered() {
        return isRegistered;
    }

    public static synchronized boolean registerIfAbsent(Callable<Tracer> callable) {
        synchronized (GlobalTracer.class) {
            requireNonNull(callable, "Cannot register GlobalTracer from provider <null>.");
            if (!isRegistered()) {
                try {
                    Tracer tracer2 = (Tracer) requireNonNull(callable.call(), "Cannot register GlobalTracer <null>.");
                    if (!(tracer2 instanceof GlobalTracer)) {
                        tracer = tracer2;
                        isRegistered = true;
                        return true;
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IllegalStateException("Exception obtaining tracer from provider: " + e2.getMessage(), e2);
                }
            }
            return false;
        }
    }

    public static synchronized boolean registerIfAbsent(final Tracer tracer2) {
        boolean registerIfAbsent;
        synchronized (GlobalTracer.class) {
            requireNonNull(tracer2, "Cannot register GlobalTracer. Tracer is null");
            registerIfAbsent = registerIfAbsent((Callable<Tracer>) new Callable<Tracer>() {
                public Tracer call() {
                    return tracer2;
                }
            });
        }
        return registerIfAbsent;
    }

    @Deprecated
    public static void register(Tracer tracer2) {
        if (!registerIfAbsent(provide(tracer2)) && !tracer2.equals(tracer) && !(tracer2 instanceof GlobalTracer)) {
            throw new IllegalStateException("There is already a current global Tracer registered.");
        }
    }

    public ScopeManager scopeManager() {
        return tracer.scopeManager();
    }

    public Tracer.SpanBuilder buildSpan(String str) {
        return tracer.buildSpan(str);
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C c) {
        tracer.inject(spanContext, format, c);
    }

    public <C> SpanContext extract(Format<C> format, C c) {
        return tracer.extract(format, c);
    }

    public Span activeSpan() {
        return tracer.activeSpan();
    }

    public Scope activateSpan(Span span) {
        return tracer.activateSpan(span);
    }

    public void close() {
        tracer.close();
    }

    public String toString() {
        return "GlobalTracer" + '{' + tracer + '}';
    }

    private static Callable<Tracer> provide(final Tracer tracer2) {
        return new Callable<Tracer>() {
            public Tracer call() {
                return tracer2;
            }
        };
    }

    private static <T> T requireNonNull(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }
}
