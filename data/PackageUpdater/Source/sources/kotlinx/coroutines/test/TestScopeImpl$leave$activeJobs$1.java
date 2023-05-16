package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.Job;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "it", "Lkotlinx/coroutines/Job;", "invoke", "(Lkotlinx/coroutines/Job;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestScope.kt */
final class TestScopeImpl$leave$activeJobs$1 extends Lambda implements Function1<Job, Boolean> {
    public static final TestScopeImpl$leave$activeJobs$1 INSTANCE = new TestScopeImpl$leave$activeJobs$1();

    TestScopeImpl$leave$activeJobs$1() {
        super(1);
    }

    public final Boolean invoke(Job job) {
        return Boolean.valueOf(job.isActive());
    }
}
