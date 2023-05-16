package com.datadog.android.core.internal.persistence.file;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000  2\u00020\u0001:\u0001 BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig;", "", "recentDelayMs", "", "maxBatchSize", "maxItemSize", "maxItemsPerBatch", "", "oldFileThreshold", "maxDiskSpace", "(JJJIJJ)V", "getMaxBatchSize", "()J", "getMaxDiskSpace", "getMaxItemSize", "getMaxItemsPerBatch", "()I", "getOldFileThreshold", "getRecentDelayMs", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: FilePersistenceConfig.kt */
public final class FilePersistenceConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long MAX_BATCH_SIZE = 4194304;
    public static final long MAX_DELAY_BETWEEN_MESSAGES_MS = 5000;
    public static final long MAX_DISK_SPACE = 536870912;
    public static final int MAX_ITEMS_PER_BATCH = 500;
    public static final long MAX_ITEM_SIZE = 524288;
    public static final long OLD_FILE_THRESHOLD = 64800000;
    private final long maxBatchSize;
    private final long maxDiskSpace;
    private final long maxItemSize;
    private final int maxItemsPerBatch;
    private final long oldFileThreshold;
    private final long recentDelayMs;

    public FilePersistenceConfig() {
        this(0, 0, 0, 0, 0, 0, 63, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ FilePersistenceConfig copy$default(FilePersistenceConfig filePersistenceConfig, long j, long j2, long j3, int i, long j4, long j5, int i2, Object obj) {
        FilePersistenceConfig filePersistenceConfig2 = filePersistenceConfig;
        return filePersistenceConfig.copy((i2 & 1) != 0 ? filePersistenceConfig2.recentDelayMs : j, (i2 & 2) != 0 ? filePersistenceConfig2.maxBatchSize : j2, (i2 & 4) != 0 ? filePersistenceConfig2.maxItemSize : j3, (i2 & 8) != 0 ? filePersistenceConfig2.maxItemsPerBatch : i, (i2 & 16) != 0 ? filePersistenceConfig2.oldFileThreshold : j4, (i2 & 32) != 0 ? filePersistenceConfig2.maxDiskSpace : j5);
    }

    public final long component1() {
        return this.recentDelayMs;
    }

    public final long component2() {
        return this.maxBatchSize;
    }

    public final long component3() {
        return this.maxItemSize;
    }

    public final int component4() {
        return this.maxItemsPerBatch;
    }

    public final long component5() {
        return this.oldFileThreshold;
    }

    public final long component6() {
        return this.maxDiskSpace;
    }

    public final FilePersistenceConfig copy(long j, long j2, long j3, int i, long j4, long j5) {
        return new FilePersistenceConfig(j, j2, j3, i, j4, j5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePersistenceConfig)) {
            return false;
        }
        FilePersistenceConfig filePersistenceConfig = (FilePersistenceConfig) obj;
        return this.recentDelayMs == filePersistenceConfig.recentDelayMs && this.maxBatchSize == filePersistenceConfig.maxBatchSize && this.maxItemSize == filePersistenceConfig.maxItemSize && this.maxItemsPerBatch == filePersistenceConfig.maxItemsPerBatch && this.oldFileThreshold == filePersistenceConfig.oldFileThreshold && this.maxDiskSpace == filePersistenceConfig.maxDiskSpace;
    }

    public int hashCode() {
        return (((((((((Long.hashCode(this.recentDelayMs) * 31) + Long.hashCode(this.maxBatchSize)) * 31) + Long.hashCode(this.maxItemSize)) * 31) + Integer.hashCode(this.maxItemsPerBatch)) * 31) + Long.hashCode(this.oldFileThreshold)) * 31) + Long.hashCode(this.maxDiskSpace);
    }

    public String toString() {
        long j = this.recentDelayMs;
        long j2 = this.maxBatchSize;
        long j3 = this.maxItemSize;
        int i = this.maxItemsPerBatch;
        long j4 = this.oldFileThreshold;
        return "FilePersistenceConfig(recentDelayMs=" + j + ", maxBatchSize=" + j2 + ", maxItemSize=" + j3 + ", maxItemsPerBatch=" + i + ", oldFileThreshold=" + j4 + ", maxDiskSpace=" + this.maxDiskSpace + ")";
    }

    public FilePersistenceConfig(long j, long j2, long j3, int i, long j4, long j5) {
        this.recentDelayMs = j;
        this.maxBatchSize = j2;
        this.maxItemSize = j3;
        this.maxItemsPerBatch = i;
        this.oldFileThreshold = j4;
        this.maxDiskSpace = j5;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FilePersistenceConfig(long j, long j2, long j3, int i, long j4, long j5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 5000 : j, (i2 & 2) != 0 ? MAX_BATCH_SIZE : j2, (i2 & 4) != 0 ? MAX_ITEM_SIZE : j3, (i2 & 8) != 0 ? 500 : i, (i2 & 16) != 0 ? OLD_FILE_THRESHOLD : j4, (i2 & 32) != 0 ? MAX_DISK_SPACE : j5);
    }

    public final long getRecentDelayMs() {
        return this.recentDelayMs;
    }

    public final long getMaxBatchSize() {
        return this.maxBatchSize;
    }

    public final long getMaxItemSize() {
        return this.maxItemSize;
    }

    public final int getMaxItemsPerBatch() {
        return this.maxItemsPerBatch;
    }

    public final long getOldFileThreshold() {
        return this.oldFileThreshold;
    }

    public final long getMaxDiskSpace() {
        return this.maxDiskSpace;
    }

    @Metadata(mo20734d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/FilePersistenceConfig$Companion;", "", "()V", "MAX_BATCH_SIZE", "", "MAX_DELAY_BETWEEN_MESSAGES_MS", "MAX_DISK_SPACE", "MAX_ITEMS_PER_BATCH", "", "MAX_ITEM_SIZE", "OLD_FILE_THRESHOLD", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: FilePersistenceConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
