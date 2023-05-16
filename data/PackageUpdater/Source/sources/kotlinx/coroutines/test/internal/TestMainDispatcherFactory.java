package kotlinx.coroutines.test.internal;

import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatcherFactory;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, mo20735d2 = {"Lkotlinx/coroutines/test/internal/TestMainDispatcherFactory;", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "()V", "loadPriority", "", "getLoadPriority", "()I", "createDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "allFactories", "", "kotlinx-coroutines-test"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: TestMainDispatcherJvm.kt */
public final class TestMainDispatcherFactory implements MainDispatcherFactory {
    public int getLoadPriority() {
        return Integer.MAX_VALUE;
    }

    public String hintOnError() {
        return MainDispatcherFactory.DefaultImpls.hintOnError(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: kotlinx.coroutines.internal.MainDispatcherFactory} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlinx.coroutines.MainCoroutineDispatcher createDispatcher(java.util.List<? extends kotlinx.coroutines.internal.MainDispatcherFactory> r5) {
        /*
            r4 = this;
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r5 = r5.iterator()
        L_0x000d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0025
            java.lang.Object r1 = r5.next()
            r2 = r1
            kotlinx.coroutines.internal.MainDispatcherFactory r2 = (kotlinx.coroutines.internal.MainDispatcherFactory) r2
            if (r2 == r4) goto L_0x001e
            r2 = 1
            goto L_0x001f
        L_0x001e:
            r2 = 0
        L_0x001f:
            if (r2 == 0) goto L_0x000d
            r0.add(r1)
            goto L_0x000d
        L_0x0025:
            java.util.List r0 = (java.util.List) r0
            r4 = r0
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
            boolean r5 = r4.hasNext()
            if (r5 != 0) goto L_0x0036
            r4 = 0
            goto L_0x005f
        L_0x0036:
            java.lang.Object r5 = r4.next()
            boolean r1 = r4.hasNext()
            if (r1 != 0) goto L_0x0042
        L_0x0040:
            r4 = r5
            goto L_0x005f
        L_0x0042:
            r1 = r5
            kotlinx.coroutines.internal.MainDispatcherFactory r1 = (kotlinx.coroutines.internal.MainDispatcherFactory) r1
            int r1 = r1.getLoadPriority()
        L_0x0049:
            java.lang.Object r2 = r4.next()
            r3 = r2
            kotlinx.coroutines.internal.MainDispatcherFactory r3 = (kotlinx.coroutines.internal.MainDispatcherFactory) r3
            int r3 = r3.getLoadPriority()
            if (r1 >= r3) goto L_0x0058
            r5 = r2
            r1 = r3
        L_0x0058:
            boolean r2 = r4.hasNext()
            if (r2 != 0) goto L_0x0049
            goto L_0x0040
        L_0x005f:
            kotlinx.coroutines.internal.MainDispatcherFactory r4 = (kotlinx.coroutines.internal.MainDispatcherFactory) r4
            if (r4 != 0) goto L_0x0067
            kotlinx.coroutines.internal.MissingMainCoroutineDispatcherFactory r4 = kotlinx.coroutines.internal.MissingMainCoroutineDispatcherFactory.INSTANCE
            kotlinx.coroutines.internal.MainDispatcherFactory r4 = (kotlinx.coroutines.internal.MainDispatcherFactory) r4
        L_0x0067:
            kotlinx.coroutines.MainCoroutineDispatcher r4 = kotlinx.coroutines.internal.MainDispatchersKt.tryCreateDispatcher(r4, r0)
            kotlinx.coroutines.test.internal.TestMainDispatcher r5 = new kotlinx.coroutines.test.internal.TestMainDispatcher
            kotlinx.coroutines.CoroutineDispatcher r4 = (kotlinx.coroutines.CoroutineDispatcher) r4
            r5.<init>(r4)
            kotlinx.coroutines.MainCoroutineDispatcher r5 = (kotlinx.coroutines.MainCoroutineDispatcher) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.test.internal.TestMainDispatcherFactory.createDispatcher(java.util.List):kotlinx.coroutines.MainCoroutineDispatcher");
    }
}
