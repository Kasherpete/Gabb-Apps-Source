package com.gabb.packageupdater.domain.packagemanagement;

import com.gabb.base.certs.CertificateManager;
import com.gabb.data.entities.App;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0019\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u00020\u00122\n\u0010\u0019\u001a\u00060\u0014j\u0002`\u001aJ\u000e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010J\u0019\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u0011\u0010\u001e\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0011\u0010 \u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0019\u0010!\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u001a\u0010\"\u001a\u00020\u00122\n\u0010\u0019\u001a\u00060\u0014j\u0002`\u001a2\u0006\u0010#\u001a\u00020$R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, mo20735d2 = {"Lcom/gabb/packageupdater/domain/packagemanagement/PackageHandler;", "", "installer", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageInstaller;", "uninstaller", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageRemover;", "stateModifier", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageStateModifier;", "certsManager", "Lcom/gabb/base/certs/CertificateManager;", "versionChecker", "Lcom/gabb/packageupdater/domain/packagemanagement/VersionChecker;", "(Lcom/gabb/packageupdater/domain/packagemanagement/PackageInstaller;Lcom/gabb/packageupdater/domain/packagemanagement/PackageRemover;Lcom/gabb/packageupdater/domain/packagemanagement/PackageStateModifier;Lcom/gabb/base/certs/CertificateManager;Lcom/gabb/packageupdater/domain/packagemanagement/VersionChecker;)V", "handleDesiredPackageState", "", "app", "Lcom/gabb/data/entities/App;", "hasValidSignature", "", "uri", "", "install", "Lcom/gabb/packageupdater/data/resultentities/AppInstallInfo;", "(Lcom/gabb/packageupdater/data/resultentities/AppInstallInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isInstalled", "packageName", "Lcom/gabb/packageupdater/domain/packagemanagement/PackageName;", "needsChangeState", "remove", "(Lcom/gabb/data/entities/App;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAllUserApps", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeDisabledUserApps", "removeIfNonEligible", "requiresUpdate", "targetVersion", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PackageHandler.kt */
public final class PackageHandler {
    private final CertificateManager certsManager;
    private final PackageInstaller installer;
    private final PackageStateModifier stateModifier;
    private final PackageRemover uninstaller;
    private final VersionChecker versionChecker;

    @Inject
    public PackageHandler(PackageInstaller packageInstaller, PackageRemover packageRemover, PackageStateModifier packageStateModifier, CertificateManager certificateManager, VersionChecker versionChecker2) {
        Intrinsics.checkNotNullParameter(packageInstaller, "installer");
        Intrinsics.checkNotNullParameter(packageRemover, "uninstaller");
        Intrinsics.checkNotNullParameter(packageStateModifier, "stateModifier");
        Intrinsics.checkNotNullParameter(certificateManager, "certsManager");
        Intrinsics.checkNotNullParameter(versionChecker2, "versionChecker");
        this.installer = packageInstaller;
        this.uninstaller = packageRemover;
        this.stateModifier = packageStateModifier;
        this.certsManager = certificateManager;
        this.versionChecker = versionChecker2;
    }

    public final boolean isInstalled(String str) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        return this.versionChecker.isInstalled(str);
    }

    public final boolean requiresUpdate(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "packageName");
        return this.versionChecker.requiresNewVersion(str, j);
    }

    public final boolean hasValidSignature(String str) {
        Intrinsics.checkNotNullParameter(str, "uri");
        return this.certsManager.verifyApkSignatureWithUri(str);
    }

    public final Object removeIfNonEligible(App app, Continuation<? super Boolean> continuation) {
        if (this.certsManager.verifyApkSignatureWithPackageName(app.getPackageName())) {
            return this.uninstaller.delete(app.getPackageName(), continuation);
        }
        return Boxing.boxBoolean(true);
    }

    public final void handleDesiredPackageState(App app) {
        Intrinsics.checkNotNullParameter(app, "app");
        this.stateModifier.updatePackageState(app);
    }

    public final boolean needsChangeState(App app) {
        Intrinsics.checkNotNullParameter(app, "app");
        return this.stateModifier.needsChangeState(app);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object install(com.gabb.packageupdater.data.resultentities.AppInstallInfo r5, kotlin.coroutines.Continuation<? super java.lang.Boolean> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.gabb.packageupdater.domain.packagemanagement.PackageHandler$install$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$install$1 r0 = (com.gabb.packageupdater.domain.packagemanagement.PackageHandler$install$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$install$1 r0 = new com.gabb.packageupdater.domain.packagemanagement.PackageHandler$install$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r4 = r6.m185unboximpl()
            goto L_0x004e
        L_0x0030:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r6)
            com.gabb.packageupdater.domain.packagemanagement.PackageInstaller r4 = r4.installer
            java.lang.String r6 = r5.getPackageName()
            android.net.Uri r5 = r5.getUri()
            r0.label = r3
            java.lang.Object r4 = r4.m166install0E7RQCE(r6, r5, r0)
            if (r4 != r1) goto L_0x004e
            return r1
        L_0x004e:
            boolean r4 = kotlin.Result.m183isSuccessimpl(r4)
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.domain.packagemanagement.PackageHandler.install(com.gabb.packageupdater.data.resultentities.AppInstallInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object remove(App app, Continuation<? super Boolean> continuation) {
        return this.uninstaller.delete(app.getPackageName(), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeAllUserApps(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeAllUserApps$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeAllUserApps$1 r0 = (com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeAllUserApps$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeAllUserApps$1 r0 = new com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeAllUserApps$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.L$0
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r2 = (com.gabb.packageupdater.domain.packagemanagement.PackageHandler) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r2
            goto L_0x004d
        L_0x0033:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r8)
            com.gabb.packageupdater.domain.packagemanagement.PackageStateModifier r8 = r7.stateModifier
            java.util.List r8 = r8.getUserApps()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x004d:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x006f
            java.lang.Object r2 = r7.next()
            android.content.pm.ApplicationInfo r2 = (android.content.pm.ApplicationInfo) r2
            com.gabb.packageupdater.domain.packagemanagement.PackageRemover r4 = r8.uninstaller
            java.lang.String r2 = r2.packageName
            java.lang.String r5 = "it.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r2 = r4.delete(r2, r0)
            if (r2 != r1) goto L_0x004d
            return r1
        L_0x006f:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.domain.packagemanagement.PackageHandler.removeAllUserApps(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object removeDisabledUserApps(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeDisabledUserApps$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeDisabledUserApps$1 r0 = (com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeDisabledUserApps$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeDisabledUserApps$1 r0 = new com.gabb.packageupdater.domain.packagemanagement.PackageHandler$removeDisabledUserApps$1
            r0.<init>(r7, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r7 = r0.L$1
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r2 = r0.L$0
            com.gabb.packageupdater.domain.packagemanagement.PackageHandler r2 = (com.gabb.packageupdater.domain.packagemanagement.PackageHandler) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r2
            goto L_0x004d
        L_0x0033:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r8)
            com.gabb.packageupdater.domain.packagemanagement.PackageStateModifier r8 = r7.stateModifier
            java.util.List r8 = r8.getDisabledUserApps()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x004d:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x006f
            java.lang.Object r2 = r7.next()
            android.content.pm.ApplicationInfo r2 = (android.content.pm.ApplicationInfo) r2
            com.gabb.packageupdater.domain.packagemanagement.PackageRemover r4 = r8.uninstaller
            java.lang.String r2 = r2.packageName
            java.lang.String r5 = "it.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
            r0.L$0 = r8
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r2 = r4.delete(r2, r0)
            if (r2 != r1) goto L_0x004d
            return r1
        L_0x006f:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gabb.packageupdater.domain.packagemanagement.PackageHandler.removeDisabledUserApps(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
