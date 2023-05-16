package androidx.room.util;

import android.util.Log;
import com.datadog.android.core.internal.CoreFeature;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static StringBuilder newStringBuilder() {
        return new StringBuilder();
    }

    public static void appendPlaceholders(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(CoreFeature.DEFAULT_APP_VERSION);
            if (i2 < i - 1) {
                sb.append(WebViewLogEventConsumer.DDTAGS_SEPARATOR);
            }
        }
    }

    public static List<Integer> splitToIntList(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, WebViewLogEventConsumer.DDTAGS_SEPARATOR);
        while (stringTokenizer.hasMoreElements()) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
            } catch (NumberFormatException e) {
                Log.e("ROOM", "Malformed integer list", e);
            }
        }
        return arrayList;
    }

    public static String joinIntoString(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        if (size == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(Integer.toString(list.get(i).intValue()));
            if (i < size - 1) {
                sb.append(WebViewLogEventConsumer.DDTAGS_SEPARATOR);
            }
        }
        return sb.toString();
    }

    private StringUtil() {
    }
}
