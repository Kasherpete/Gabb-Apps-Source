package com.gabb.packageupdater.domain.packagemanagement;

import android.os.RemoteException;
import android.util.Log;
import com.gabb.privileged.IPrivilegedCallback;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, mo20735d2 = {"com/gabb/packageupdater/domain/packagemanagement/PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1$onServiceConnected$callback$1", "Lcom/gabb/privileged/IPrivilegedCallback$Stub;", "handleResult", "", "packageName", "", "returnCode", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* renamed from: com.gabb.packageupdater.domain.packagemanagement.PrivilegedPackageInstaller$installImpl$2$mServiceConnection$1$onServiceConnected$callback$1 */
/* compiled from: PrivilegedPackageInstaller.kt */
public final class C0908x8630f4dc extends IPrivilegedCallback.Stub {
    final /* synthetic */ CancellableContinuation<Integer> $cont;

    C0908x8630f4dc(CancellableContinuation<? super Integer> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public void handleResult(String str, int i) throws RemoteException {
        Log.i("TAGGER", String.valueOf(i));
        if (this.$cont.isActive()) {
            CancellableContinuation<Integer> cancellableContinuation = this.$cont;
            Result.Companion companion = Result.Companion;
            cancellableContinuation.resumeWith(Result.m176constructorimpl(Integer.valueOf(i)));
        }
    }
}
