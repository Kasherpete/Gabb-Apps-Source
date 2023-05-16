package kotlinx.coroutines.debug.internal;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, mo20735d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-12$$inlined$sortedBy$1 */
/* compiled from: Comparisons.kt */
public final class C1359x8808a3e8<T> implements Comparator {
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
    }
}
