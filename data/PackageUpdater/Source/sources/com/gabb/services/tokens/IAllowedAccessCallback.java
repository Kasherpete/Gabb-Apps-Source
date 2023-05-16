package com.gabb.services.tokens;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IAllowedAccessCallback extends IInterface {

    public static class Default implements IAllowedAccessCallback {
        public IBinder asBinder() {
            return null;
        }

        public void handleError(String str) throws RemoteException {
        }

        public void handleResult(List<GabbService> list) throws RemoteException {
        }

        public void handleUnauthorized() throws RemoteException {
        }
    }

    void handleError(String str) throws RemoteException;

    void handleResult(List<GabbService> list) throws RemoteException;

    void handleUnauthorized() throws RemoteException;

    public static abstract class Stub extends Binder implements IAllowedAccessCallback {
        private static final String DESCRIPTOR = "com.gabb.services.tokens.IAllowedAccessCallback";
        static final int TRANSACTION_handleError = 2;
        static final int TRANSACTION_handleResult = 1;
        static final int TRANSACTION_handleUnauthorized = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAllowedAccessCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAllowedAccessCallback)) {
                return new Proxy(iBinder);
            }
            return (IAllowedAccessCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                handleResult(parcel.createTypedArrayList(GabbService.CREATOR));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                handleError(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                handleUnauthorized();
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IAllowedAccessCallback {
            public static IAllowedAccessCallback sDefaultImpl;
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

            public void handleResult(List<GabbService> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().handleResult(list);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void handleError(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().handleError(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void handleUnauthorized() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().handleUnauthorized();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAllowedAccessCallback iAllowedAccessCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAllowedAccessCallback == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAllowedAccessCallback;
                return true;
            }
        }

        public static IAllowedAccessCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
