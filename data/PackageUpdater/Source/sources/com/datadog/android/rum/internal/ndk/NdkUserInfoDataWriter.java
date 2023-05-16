package com.datadog.android.rum.internal.ndk;

import android.content.Context;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.advanced.ConsentAwareFileMigrator;
import com.datadog.android.core.internal.persistence.file.advanced.ConsentAwareFileOrchestrator;
import com.datadog.android.core.internal.persistence.file.single.SingleFileOrchestrator;
import com.datadog.android.core.internal.persistence.file.single.SingleItemDataWriter;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.core.model.UserInfo;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.user.UserInfoSerializer;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\r¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/ndk/NdkUserInfoDataWriter;", "Lcom/datadog/android/core/internal/persistence/file/single/SingleItemDataWriter;", "Lcom/datadog/android/core/model/UserInfo;", "context", "Landroid/content/Context;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "executorService", "Ljava/util/concurrent/ExecutorService;", "fileHandler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "(Landroid/content/Context;Lcom/datadog/android/core/internal/privacy/ConsentProvider;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/log/Logger;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NdkUserInfoDataWriter.kt */
public final class NdkUserInfoDataWriter extends SingleItemDataWriter<UserInfo> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NdkUserInfoDataWriter(Context context, ConsentProvider consentProvider, ExecutorService executorService, FileHandler fileHandler, Logger logger) {
        super(new ConsentAwareFileOrchestrator(consentProvider, new SingleFileOrchestrator(DatadogNdkCrashHandler.Companion.getPendingUserInfoFile$dd_sdk_android_release(context)), new SingleFileOrchestrator(DatadogNdkCrashHandler.Companion.getGrantedUserInfoFile$dd_sdk_android_release(context)), new ConsentAwareFileMigrator(fileHandler, executorService, logger)), new UserInfoSerializer(), fileHandler, logger);
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(consentProvider, "consentProvider");
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        Intrinsics.checkNotNullParameter(fileHandler, "fileHandler");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
    }
}
