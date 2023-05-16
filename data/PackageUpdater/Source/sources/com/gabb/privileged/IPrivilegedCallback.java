package com.gabb.privileged;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPrivilegedCallback extends IInterface {

    public static class Default implements IPrivilegedCallback {
        public IBinder asBinder() {
            return null;
        }

        public void handleResult(String str, int i) throws RemoteException {
        }
    }

    void handleResult(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrivilegedCallback {
        private static final String DESCRIPTOR = "com.gabb.privileged.IPrivilegedCallback";
        static final int TRANSACTION_handleResult = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPrivilegedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPrivilegedCallback)) {
                return new Proxy(iBinder);
            }
            return (IPrivilegedCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                handleResult(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IPrivilegedCallback {
            public static IPrivilegedCallback sDefaultImpl;
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

            public void handleResult(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().handleResult(str, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPrivilegedCallback iPrivilegedCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iPrivilegedCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iPrivilegedCallback;
                return true;
            }
        }

        public static IPrivilegedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
