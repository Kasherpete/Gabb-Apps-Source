package com.gabb.privileged;

import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.gabb.privileged.IPrivilegedCallback;
import java.util.ArrayList;
import java.util.List;

public interface IPrivilegedService extends IInterface {

    public static class Default implements IPrivilegedService {
        public IBinder asBinder() {
            return null;
        }

        public void deletePackage(String str, int i, IPrivilegedCallback iPrivilegedCallback) throws RemoteException {
        }

        public List<PackageInfo> getInstalledPackages(int i) throws RemoteException {
            return null;
        }

        public boolean hasPrivilegedPermissions() throws RemoteException {
            return false;
        }

        public void installPackage(Uri uri, int i, String str, IPrivilegedCallback iPrivilegedCallback) throws RemoteException {
        }
    }

    void deletePackage(String str, int i, IPrivilegedCallback iPrivilegedCallback) throws RemoteException;

    List<PackageInfo> getInstalledPackages(int i) throws RemoteException;

    boolean hasPrivilegedPermissions() throws RemoteException;

    void installPackage(Uri uri, int i, String str, IPrivilegedCallback iPrivilegedCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrivilegedService {
        private static final String DESCRIPTOR = "com.gabb.privileged.IPrivilegedService";
        static final int TRANSACTION_deletePackage = 3;
        static final int TRANSACTION_getInstalledPackages = 4;
        static final int TRANSACTION_hasPrivilegedPermissions = 1;
        static final int TRANSACTION_installPackage = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivilegedService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPrivilegedService)) {
                return new Proxy(iBinder);
            }
            return (IPrivilegedService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean hasPrivilegedPermissions = hasPrivilegedPermissions();
                parcel2.writeNoException();
                parcel2.writeInt(hasPrivilegedPermissions ? 1 : 0);
                return true;
            } else if (i == TRANSACTION_installPackage) {
                parcel.enforceInterface(DESCRIPTOR);
                installPackage(parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString(), IPrivilegedCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == TRANSACTION_deletePackage) {
                parcel.enforceInterface(DESCRIPTOR);
                deletePackage(parcel.readString(), parcel.readInt(), IPrivilegedCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i == TRANSACTION_getInstalledPackages) {
                parcel.enforceInterface(DESCRIPTOR);
                List<PackageInfo> installedPackages = getInstalledPackages(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeTypedList(installedPackages);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IPrivilegedService {
            public static IPrivilegedService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public boolean hasPrivilegedPermissions() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasPrivilegedPermissions();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void installPackage(Uri uri, int i, String str, IPrivilegedCallback iPrivilegedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iPrivilegedCallback != null ? iPrivilegedCallback.asBinder() : null);
                    if (this.mRemote.transact(Stub.TRANSACTION_installPackage, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().installPackage(uri, i, str, iPrivilegedCallback);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void deletePackage(String str, int i, IPrivilegedCallback iPrivilegedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iPrivilegedCallback != null ? iPrivilegedCallback.asBinder() : null);
                    if (this.mRemote.transact(Stub.TRANSACTION_deletePackage, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().deletePackage(str, i, iPrivilegedCallback);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public List<PackageInfo> getInstalledPackages(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(Stub.TRANSACTION_getInstalledPackages, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledPackages(i);
                    }
                    obtain2.readException();
                    ArrayList createTypedArrayList = obtain2.createTypedArrayList(PackageInfo.CREATOR);
                    obtain2.recycle();
                    obtain.recycle();
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivilegedService iPrivilegedService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iPrivilegedService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iPrivilegedService;
                return true;
            }
        }

        public static IPrivilegedService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
