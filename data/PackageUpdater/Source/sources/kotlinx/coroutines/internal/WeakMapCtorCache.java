package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\tj\u0002`\n2\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R4\u0010\u0005\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\tj\u0002`\n0\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lkotlinx/coroutines/internal/WeakMapCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", "()V", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "get", "key", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ExceptionsConstructor.kt */
final class WeakMapCtorCache extends CtorCache {
    public static final WeakMapCtorCache INSTANCE = new WeakMapCtorCache();
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    private WeakMapCtorCache() {
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable> get(java.lang.Class<? extends java.lang.Throwable> r6) {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = cacheLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r5.readLock()
            r0.lock()
            java.util.WeakHashMap<java.lang.Class<? extends java.lang.Throwable>, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable>> r1 = exceptionCtors     // Catch:{ all -> 0x0070 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0070 }
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1     // Catch:{ all -> 0x0070 }
            r0.unlock()
            if (r1 == 0) goto L_0x0017
            return r1
        L_0x0017:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r5.readLock()
            int r1 = r5.getWriteHoldCount()
            r2 = 0
            if (r1 != 0) goto L_0x0027
            int r1 = r5.getReadHoldCount()
            goto L_0x0028
        L_0x0027:
            r1 = r2
        L_0x0028:
            r3 = r2
        L_0x0029:
            if (r3 >= r1) goto L_0x0031
            r0.unlock()
            int r3 = r3 + 1
            goto L_0x0029
        L_0x0031:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r5 = r5.writeLock()
            r5.lock()
            java.util.WeakHashMap<java.lang.Class<? extends java.lang.Throwable>, kotlin.jvm.functions.Function1<java.lang.Throwable, java.lang.Throwable>> r3 = exceptionCtors     // Catch:{ all -> 0x0063 }
            java.lang.Object r4 = r3.get(r6)     // Catch:{ all -> 0x0063 }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ all -> 0x0063 }
            if (r4 == 0) goto L_0x004e
        L_0x0042:
            if (r2 >= r1) goto L_0x004a
            r0.lock()
            int r2 = r2 + 1
            goto L_0x0042
        L_0x004a:
            r5.unlock()
            return r4
        L_0x004e:
            kotlin.jvm.functions.Function1 r4 = kotlinx.coroutines.internal.ExceptionsConstructorKt.createConstructor(r6)     // Catch:{ all -> 0x0063 }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ all -> 0x0063 }
            r3.put(r6, r4)     // Catch:{ all -> 0x0063 }
        L_0x0057:
            if (r2 >= r1) goto L_0x005f
            r0.lock()
            int r2 = r2 + 1
            goto L_0x0057
        L_0x005f:
            r5.unlock()
            return r4
        L_0x0063:
            r6 = move-exception
        L_0x0064:
            if (r2 >= r1) goto L_0x006c
            r0.lock()
            int r2 = r2 + 1
            goto L_0x0064
        L_0x006c:
            r5.unlock()
            throw r6
        L_0x0070:
            r5 = move-exception
            r0.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.WeakMapCtorCache.get(java.lang.Class):kotlin.jvm.functions.Function1");
    }
}
