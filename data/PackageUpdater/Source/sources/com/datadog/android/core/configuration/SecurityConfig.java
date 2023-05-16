package com.datadog.android.core.configuration;

import com.datadog.android.security.Encryption;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/SecurityConfig;", "", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "(Lcom/datadog/android/security/Encryption;)V", "getLocalDataEncryption", "()Lcom/datadog/android/security/Encryption;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SecurityConfig.kt */
public final class SecurityConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final SecurityConfig DEFAULT = new SecurityConfig((Encryption) null);
    private final Encryption localDataEncryption;

    public static /* synthetic */ SecurityConfig copy$default(SecurityConfig securityConfig, Encryption encryption, int i, Object obj) {
        if ((i & 1) != 0) {
            encryption = securityConfig.localDataEncryption;
        }
        return securityConfig.copy(encryption);
    }

    public final Encryption component1() {
        return this.localDataEncryption;
    }

    public final SecurityConfig copy(Encryption encryption) {
        return new SecurityConfig(encryption);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SecurityConfig) && Intrinsics.areEqual((Object) this.localDataEncryption, (Object) ((SecurityConfig) obj).localDataEncryption);
    }

    public int hashCode() {
        Encryption encryption = this.localDataEncryption;
        if (encryption == null) {
            return 0;
        }
        return encryption.hashCode();
    }

    public String toString() {
        return "SecurityConfig(localDataEncryption=" + this.localDataEncryption + ")";
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/configuration/SecurityConfig$Companion;", "", "()V", "DEFAULT", "Lcom/datadog/android/core/configuration/SecurityConfig;", "getDEFAULT$dd_sdk_android_release", "()Lcom/datadog/android/core/configuration/SecurityConfig;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: SecurityConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SecurityConfig getDEFAULT$dd_sdk_android_release() {
            return SecurityConfig.DEFAULT;
        }
    }

    public SecurityConfig(Encryption encryption) {
        this.localDataEncryption = encryption;
    }

    public final Encryption getLocalDataEncryption() {
        return this.localDataEncryption;
    }
}
