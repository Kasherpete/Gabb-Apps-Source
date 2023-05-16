package com.datadog.android.rum.internal.ndk;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u0000  2\u00020\u0001:\u0001 B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\r\u0010\u001d\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u001eJ\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006!"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkCrashLog;", "", "signal", "", "timestamp", "", "signalName", "", "message", "stacktrace", "(IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getSignal", "()I", "getSignalName", "getStacktrace", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toJson", "toJson$dd_sdk_android_release", "toString", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NdkCrashLog.kt */
public final class NdkCrashLog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MESSAGE_KEY_NAME = "message";
    public static final String SIGNAL_KEY_NAME = "signal";
    public static final String SIGNAL_NAME_KEY_NAME = "signal_name";
    public static final String STACKTRACE_KEY_NAME = "stacktrace";
    public static final String TIMESTAMP_KEY_NAME = "timestamp";
    private final String message;
    private final int signal;
    private final String signalName;
    private final String stacktrace;
    private final long timestamp;

    public static /* synthetic */ NdkCrashLog copy$default(NdkCrashLog ndkCrashLog, int i, long j, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = ndkCrashLog.signal;
        }
        if ((i2 & 2) != 0) {
            j = ndkCrashLog.timestamp;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            str = ndkCrashLog.signalName;
        }
        String str4 = str;
        if ((i2 & 8) != 0) {
            str2 = ndkCrashLog.message;
        }
        String str5 = str2;
        if ((i2 & 16) != 0) {
            str3 = ndkCrashLog.stacktrace;
        }
        return ndkCrashLog.copy(i, j2, str4, str5, str3);
    }

    public final int component1() {
        return this.signal;
    }

    public final long component2() {
        return this.timestamp;
    }

    public final String component3() {
        return this.signalName;
    }

    public final String component4() {
        return this.message;
    }

    public final String component5() {
        return this.stacktrace;
    }

    public final NdkCrashLog copy(int i, long j, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "signalName");
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(str3, STACKTRACE_KEY_NAME);
        return new NdkCrashLog(i, j, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NdkCrashLog)) {
            return false;
        }
        NdkCrashLog ndkCrashLog = (NdkCrashLog) obj;
        return this.signal == ndkCrashLog.signal && this.timestamp == ndkCrashLog.timestamp && Intrinsics.areEqual((Object) this.signalName, (Object) ndkCrashLog.signalName) && Intrinsics.areEqual((Object) this.message, (Object) ndkCrashLog.message) && Intrinsics.areEqual((Object) this.stacktrace, (Object) ndkCrashLog.stacktrace);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.signal) * 31) + Long.hashCode(this.timestamp)) * 31) + this.signalName.hashCode()) * 31) + this.message.hashCode()) * 31) + this.stacktrace.hashCode();
    }

    public String toString() {
        int i = this.signal;
        long j = this.timestamp;
        String str = this.signalName;
        String str2 = this.message;
        return "NdkCrashLog(signal=" + i + ", timestamp=" + j + ", signalName=" + str + ", message=" + str2 + ", stacktrace=" + this.stacktrace + ")";
    }

    public NdkCrashLog(int i, long j, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "signalName");
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(str3, STACKTRACE_KEY_NAME);
        this.signal = i;
        this.timestamp = j;
        this.signalName = str;
        this.message = str2;
        this.stacktrace = str3;
    }

    public final int getSignal() {
        return this.signal;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getSignalName() {
        return this.signalName;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getStacktrace() {
        return this.stacktrace;
    }

    public final String toJson$dd_sdk_android_release() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(SIGNAL_KEY_NAME, (Number) Integer.valueOf(this.signal));
        jsonObject.addProperty(SIGNAL_NAME_KEY_NAME, this.signalName);
        jsonObject.addProperty(TIMESTAMP_KEY_NAME, (Number) Long.valueOf(this.timestamp));
        jsonObject.addProperty("message", this.message);
        jsonObject.addProperty(STACKTRACE_KEY_NAME, this.stacktrace);
        String jsonObject2 = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject2, "jsonObject.toString()");
        return jsonObject2;
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkCrashLog$Companion;", "", "()V", "MESSAGE_KEY_NAME", "", "SIGNAL_KEY_NAME", "SIGNAL_NAME_KEY_NAME", "STACKTRACE_KEY_NAME", "TIMESTAMP_KEY_NAME", "fromJson", "Lcom/datadog/android/rum/internal/ndk/NdkCrashLog;", "jsonString", "fromJson$dd_sdk_android_release", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: NdkCrashLog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NdkCrashLog fromJson$dd_sdk_android_release(String str) throws JsonParseException, IllegalStateException {
            Intrinsics.checkNotNullParameter(str, "jsonString");
            JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
            int asInt = asJsonObject.get(NdkCrashLog.SIGNAL_KEY_NAME).getAsInt();
            long asLong = asJsonObject.get(NdkCrashLog.TIMESTAMP_KEY_NAME).getAsLong();
            String asString = asJsonObject.get(NdkCrashLog.SIGNAL_NAME_KEY_NAME).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString, "jsonObject.get(SIGNAL_NAME_KEY_NAME).asString");
            String asString2 = asJsonObject.get("message").getAsString();
            Intrinsics.checkNotNullExpressionValue(asString2, "jsonObject.get(MESSAGE_KEY_NAME).asString");
            String asString3 = asJsonObject.get(NdkCrashLog.STACKTRACE_KEY_NAME).getAsString();
            Intrinsics.checkNotNullExpressionValue(asString3, "jsonObject.get(STACKTRACE_KEY_NAME).asString");
            return new NdkCrashLog(asInt, asLong, asString, asString2, asString3);
        }
    }
}
