package com.gabb.privileged;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class AccessProtectionHelper {
    Context context;

    /* renamed from: pm */
    PackageManager f0pm;
    HashSet<Pair<String, String>> whitelist;

    AccessProtectionHelper(Context context2) {
        this(context2, ClientWhitelist.whitelist);
    }

    AccessProtectionHelper(Context context2, HashSet<Pair<String, String>> hashSet) {
        this.context = context2;
        this.f0pm = context2.getPackageManager();
        this.whitelist = hashSet;
    }

    public boolean isCallerAllowed() {
        return isUidAllowed(Binder.getCallingUid());
    }

    private boolean isUidAllowed(int i) {
        String[] packagesForUid = this.f0pm.getPackagesForUid(i);
        if (packagesForUid != null) {
            return isPackageAllowed(packagesForUid[0]);
        }
        throw new RuntimeException("Should not happen. No packages associated to caller UID!");
    }

    public boolean isPackageAllowed(String str) {
        Log.d(PrivilegedService.TAG, "Checking if package is allowed to access privileged extension: " + str);
        try {
            byte[] packageCertificate = getPackageCertificate(str);
            Iterator<Pair<String, String>> it = this.whitelist.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                String str2 = (String) next.second;
                byte[] hexStringToByteArray = hexStringToByteArray(str2);
                byte[] digest = MessageDigest.getInstance("SHA-256").digest(packageCertificate);
                String bigInteger = new BigInteger(1, digest).toString(16);
                Log.d(PrivilegedService.TAG, "Allowed cert hash: " + str2);
                Log.d(PrivilegedService.TAG, "Package cert hash: " + bigInteger);
                boolean equals = str.equals((String) next.first);
                boolean equals2 = Arrays.equals(hexStringToByteArray, digest);
                if (equals && equals2) {
                    Log.d(PrivilegedService.TAG, "Package is allowed to access the privileged extension!");
                    return true;
                }
            }
            Log.e(PrivilegedService.TAG, "Package is NOT allowed to access the privileged extension!");
            return false;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private byte[] getPackageCertificate(String str) {
        try {
            Signature[] signatureArr = this.f0pm.getPackageInfo(str, 64).signatures;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (Signature byteArray : signatureArr) {
                byteArrayOutputStream.write(byteArray.toByteArray());
            }
            return byteArrayOutputStream.toByteArray();
        } catch (PackageManager.NameNotFoundException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
