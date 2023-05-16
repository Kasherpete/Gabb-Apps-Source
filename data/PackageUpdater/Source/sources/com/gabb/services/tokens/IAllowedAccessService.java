package com.gabb.services.tokens;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.gabb.services.tokens.IAllowedAccessCallback;

public interface IAllowedAccessService extends IInterface {

    public static class Default implements IAllowedAccessService {
        public IBinder asBinder() {
            return null;
        }

        public void getCurrentServices(IAllowedAccessCallback iAllowedAccessCallback) throws RemoteException {
        }
    }

    void getCurrentServices(IAllowedAccessCallback iAllowedAccessCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAllowedAccessService {
        private static final String DESCRIPTOR = "com.gabb.services.tokens.IAllowedAccessService";
        static final int TRANSACTION_getCurrentServices = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAllowedAccessService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAllowedAccessService)) {
                return new Proxy(iBinder);
            }
            return (IAllowedAccessService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getCurrentServices(IAllowedAccessCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IAllowedAccessService {
            public static IAllowedAccessService sDefaultImpl;
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

            public void getCurrentServices(IAllowedAccessCallback iAllowedAccessCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAllowedAccessCallback != null ? iAllowedAccessCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getCurrentServices(iAllowedAccessCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAllowedAccessService iAllowedAccessService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iAllowedAccessService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iAllowedAccessService;
                return true;
            }
        }

        public static IAllowedAccessService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
