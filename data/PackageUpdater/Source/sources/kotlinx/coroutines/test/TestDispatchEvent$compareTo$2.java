package kotlinx.coroutines.test;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;

@Metadata(mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestCoroutineScheduler.kt */
/* synthetic */ class TestDispatchEvent$compareTo$2 extends PropertyReference1Impl {
    public static final TestDispatchEvent$compareTo$2 INSTANCE = new TestDispatchEvent$compareTo$2();

    TestDispatchEvent$compareTo$2() {
        super(TestDispatchEvent.class, "count", "getCount()J", 0);
    }

    public Object get(Object obj) {
        return Long.valueOf(((TestDispatchEvent) obj).count);
    }
}
