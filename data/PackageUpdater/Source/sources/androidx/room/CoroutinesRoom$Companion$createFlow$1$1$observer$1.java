package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;

@Metadata(mo20734d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, mo20735d2 = {"androidx/room/CoroutinesRoom$Companion$createFlow$1$1$observer$1", "Landroidx/room/InvalidationTracker$Observer;", "onInvalidated", "", "tables", "", "", "room-ktx_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CoroutinesRoom.kt */
public final class CoroutinesRoom$Companion$createFlow$1$1$observer$1 extends InvalidationTracker.Observer {
    final /* synthetic */ Channel<Unit> $observerChannel;
    final /* synthetic */ String[] $tableNames;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CoroutinesRoom$Companion$createFlow$1$1$observer$1(String[] strArr, Channel<Unit> channel) {
        super(strArr);
        this.$tableNames = strArr;
        this.$observerChannel = channel;
    }

    public void onInvalidated(Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "tables");
        this.$observerChannel.m1643trySendJP2dKIU(Unit.INSTANCE);
    }
}
