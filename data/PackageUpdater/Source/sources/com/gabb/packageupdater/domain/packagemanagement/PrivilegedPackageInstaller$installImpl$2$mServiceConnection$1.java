package com.gabb.packageupdater.domain.packagemanagement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.gabb.packageupdater.util.UriKt;
import com.gabb.privileged.IPrivilegedCallback;
import com.gabb.privileged.IPrivilegedService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, mo20735d2 = {"com/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PrivilegedPackageInstaller.kt */
public final class PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1 implements ServiceConnection {
    final /* synthetic */ CancellableContinuation<Integer> $cont;
    final /* synthetic */ String $packageName;
    final /* synthetic */ Uri $rawUri;
    final /* synthetic */ PrivilegedPackageInstaller this$0;

    public void onServiceDisconnected(ComponentName componentName) {
    }

    PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1(PrivilegedPackageInstaller privilegedPackageInstaller, String str, Uri uri, CancellableContinuation<? super Integer> cancellableContinuation) {
        this.this$0 = privilegedPackageInstaller;
        this.$packageName = str;
        this.$rawUri = uri;
        this.$cont = cancellableContinuation;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        IPrivilegedService asInterface = IPrivilegedService.Stub.asInterface(iBinder);
        Intrinsics.checkNotNullExpressionValue(asInterface, "asInterface(service)");
        Log.i("TAGGER", "connected");
        IPrivilegedCallback privilegedPackageInstaller$installImpl$2$mServiceConnection$1$onServiceConnected$callback$1 = new C0908x8630f4dc(this.$cont);
        try {
            this.this$0.requests.put(this.$packageName, this.$rawUri);
            UriKt.grantPermission(this.this$0.context, PrivilegedPackageRemover.PACKAGE_NAME, this.$rawUri);
            asInterface.installPackage(this.$rawUri, 2, (String) null, privilegedPackageInstaller$installImpl$2$mServiceConnection$1$onServiceConnected$callback$1);
        } catch (RemoteException e) {
            throw e;
        }
    }
}
