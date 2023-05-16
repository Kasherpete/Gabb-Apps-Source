package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0004\b\u0007\u0010\b"}, mo20735d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/AbstractCoroutine;", "", "it", "Lkotlinx/coroutines/Job;", "invoke", "(Lkotlinx/coroutines/Job;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestBuilders.kt */
public final class TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1 extends Lambda implements Function1<Job, Boolean> {
    public static final TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1 INSTANCE = new TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1();

    public TestBuildersKt__TestBuildersKt$handleTimeout$activeChildren$1() {
        super(1);
    }

    public final Boolean invoke(Job job) {
        return Boolean.valueOf(job.isActive());
    }
}
