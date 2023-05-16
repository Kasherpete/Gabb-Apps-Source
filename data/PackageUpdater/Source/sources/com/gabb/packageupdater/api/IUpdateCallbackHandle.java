package com.gabb.packageupdater.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUpdateCallbackHandle extends IInterface {

    public static class Default implements IUpdateCallbackHandle {
        public IBinder asBinder() {
            return null;
        }

        public void removeCallback() throws RemoteException {
        }
    }

    void removeCallback() throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateCallbackHandle {
        private static final String DESCRIPTOR = "com.gabb.packageupdater.api.IUpdateCallbackHandle";
        static final int TRANSACTION_removeCallback = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateCallbackHandle asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUpdateCallbackHandle)) {
                return new Proxy(iBinder);
            }
            return (IUpdateCallbackHandle) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                removeCallback();
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IUpdateCallbackHandle {
            public static IUpdateCallbackHandle sDefaultImpl;
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

            public void removeCallback() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeCallback();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateCallbackHandle iUpdateCallbackHandle) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iUpdateCallbackHandle == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iUpdateCallbackHandle;
                return true;
            }
        }

        public static IUpdateCallbackHandle getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
