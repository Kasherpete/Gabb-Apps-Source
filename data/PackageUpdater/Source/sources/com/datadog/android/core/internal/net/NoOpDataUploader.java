package com.datadog.android.core.internal.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/core/internal/net/NoOpDataUploader;", "Lcom/datadog/android/core/internal/net/DataUploader;", "()V", "upload", "Lcom/datadog/android/core/internal/net/UploadStatus;", "data", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpDataUploader.kt */
public final class NoOpDataUploader implements DataUploader {
    public UploadStatus upload(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, MPDbAdapter.KEY_DATA);
        return UploadStatus.SUCCESS;
    }
}
