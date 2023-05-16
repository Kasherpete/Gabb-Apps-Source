package p006io.opentracing.propagation;

import java.util.Map;

/* renamed from: io.opentracing.propagation.TextMapInjectAdapter */
public class TextMapInjectAdapter implements TextMapInject {
    protected final Map<String, ? super String> map;

    public TextMapInjectAdapter(Map<String, ? super String> map2) {
        this.map = map2;
    }

    public void put(String str, String str2) {
        this.map.put(str, str2);
    }
}
