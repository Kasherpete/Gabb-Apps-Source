package com.gabb.packageupdater.domain.packagemanagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.packageupdater.BuildConfig;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageRemover;", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageRemover;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "delete", "", "packageName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PrivilegedPackageRemover.kt */
public final class PrivilegedPackageRemover implements PackageRemover {
    public static final int ACTION_DELETE_EXISTING = 0;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PACKAGE_NAME = "com.gabb.privileged";
    public static final String SERVICE_NAME = "com.gabb.privileged.IPrivilegedService";
    /* access modifiers changed from: private */
    public static final List<String> exceptionList = CollectionsKt.listOf(BuildConfig.APPLICATION_ID, PACKAGE_NAME);
    /* access modifiers changed from: private */
    public final Context context;

    @Inject
    public PrivilegedPackageRemover(@ApplicationContext Context context2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        this.context = context2;
    }

    @Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageRemover$Companion;", "", "()V", "ACTION_DELETE_EXISTING", "", "PACKAGE_NAME", "", "SERVICE_NAME", "exceptionList", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: PrivilegedPackageRemover.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Object delete(String str, Continuation<? super Boolean> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Log.i("DELETED", "LAUNCHING");
        Intent intent = new Intent(SERVICE_NAME);
        intent.setPackage(PACKAGE_NAME);
        this.context.getApplicationContext().bindService(intent, new PrivilegedPackageRemover$delete$2$mServiceConnection$1(str, cancellableContinuationImpl), 1);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
