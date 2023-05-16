package p006io.opentracing.propagation;

import java.util.Map;

/* renamed from: io.opentracing.propagation.TextMapAdapter */
public class TextMapAdapter extends TextMapExtractAdapter implements TextMap {
    public TextMapAdapter(Map<String, String> map) {
        super(map);
    }

    public void put(String str, String str2) {
        this.map.put(str, str2);
    }
}
