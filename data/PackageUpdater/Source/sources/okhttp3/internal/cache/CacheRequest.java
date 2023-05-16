package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okio.Sink;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo20735d2 = {"Lokhttp3/internal/cache/CacheRequest;", "", "abort", "", "body", "Lokio/Sink;", "okhttp"}, mo20736k = 1, mo20737mv = {1, 4, 0})
/* compiled from: CacheRequest.kt */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
