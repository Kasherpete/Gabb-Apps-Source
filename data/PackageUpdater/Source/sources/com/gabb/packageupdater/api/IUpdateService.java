package com.gabb.packageupdater.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.gabb.packageupdater.api.IUpdateCallback;
import com.gabb.packageupdater.api.IUpdateCallbackHandle;

public interface IUpdateService extends IInterface {

    public static class Default implements IUpdateService {
        public IBinder asBinder() {
            return null;
        }

        public IUpdateCallbackHandle checkForUpdates(IUpdateCallback iUpdateCallback) throws RemoteException {
            return null;
        }
    }

    IUpdateCallbackHandle checkForUpdates(IUpdateCallback iUpdateCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateService {
        private static final String DESCRIPTOR = "com.gabb.packageupdater.api.IUpdateService";
        static final int TRANSACTION_checkForUpdates = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUpdateService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUpdateService)) {
                return new Proxy(iBinder);
            }
            return (IUpdateService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IUpdateCallbackHandle checkForUpdates = checkForUpdates(IUpdateCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(checkForUpdates != null ? checkForUpdates.asBinder() : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IUpdateService {
            public static IUpdateService sDefaultImpl;
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

            public IUpdateCallbackHandle checkForUpdates(IUpdateCallback iUpdateCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iUpdateCallback != null ? iUpdateCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkForUpdates(iUpdateCallback);
                    }
                    obtain2.readException();
                    IUpdateCallbackHandle asInterface = IUpdateCallbackHandle.Stub.asInterface(obtain2.readStrongBinder());
                    obtain2.recycle();
                    obtain.recycle();
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUpdateService iUpdateService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iUpdateService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iUpdateService;
                return true;
            }
        }

        public static IUpdateService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
