package p006io.opentracing.propagation;

import java.util.Iterator;
import java.util.Map;

/* renamed from: io.opentracing.propagation.TextMapExtract */
public interface TextMapExtract extends Iterable<Map.Entry<String, String>> {
    Iterator<Map.Entry<String, String>> iterator();
}
