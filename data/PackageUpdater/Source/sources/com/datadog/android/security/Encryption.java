package com.datadog.android.security;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0006"}, mo20735d2 = {"Lcom/datadog/android/security/Encryption;", "", "decrypt", "", "data", "encrypt", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Encryption.kt */
public interface Encryption {
    byte[] decrypt(byte[] bArr);

    byte[] encrypt(byte[] bArr);
}
