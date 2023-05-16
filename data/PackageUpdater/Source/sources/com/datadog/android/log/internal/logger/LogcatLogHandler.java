package com.datadog.android.log.internal.logger;

import android.os.Build;
import android.util.Log;
import com.datadog.android.Datadog;
import com.datadog.android.log.Logger;
import com.datadog.trace.api.Config;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\u0012JU\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010$\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\fH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006&"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/LogcatLogHandler;", "Lcom/datadog/android/log/internal/logger/LogHandler;", "serviceName", "", "useClassnameAsTag", "", "(Ljava/lang/String;Z)V", "getServiceName$dd_sdk_android_release", "()Ljava/lang/String;", "getUseClassnameAsTag$dd_sdk_android_release", "()Z", "findValidCallStackElement", "Ljava/lang/StackTraceElement;", "stackTrace", "", "findValidCallStackElement$dd_sdk_android_release", "([Ljava/lang/StackTraceElement;)Ljava/lang/StackTraceElement;", "getCallerStackElement", "getCallerStackElement$dd_sdk_android_release", "handleLog", "", "level", "", "message", "throwable", "", "attributes", "", "", "tags", "", "timestamp", "", "(ILjava/lang/String;Ljava/lang/Throwable;Ljava/util/Map;Ljava/util/Set;Ljava/lang/Long;)V", "resolveSuffix", "stackTraceElement", "resolveTag", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogcatLogHandler.kt */
public final class LogcatLogHandler implements LogHandler {
    private static final Regex ANONYMOUS_CLASS = new Regex("(\\$\\d+)+$");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String[] IGNORED_CLASS_NAMES;
    /* access modifiers changed from: private */
    public static final String[] IGNORED_PACKAGE_PREFIXES = {"com.datadog.android.timber", "timber.log"};
    private static final int MAX_TAG_LENGTH = 23;
    private final String serviceName;
    private final boolean useClassnameAsTag;

    public LogcatLogHandler(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "serviceName");
        this.serviceName = str;
        this.useClassnameAsTag = z;
    }

    public final String getServiceName$dd_sdk_android_release() {
        return this.serviceName;
    }

    public final boolean getUseClassnameAsTag$dd_sdk_android_release() {
        return this.useClassnameAsTag;
    }

    public void handleLog(int i, String str, Throwable th, Map<String, ? extends Object> map, Set<String> set, Long l) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(map, "attributes");
        Intrinsics.checkNotNullParameter(set, Config.TAGS);
        StackTraceElement callerStackElement$dd_sdk_android_release = getCallerStackElement$dd_sdk_android_release();
        String resolveTag = resolveTag(callerStackElement$dd_sdk_android_release);
        Log.println(i, resolveTag, str + resolveSuffix(callerStackElement$dd_sdk_android_release));
        if (th != null) {
            Log.println(i, resolveTag, Log.getStackTraceString(th));
        }
    }

    private final String resolveTag(StackTraceElement stackTraceElement) {
        String str;
        if (stackTraceElement == null) {
            str = this.serviceName;
        } else {
            String className = stackTraceElement.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "stackTraceElement.className");
            str = StringsKt.substringAfterLast$default(ANONYMOUS_CLASS.replace((CharSequence) className, ""), '.', (String) null, 2, (Object) null);
        }
        if (str.length() < 23 || Build.VERSION.SDK_INT >= 24) {
            return str;
        }
        String substring = str.substring(0, 23);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    private final String resolveSuffix(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null) {
            return "";
        }
        String methodName = stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        return "\t| at ." + methodName + "(" + fileName + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public final StackTraceElement getCallerStackElement$dd_sdk_android_release() {
        if (!Datadog.INSTANCE.isDebug$dd_sdk_android_release() || !this.useClassnameAsTag) {
            return null;
        }
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        return findValidCallStackElement$dd_sdk_android_release(stackTrace);
    }

    @Metadata(mo20734d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo20735d2 = {"Lcom/datadog/android/log/internal/logger/LogcatLogHandler$Companion;", "", "()V", "ANONYMOUS_CLASS", "Lkotlin/text/Regex;", "IGNORED_CLASS_NAMES", "", "", "getIGNORED_CLASS_NAMES$dd_sdk_android_release", "()[Ljava/lang/String;", "[Ljava/lang/String;", "IGNORED_PACKAGE_PREFIXES", "getIGNORED_PACKAGE_PREFIXES$dd_sdk_android_release", "MAX_TAG_LENGTH", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogcatLogHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String[] getIGNORED_CLASS_NAMES$dd_sdk_android_release() {
            return LogcatLogHandler.IGNORED_CLASS_NAMES;
        }

        public final String[] getIGNORED_PACKAGE_PREFIXES$dd_sdk_android_release() {
            return LogcatLogHandler.IGNORED_PACKAGE_PREFIXES;
        }
    }

    static {
        String str = null;
        String[] strArr = new String[7];
        strArr[0] = Logger.class.getCanonicalName();
        strArr[1] = LogHandler.class.getCanonicalName();
        String canonicalName = LogHandler.class.getCanonicalName();
        if (canonicalName != null) {
            str = canonicalName + "$DefaultImpls";
        }
        strArr[2] = str;
        strArr[3] = LogcatLogHandler.class.getCanonicalName();
        strArr[4] = ConditionalLogHandler.class.getCanonicalName();
        strArr[5] = CombinedLogHandler.class.getCanonicalName();
        strArr[6] = DatadogLogHandler.class.getCanonicalName();
        IGNORED_CLASS_NAMES = strArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.StackTraceElement findValidCallStackElement$dd_sdk_android_release(java.lang.StackTraceElement[] r12) {
        /*
            r11 = this;
            java.lang.String r11 = "stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r11)
            int r11 = r12.length
            r0 = 0
            r1 = r0
        L_0x0008:
            r2 = 0
            if (r1 >= r11) goto L_0x0040
            r3 = r12[r1]
            int r1 = r1 + 1
            java.lang.String[] r4 = IGNORED_CLASS_NAMES
            java.lang.String r5 = r3.getClassName()
            boolean r4 = kotlin.collections.ArraysKt.contains((T[]) r4, r5)
            r5 = 1
            if (r4 != 0) goto L_0x003c
            java.lang.String[] r4 = IGNORED_PACKAGE_PREFIXES
            int r6 = r4.length
            r7 = r0
        L_0x0020:
            if (r7 >= r6) goto L_0x0038
            r8 = r4[r7]
            int r7 = r7 + 1
            java.lang.String r9 = r3.getClassName()
            java.lang.String r10 = "element.className"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            r10 = 2
            boolean r8 = kotlin.text.StringsKt.startsWith$default(r9, r8, r0, r10, r2)
            if (r8 == 0) goto L_0x0020
            r2 = r0
            goto L_0x0039
        L_0x0038:
            r2 = r5
        L_0x0039:
            if (r2 == 0) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r5 = r0
        L_0x003d:
            if (r5 == 0) goto L_0x0008
            r2 = r3
        L_0x0040:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.datadog.android.log.internal.logger.LogcatLogHandler.findValidCallStackElement$dd_sdk_android_release(java.lang.StackTraceElement[]):java.lang.StackTraceElement");
    }
}
