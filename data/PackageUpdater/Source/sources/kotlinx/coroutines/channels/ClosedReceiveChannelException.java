package kotlinx.coroutines.channels;

import java.util.NoSuchElementException;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo20735d2 = {"Lkotlinx/coroutines/channels/ClosedReceiveChannelException;", "Ljava/util/NoSuchElementException;", "Lkotlin/NoSuchElementException;", "message", "", "(Ljava/lang/String;)V", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Channel.kt */
public final class ClosedReceiveChannelException extends NoSuchElementException {
    public ClosedReceiveChannelException(String str) {
        super(str);
    }
}
