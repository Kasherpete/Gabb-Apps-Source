package com.gabb.packageupdater.domain.packagemanagement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.gabb.privileged.IPrivilegedCallback;
import com.gabb.privileged.IPrivilegedService;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, mo20735d2 = {"com/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageRemover$delete$2$mServiceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PrivilegedPackageRemover.kt */
public final class PrivilegedPackageRemover$delete$2$mServiceConnection$1 implements ServiceConnection {
    final /* synthetic */ CancellableContinuation<Boolean> $cont;
    final /* synthetic */ String $packageName;

    public void onServiceDisconnected(ComponentName componentName) {
    }

    PrivilegedPackageRemover$delete$2$mServiceConnection$1(String str, CancellableContinuation<? super Boolean> cancellableContinuation) {
        this.$packageName = str;
        this.$cont = cancellableContinuation;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IPrivilegedService asInterface = IPrivilegedService.Stub.asInterface(iBinder);
        Intrinsics.checkNotNullExpressionValue(asInterface, "asInterface(service)");
        IPrivilegedCallback privilegedPackageRemover$delete$2$mServiceConnection$1$onServiceConnected$callback$1 = new C0909xdd8ac7b4(this.$cont);
        try {
            if (!PrivilegedPackageRemover.exceptionList.contains(this.$packageName)) {
                asInterface.deletePackage(this.$packageName, 0, privilegedPackageRemover$delete$2$mServiceConnection$1$onServiceConnected$callback$1);
            }
        } catch (RemoteException unused) {
            if (this.$cont.isActive()) {
                Result.Companion companion = Result.Companion;
                this.$cont.resumeWith(Result.m176constructorimpl(false));
            }
        }
    }
}
