package p006io.opentracing.propagation;

import java.nio.ByteBuffer;
import java.util.Objects;

/* renamed from: io.opentracing.propagation.BinaryAdapters */
public final class BinaryAdapters {
    private BinaryAdapters() {
    }

    public static BinaryExtract extractionCarrier(ByteBuffer byteBuffer) {
        Objects.requireNonNull(byteBuffer);
        return new BinaryExtractAdapter(byteBuffer);
    }

    public static BinaryInject injectionCarrier(ByteBuffer byteBuffer) {
        return new BinaryInjectAdapter(byteBuffer);
    }

    /* renamed from: io.opentracing.propagation.BinaryAdapters$BinaryExtractAdapter */
    static class BinaryExtractAdapter implements BinaryExtract {
        ByteBuffer buffer;

        public BinaryExtractAdapter(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
        }

        public ByteBuffer extractionBuffer() {
            return this.buffer;
        }
    }

    /* renamed from: io.opentracing.propagation.BinaryAdapters$BinaryInjectAdapter */
    static class BinaryInjectAdapter implements BinaryInject {
        ByteBuffer buffer;

        public BinaryInjectAdapter(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
        }

        public ByteBuffer injectionBuffer(int i) {
            if (i < 1) {
                throw new IllegalArgumentException("length needs to be larger than 0");
            } else if (i <= this.buffer.remaining()) {
                ByteBuffer byteBuffer = this.buffer;
                byteBuffer.limit(byteBuffer.position() + i);
                return this.buffer;
            } else {
                throw new AssertionError("length is larger than the backing ByteBuffer remaining length");
            }
        }
    }
}
