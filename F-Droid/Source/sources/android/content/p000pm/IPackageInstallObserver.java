package android.content.p000pm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: android.content.pm.IPackageInstallObserver */
public interface IPackageInstallObserver extends IInterface {
    void packageInstalled(String str, int i) throws RemoteException;

    /* renamed from: android.content.pm.IPackageInstallObserver$Stub */
    public static abstract class Stub extends Binder implements IPackageInstallObserver {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IPackageInstallObserver asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            throw new RuntimeException("Stub!");
        }
    }
}
