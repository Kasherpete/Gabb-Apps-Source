package p006io.opentracing.propagation;

import java.util.Iterator;
import java.util.Map;

/* renamed from: io.opentracing.propagation.TextMapExtractAdapter */
public class TextMapExtractAdapter implements TextMapExtract {
    protected final Map<String, String> map;

    public TextMapExtractAdapter(Map<String, String> map2) {
        this.map = map2;
    }

    public Iterator<Map.Entry<String, String>> iterator() {
        return this.map.entrySet().iterator();
    }
}
