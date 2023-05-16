package com.datadog.android.log.internal.domain;

import android.content.Context;
import com.datadog.android.core.internal.constraints.DataConstraints;
import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.file.advanced.FeatureFileOrchestrator;
import com.datadog.android.core.internal.persistence.file.batch.BatchFileHandler;
import com.datadog.android.core.internal.persistence.file.batch.BatchFilePersistenceStrategy;
import com.datadog.android.core.internal.privacy.ConsentProvider;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.event.EventMapper;
import com.datadog.android.event.MapperSerializer;
import com.datadog.android.log.Logger;
import com.datadog.android.log.internal.LogsFeature;
import com.datadog.android.log.internal.domain.event.LogEventMapperWrapper;
import com.datadog.android.log.internal.domain.event.LogEventSerializer;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.datadog.android.security.Encryption;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/LogFilePersistenceStrategy;", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFilePersistenceStrategy;", "Lcom/datadog/android/log/model/LogEvent;", "consentProvider", "Lcom/datadog/android/core/internal/privacy/ConsentProvider;", "context", "Landroid/content/Context;", "executorService", "Ljava/util/concurrent/ExecutorService;", "internalLogger", "Lcom/datadog/android/log/Logger;", "logEventMapper", "Lcom/datadog/android/event/EventMapper;", "localDataEncryption", "Lcom/datadog/android/security/Encryption;", "(Lcom/datadog/android/core/internal/privacy/ConsentProvider;Landroid/content/Context;Ljava/util/concurrent/ExecutorService;Lcom/datadog/android/log/Logger;Lcom/datadog/android/event/EventMapper;Lcom/datadog/android/security/Encryption;)V", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogFilePersistenceStrategy.kt */
public final class LogFilePersistenceStrategy extends BatchFilePersistenceStrategy<LogEvent> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogFilePersistenceStrategy(ConsentProvider consentProvider, Context context, ExecutorService executorService, Logger logger, EventMapper<LogEvent> eventMapper, Encryption encryption) {
        super(new FeatureFileOrchestrator(consentProvider, context, LogsFeature.LOGS_FEATURE_NAME, executorService, logger), executorService, new MapperSerializer(new LogEventMapperWrapper(eventMapper), new LogEventSerializer((DataConstraints) null, 1, (DefaultConstructorMarker) null)), PayloadDecoration.Companion.getJSON_ARRAY_DECORATION(), RuntimeUtilsKt.getSdkLogger(), BatchFileHandler.Companion.create(RuntimeUtilsKt.getSdkLogger(), encryption));
        Intrinsics.checkNotNullParameter(consentProvider, "consentProvider");
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        Intrinsics.checkNotNullParameter(eventMapper, "logEventMapper");
    }
}
