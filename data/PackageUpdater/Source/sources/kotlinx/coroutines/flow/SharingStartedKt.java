package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.time.Duration;
import kotlinx.coroutines.flow.SharingStarted;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, mo20735d2 = {"WhileSubscribed", "Lkotlinx/coroutines/flow/SharingStarted;", "Lkotlinx/coroutines/flow/SharingStarted$Companion;", "stopTimeout", "Lkotlin/time/Duration;", "replayExpiration", "WhileSubscribed-5qebJ5I", "(Lkotlinx/coroutines/flow/SharingStarted$Companion;JJ)Lkotlinx/coroutines/flow/SharingStarted;", "kotlinx-coroutines-core"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SharingStarted.kt */
public final class SharingStartedKt {
    /* renamed from: WhileSubscribed-5qebJ5I$default  reason: not valid java name */
    public static /* synthetic */ SharingStarted m1655WhileSubscribed5qebJ5I$default(SharingStarted.Companion companion, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Duration.Companion.m1564getZEROUwyO8pc();
        }
        if ((i & 2) != 0) {
            j2 = Duration.Companion.m1562getINFINITEUwyO8pc();
        }
        return m1654WhileSubscribed5qebJ5I(companion, j, j2);
    }

    /* renamed from: WhileSubscribed-5qebJ5I  reason: not valid java name */
    public static final SharingStarted m1654WhileSubscribed5qebJ5I(SharingStarted.Companion companion, long j, long j2) {
        return new StartedWhileSubscribed(Duration.m1480getInWholeMillisecondsimpl(j), Duration.m1480getInWholeMillisecondsimpl(j2));
    }
}
