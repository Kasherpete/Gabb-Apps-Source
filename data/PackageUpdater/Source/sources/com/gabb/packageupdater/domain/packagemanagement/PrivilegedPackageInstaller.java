package com.gabb.packageupdater.domain.packagemanagement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J2\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageInstaller;", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageInstaller;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "requests", "", "", "Landroid/net/Uri;", "install", "Lkotlin/Result;", "", "packageName", "rawUri", "install-0E7RQCE", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installImpl", "isInstalling", "", "Companion", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PrivilegedPackageInstaller.kt */
public final class PrivilegedPackageInstaller implements PackageInstaller {
    public static final int ACTION_INSTALL_REPLACE_EXISTING = 2;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PACKAGE_NAME = "com.gabb.privileged";
    private static final String SERVICE_NAME = "com.gabb.privileged.IPrivilegedService";
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Map<String, Uri> requests = new LinkedHashMap();

    @Inject
    public PrivilegedPackageInstaller(@ApplicationContext Context context2) {
        Intrinsics.checkNotNullParameter(context2, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        this.context = context2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: install-0E7RQCE  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object m167install0E7RQCE(java.lang.String r5, android.net.Uri r6, kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Integer>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$install$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$install$1 r0 = (com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$install$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$install$1 r0 = new com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$install$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$1
            r5 = r4
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r4 = r0.L$0
            com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller r4 = (com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller) r4
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x0065 }
            goto L_0x0056
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r7)
            android.content.Context r7 = r4.context
            android.net.Uri r6 = com.gabb.packageupdater.util.UriKt.getFileProviderURI(r7, r6)
            kotlin.Result$Companion r7 = kotlin.Result.Companion     // Catch:{ all -> 0x0065 }
            r7 = r4
            com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller r7 = (com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller) r7     // Catch:{ all -> 0x0065 }
            r0.L$0 = r4     // Catch:{ all -> 0x0065 }
            r0.L$1 = r5     // Catch:{ all -> 0x0065 }
            r0.label = r3     // Catch:{ all -> 0x0065 }
            java.lang.Object r7 = r7.installImpl(r5, r6, r0)     // Catch:{ all -> 0x0065 }
            if (r7 != r1) goto L_0x0056
            return r1
        L_0x0056:
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ all -> 0x0065 }
            int r6 = r7.intValue()     // Catch:{ all -> 0x0065 }
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)     // Catch:{ all -> 0x0065 }
            java.lang.Object r6 = kotlin.Result.m176constructorimpl(r6)     // Catch:{ all -> 0x0065 }
            goto L_0x0070
        L_0x0065:
            r6 = move-exception
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m176constructorimpl(r6)
        L_0x0070:
            java.lang.Throwable r7 = kotlin.Result.m179exceptionOrNullimpl(r6)
            if (r7 == 0) goto L_0x007e
            java.util.Map<java.lang.String, android.net.Uri> r0 = r4.requests
            r0.remove(r5)
            r7.printStackTrace()
        L_0x007e:
            boolean r7 = kotlin.Result.m183isSuccessimpl(r6)
            if (r7 == 0) goto L_0x008f
            r7 = r6
            java.lang.Number r7 = (java.lang.Number) r7
            r7.intValue()
            java.util.Map<java.lang.String, android.net.Uri> r4 = r4.requests
            r4.remove(r5)
        L_0x008f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller.m167install0E7RQCE(java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public boolean isInstalling(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        return this.requests.containsKey(str);
    }

    @Metadata(mo20734d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageInstaller$Companion;", "", "()V", "ACTION_INSTALL_REPLACE_EXISTING", "", "PACKAGE_NAME", "", "SERVICE_NAME", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: PrivilegedPackageInstaller.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public final Object installImpl(String str, Uri uri, Continuation<? super Integer> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Log.i("TAGGER", "install");
        Intent intent = new Intent("com.gabb.privileged.IPrivilegedService");
        intent.setComponent(new ComponentName(this.context.createPackageContext("com.gabb.privileged", 0), "com.gabb.privileged.PrivilegedService"));
        this.context.bindService(intent, new PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1(this, str, uri, cancellableContinuationImpl), 1);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
