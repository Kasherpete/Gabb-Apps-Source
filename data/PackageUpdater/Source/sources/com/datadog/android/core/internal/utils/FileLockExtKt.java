package com.datadog.android.core.internal.utils;

import java.io.IOException;
import java.nio.channels.FileLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00010\u0004H\bø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0006"}, mo20735d2 = {"use", "R", "Ljava/nio/channels/FileLock;", "block", "Lkotlin/Function1;", "(Ljava/nio/channels/FileLock;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FileLockExt.kt */
public final class FileLockExtKt {
    public static final /* synthetic */ <R> R use(FileLock fileLock, Function1<? super FileLock, ? extends R> function1) throws IOException {
        Intrinsics.checkNotNullParameter(fileLock, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        try {
            return function1.invoke(fileLock);
        } finally {
            InlineMarker.finallyStart(1);
            fileLock.release();
            InlineMarker.finallyEnd(1);
        }
    }
}
