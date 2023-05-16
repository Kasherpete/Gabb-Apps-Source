package com.datadog.android.rum.internal.domain;

import com.datadog.android.core.internal.persistence.PayloadDecoration;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.core.internal.persistence.file.FileExtKt;
import com.datadog.android.core.internal.persistence.file.FileHandler;
import com.datadog.android.core.internal.persistence.file.FileOrchestrator;
import com.datadog.android.core.internal.persistence.file.batch.BatchFileDataWriter;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.internal.monitor.AdvancedRumMonitor;
import com.datadog.android.rum.internal.monitor.EventType;
import com.datadog.android.rum.model.ActionEvent;
import com.datadog.android.rum.model.ErrorEvent;
import com.datadog.android.rum.model.LongTaskEvent;
import com.datadog.android.rum.model.ResourceEvent;
import com.datadog.android.rum.model.ViewEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001d\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0019H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/RumDataWriter;", "Lcom/datadog/android/core/internal/persistence/file/batch/BatchFileDataWriter;", "", "fileOrchestrator", "Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;", "serializer", "Lcom/datadog/android/core/internal/persistence/Serializer;", "decoration", "Lcom/datadog/android/core/internal/persistence/PayloadDecoration;", "handler", "Lcom/datadog/android/core/internal/persistence/file/FileHandler;", "internalLogger", "Lcom/datadog/android/log/Logger;", "lastViewEventFile", "Ljava/io/File;", "(Lcom/datadog/android/core/internal/persistence/file/FileOrchestrator;Lcom/datadog/android/core/internal/persistence/Serializer;Lcom/datadog/android/core/internal/persistence/PayloadDecoration;Lcom/datadog/android/core/internal/persistence/file/FileHandler;Lcom/datadog/android/log/Logger;Ljava/io/File;)V", "notifyEventSent", "", "viewId", "", "eventType", "Lcom/datadog/android/rum/internal/monitor/EventType;", "onDataWritten", "data", "rawData", "", "onDataWritten$dd_sdk_android_release", "persistViewEvent", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumDataWriter.kt */
public final class RumDataWriter extends BatchFileDataWriter<Object> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String LAST_VIEW_EVENT_DIR_MISSING_MESSAGE = "Directory structure %s for writing last view event doesn't exist.";
    private final File lastViewEventFile;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RumDataWriter(FileOrchestrator fileOrchestrator, Serializer<Object> serializer, PayloadDecoration payloadDecoration, FileHandler fileHandler, Logger logger, File file) {
        super(fileOrchestrator, serializer, payloadDecoration, fileHandler, logger);
        Intrinsics.checkNotNullParameter(fileOrchestrator, "fileOrchestrator");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(payloadDecoration, "decoration");
        Intrinsics.checkNotNullParameter(fileHandler, "handler");
        Intrinsics.checkNotNullParameter(logger, "internalLogger");
        Intrinsics.checkNotNullParameter(file, "lastViewEventFile");
        this.lastViewEventFile = file;
    }

    public void onDataWritten$dd_sdk_android_release(Object obj, byte[] bArr) {
        Intrinsics.checkNotNullParameter(obj, MPDbAdapter.KEY_DATA);
        Intrinsics.checkNotNullParameter(bArr, "rawData");
        if (obj instanceof ViewEvent) {
            persistViewEvent(bArr);
        } else if (obj instanceof ActionEvent) {
            notifyEventSent(((ActionEvent) obj).getView().getId(), EventType.ACTION);
        } else if (obj instanceof ResourceEvent) {
            notifyEventSent(((ResourceEvent) obj).getView().getId(), EventType.RESOURCE);
        } else if (obj instanceof ErrorEvent) {
            ErrorEvent errorEvent = (ErrorEvent) obj;
            if (!Intrinsics.areEqual((Object) errorEvent.getError().isCrash(), (Object) true)) {
                notifyEventSent(errorEvent.getView().getId(), EventType.ERROR);
            }
        } else if (obj instanceof LongTaskEvent) {
            LongTaskEvent longTaskEvent = (LongTaskEvent) obj;
            if (Intrinsics.areEqual((Object) longTaskEvent.getLongTask().isFrozenFrame(), (Object) true)) {
                notifyEventSent(longTaskEvent.getView().getId(), EventType.FROZEN_FRAME);
            } else {
                notifyEventSent(longTaskEvent.getView().getId(), EventType.LONG_TASK);
            }
        }
    }

    private final void persistViewEvent(byte[] bArr) {
        File parentFile = this.lastViewEventFile.getParentFile();
        if (parentFile != null && FileExtKt.existsSafe(parentFile)) {
            getHandler$dd_sdk_android_release().writeData(this.lastViewEventFile, bArr, false);
            return;
        }
        Logger sdkLogger = RuntimeUtilsKt.getSdkLogger();
        String format = String.format(Locale.US, LAST_VIEW_EVENT_DIR_MISSING_MESSAGE, Arrays.copyOf(new Object[]{this.lastViewEventFile.getParent()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, this, *args)");
        Logger.i$default(sdkLogger, format, (Throwable) null, (Map) null, 6, (Object) null);
    }

    private final void notifyEventSent(String str, EventType eventType) {
        RumMonitor rumMonitor = GlobalRum.get();
        if (rumMonitor instanceof AdvancedRumMonitor) {
            ((AdvancedRumMonitor) rumMonitor).eventSent(str, eventType);
        }
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/RumDataWriter$Companion;", "", "()V", "LAST_VIEW_EVENT_DIR_MISSING_MESSAGE", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: RumDataWriter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
