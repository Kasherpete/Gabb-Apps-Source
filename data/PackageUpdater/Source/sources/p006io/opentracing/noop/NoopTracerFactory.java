package p006io.opentracing.noop;

/* renamed from: io.opentracing.noop.NoopTracerFactory */
public final class NoopTracerFactory {
    public static NoopTracer create() {
        return NoopTracerImpl.INSTANCE;
    }

    private NoopTracerFactory() {
    }
}
