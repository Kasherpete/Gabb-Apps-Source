package com.gabb.packageupdater.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IUpdateCallback extends IInterface {
    public static final int STATE_BLOCKED = 3;
    public static final int STATE_CANCELLED = 4;
    public static final int STATE_ENQUEUED = 0;
    public static final int STATE_FAILED = 5;
    public static final int STATE_RUNNING = 1;
    public static final int STATE_SUCCEEDED = 2;

    public static class Default implements IUpdateCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onChange(int i, boolean z, List<App> list) throws RemoteException {
        }
    }

    void onChange(int i, boolean z, List<App> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateCallback {
        private static final String DESCRIPTOR = "com.gabb.packageupdater.api.IUpdateCallback";
        static final int TRANSACTION_onChange = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUpdateCallback)) {
                return new Proxy(iBinder);
            }
            return (IUpdateCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onChange(parcel.readInt(), parcel.readInt() != 0, parcel.createTypedArrayList(App.CREATOR));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IUpdateCallback {
            public static IUpdateCallback sDefaultImpl;
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

            public void onChange(int i, boolean z, List<App> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onChange(i, z, list);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateCallback iUpdateCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iUpdateCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iUpdateCallback;
                return true;
            }
        }

        public static IUpdateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
