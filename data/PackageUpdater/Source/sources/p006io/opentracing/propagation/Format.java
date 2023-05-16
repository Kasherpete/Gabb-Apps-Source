package p006io.opentracing.propagation;

/* renamed from: io.opentracing.propagation.Format */
public interface Format<C> {

    /* renamed from: io.opentracing.propagation.Format$Builtin */
    public static final class Builtin<C> implements Format<C> {
        public static final Format<Binary> BINARY = new Builtin("BINARY");
        public static final Format<BinaryExtract> BINARY_EXTRACT = new Builtin("BINARY_EXTRACT");
        public static final Format<BinaryInject> BINARY_INJECT = new Builtin("BINARY_INJECT");
        public static final Format<TextMap> HTTP_HEADERS = new Builtin("HTTP_HEADERS");
        public static final Format<TextMap> TEXT_MAP = new Builtin("TEXT_MAP");
        public static final Format<TextMapExtract> TEXT_MAP_EXTRACT = new Builtin("TEXT_MAP_EXTRACT");
        public static final Format<TextMapInject> TEXT_MAP_INJECT = new Builtin("TEXT_MAP_INJECT");
        private final String name;

        private Builtin(String str) {
            this.name = str;
        }

        public String toString() {
            return Builtin.class.getSimpleName() + "." + this.name;
        }
    }
}
