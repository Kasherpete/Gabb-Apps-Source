package p006io.opentracing.propagation;

import java.nio.ByteBuffer;

/* renamed from: io.opentracing.propagation.BinaryInject */
public interface BinaryInject {
    ByteBuffer injectionBuffer(int i);
}
