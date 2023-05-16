package com.gabb.privileged;

import android.app.PendingIntent;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.p000pm.IPackageDeleteObserver;
import android.content.p000pm.IPackageInstallObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.gabb.privileged.IPrivilegedService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

public class PrivilegedService extends Service {
    private static final String BROADCAST_ACTION_INSTALL = "com.gabb.PrivilegedExtension.ACTION_INSTALL_COMMIT";
    private static final String BROADCAST_ACTION_UNINSTALL = "com.gabb.PrivilegedExtension.ACTION_UNINSTALL_COMMIT";
    private static final String BROADCAST_SENDER_PERMISSION = "android.permission.INSTALL_PACKAGES";
    private static final String EXTRA_LEGACY_STATUS = "android.content.pm.extra.LEGACY_STATUS";
    public static final String TAG = "PrivilegedExtension";
    /* access modifiers changed from: private */
    public AccessProtectionHelper accessProtectionHelper;
    private final IPrivilegedService.Stub binder = new IPrivilegedService.Stub() {
        public boolean hasPrivilegedPermissions() {
            return PrivilegedService.this.accessProtectionHelper.isCallerAllowed() && PrivilegedService.this.hasPrivilegedPermissionsImpl();
        }

        public void installPackage(Uri uri, int i, String str, IPrivilegedCallback iPrivilegedCallback) {
            if (PrivilegedService.this.accessProtectionHelper.isCallerAllowed()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    PrivilegedService.this.doPackageStage(uri);
                    IPrivilegedCallback unused = PrivilegedService.this.mCallback = iPrivilegedCallback;
                    return;
                }
                PrivilegedService.this.installPackageImpl(uri, i, str, iPrivilegedCallback);
            }
        }

        public void deletePackage(String str, int i, IPrivilegedCallback iPrivilegedCallback) {
            if (PrivilegedService.this.accessProtectionHelper.isCallerAllowed()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    IPrivilegedCallback unused = PrivilegedService.this.mCallback = iPrivilegedCallback;
                    PackageManager packageManager = PrivilegedService.this.getPackageManager();
                    PackageInstaller packageInstaller = packageManager.getPackageInstaller();
                    packageManager.setInstallerPackageName(str, PrivilegedService.this.getPackageName());
                    packageInstaller.uninstall(str, PendingIntent.getBroadcast(PrivilegedService.this.context, 0, new Intent(PrivilegedService.BROADCAST_ACTION_UNINSTALL), 134217728).getIntentSender());
                    return;
                }
                PrivilegedService.this.deletePackageImpl(str, i, iPrivilegedCallback);
            }
        }

        public List<PackageInfo> getInstalledPackages(int i) {
            Integer matchStaticSharedLibraries;
            if (Build.VERSION.SDK_INT >= 29 && (matchStaticSharedLibraries = PrivilegedService.getMatchStaticSharedLibraries()) != null) {
                i |= matchStaticSharedLibraries.intValue();
            }
            return PrivilegedService.this.getPackageManager().getInstalledPackages(i);
        }
    };
    Context context = this;
    private Method deleteMethod;
    private Method installMethod;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(PrivilegedService.EXTRA_LEGACY_STATUS, 1);
            try {
                PrivilegedService.this.mCallback.handleResult(intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME"), intExtra);
            } catch (RemoteException e) {
                Log.e(PrivilegedService.TAG, "RemoteException", e);
            }
        }
    };
    /* access modifiers changed from: private */
    public IPrivilegedCallback mCallback;

    /* access modifiers changed from: private */
    public boolean hasPrivilegedPermissionsImpl() {
        boolean z = getPackageManager().checkPermission(BROADCAST_SENDER_PERMISSION, getPackageName()) == 0;
        boolean z2 = getPackageManager().checkPermission("android.permission.DELETE_PACKAGES", getPackageName()) == 0;
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void installPackageImpl(Uri uri, int i, String str, final IPrivilegedCallback iPrivilegedCallback) {
        C00001 r0 = new IPackageInstallObserver.Stub() {
            public void packageInstalled(String str, int i) throws RemoteException {
                try {
                    iPrivilegedCallback.handleResult(str, i);
                } catch (RemoteException e) {
                    Log.e(PrivilegedService.TAG, "RemoteException", e);
                }
            }
        };
        try {
            this.installMethod.invoke(getPackageManager(), new Object[]{uri, r0, Integer.valueOf(i), str});
        } catch (Exception e) {
            Log.e(TAG, "Android not compatible!", e);
            try {
                iPrivilegedCallback.handleResult((String) null, 0);
            } catch (RemoteException e2) {
                Log.e(TAG, "RemoteException", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void deletePackageImpl(String str, int i, final IPrivilegedCallback iPrivilegedCallback) {
        if (isDeviceOwner(str)) {
            Log.e(TAG, "Cannot delete " + str + ". This app is the device owner.");
            return;
        }
        C00012 r0 = new IPackageDeleteObserver.Stub() {
            public void packageDeleted(String str, int i) throws RemoteException {
                try {
                    iPrivilegedCallback.handleResult(str, i);
                } catch (RemoteException e) {
                    Log.e(PrivilegedService.TAG, "RemoteException", e);
                }
            }
        };
        try {
            this.deleteMethod.invoke(getPackageManager(), new Object[]{str, r0, Integer.valueOf(i)});
        } catch (Exception e) {
            Log.e(TAG, "Android not compatible!", e);
            try {
                iPrivilegedCallback.handleResult((String) null, 0);
            } catch (RemoteException e2) {
                Log.e(TAG, "RemoteException", e2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void doPackageStage(Uri uri) {
        InputStream openInputStream;
        OutputStream openWrite;
        PackageManager packageManager = getPackageManager();
        PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
        PackageInstaller packageInstaller = packageManager.getPackageInstaller();
        PackageInstaller.Session session = null;
        try {
            int createSession = packageInstaller.createSession(sessionParams);
            byte[] bArr = new byte[65536];
            session = packageInstaller.openSession(createSession);
            openInputStream = getContentResolver().openInputStream(uri);
            openWrite = session.openWrite("PackageInstaller", 0, -1);
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                openWrite.write(bArr, 0, read);
            }
            session.fsync(openWrite);
            IoUtils.closeQuietly(openInputStream);
            IoUtils.closeQuietly(openWrite);
            session.commit(PendingIntent.getBroadcast(this, createSession, new Intent(BROADCAST_ACTION_INSTALL), 134217728).getIntentSender());
        } catch (IOException e) {
            try {
                Log.e(TAG, "Failure: " + e.toString());
            } catch (Throwable th) {
                IoUtils.closeQuietly(session);
                throw th;
            }
        } catch (Throwable th2) {
            IoUtils.closeQuietly(openInputStream);
            IoUtils.closeQuietly(openWrite);
            throw th2;
        }
        IoUtils.closeQuietly(session);
    }

    protected static Integer getMatchStaticSharedLibraries() {
        try {
            return (Integer) PackageManager.class.getDeclaredField("MATCH_STATIC_SHARED_LIBRARIES").get((Object) null);
        } catch (ClassCastException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    public void onCreate() {
        super.onCreate();
        this.accessProtectionHelper = new AccessProtectionHelper(this);
        if (Build.VERSION.SDK_INT < 24) {
            try {
                Class[] clsArr = {Uri.class, IPackageInstallObserver.class, Integer.TYPE, String.class};
                Class[] clsArr2 = {String.class, IPackageDeleteObserver.class, Integer.TYPE};
                PackageManager packageManager = getPackageManager();
                this.installMethod = packageManager.getClass().getMethod("installPackage", clsArr);
                this.deleteMethod = packageManager.getClass().getMethod("deletePackage", clsArr2);
            } catch (NoSuchMethodException e) {
                Log.e(TAG, "Android not compatible!", e);
                stopSelf();
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_INSTALL);
        registerReceiver(this.mBroadcastReceiver, intentFilter, BROADCAST_SENDER_PERMISSION, (Handler) null);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(BROADCAST_ACTION_UNINSTALL);
        registerReceiver(this.mBroadcastReceiver, intentFilter2, BROADCAST_SENDER_PERMISSION, (Handler) null);
    }

    private boolean isDeviceOwner(String str) {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return ((DevicePolicyManager) getSystemService("device_policy")).isDeviceOwnerApp(str);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mBroadcastReceiver);
    }
}
