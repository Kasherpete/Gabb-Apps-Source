package androidx.work;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016j\u0002\b\n¨\u0006\u000b"}, mo20735d2 = {"Landroidx/work/DirectExecutor;", "", "Ljava/util/concurrent/Executor;", "(Ljava/lang/String;I)V", "execute", "", "command", "Ljava/lang/Runnable;", "toString", "", "INSTANCE", "work-runtime-ktx_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: DirectExecutor.kt */
public enum DirectExecutor implements Executor {
    INSTANCE;

    public String toString() {
        return "DirectExecutor";
    }

    public void execute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "command");
        runnable.run();
    }
}
