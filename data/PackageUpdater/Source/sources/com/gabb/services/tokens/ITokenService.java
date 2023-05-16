package com.gabb.services.tokens;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.gabb.services.tokens.ITokenCallback;

public interface ITokenService extends IInterface {

    public static class Default implements ITokenService {
        public IBinder asBinder() {
            return null;
        }

        public void fetchToken(ITokenCallback iTokenCallback) throws RemoteException {
        }
    }

    void fetchToken(ITokenCallback iTokenCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ITokenService {
        private static final String DESCRIPTOR = "com.gabb.services.tokens.ITokenService";
        static final int TRANSACTION_fetchToken = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITokenService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ITokenService)) {
                return new Proxy(iBinder);
            }
            return (ITokenService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                fetchToken(ITokenCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements ITokenService {
            public static ITokenService sDefaultImpl;
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

            public void fetchToken(ITokenCallback iTokenCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iTokenCallback != null ? iTokenCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().fetchToken(iTokenCallback);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITokenService iTokenService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iTokenService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iTokenService;
                return true;
            }
        }

        public static ITokenService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
