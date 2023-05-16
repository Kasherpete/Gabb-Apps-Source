package com.gabb.packageupdater.network.download;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/download/DownloadStatus;", "", "()V", "Error", "Finished", "Progress", "Started", "Lcom/gabb/packageupdater/network/download/DownloadStatus$Started;", "Lcom/gabb/packageupdater/network/download/DownloadStatus$Progress;", "Lcom/gabb/packageupdater/network/download/DownloadStatus$Finished;", "Lcom/gabb/packageupdater/network/download/DownloadStatus$Error;", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DownloadStatus.kt */
public abstract class DownloadStatus {
    public /* synthetic */ DownloadStatus(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/download/DownloadStatus$Started;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;", "()V", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DownloadStatus.kt */
    public static final class Started extends DownloadStatus {
        public static final Started INSTANCE = new Started();

        private Started() {
            super((DefaultConstructorMarker) null);
        }
    }

    private DownloadStatus() {
    }

    @Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/download/DownloadStatus$Progress;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;", "percent", "", "(I)V", "getPercent", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DownloadStatus.kt */
    public static final class Progress extends DownloadStatus {
        private final int percent;

        public static /* synthetic */ Progress copy$default(Progress progress, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = progress.percent;
            }
            return progress.copy(i);
        }

        public final int component1() {
            return this.percent;
        }

        public final Progress copy(int i) {
            return new Progress(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Progress) && this.percent == ((Progress) obj).percent;
        }

        public int hashCode() {
            return Integer.hashCode(this.percent);
        }

        public String toString() {
            return "Progress(percent=" + this.percent + ')';
        }

        public Progress(int i) {
            super((DefaultConstructorMarker) null);
            this.percent = i;
        }

        public final int getPercent() {
            return this.percent;
        }
    }

    @Metadata(mo20734d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/download/DownloadStatus$Finished;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;", "()V", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DownloadStatus.kt */
    public static final class Finished extends DownloadStatus {
        public static final Finished INSTANCE = new Finished();

        private Finished() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo20735d2 = {"Lcom/gabb/packageupdater/network/download/DownloadStatus$Error;", "Lcom/gabb/packageupdater/network/download/DownloadStatus;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_productionRelease"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DownloadStatus.kt */
    public static final class Error extends DownloadStatus {
        private final Throwable throwable;

        public static /* synthetic */ Error copy$default(Error error, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                th = error.throwable;
            }
            return error.copy(th);
        }

        public final Throwable component1() {
            return this.throwable;
        }

        public final Error copy(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "throwable");
            return new Error(th);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Error) obj).throwable);
        }

        public int hashCode() {
            return this.throwable.hashCode();
        }

        public String toString() {
            return "Error(throwable=" + this.throwable + ')';
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Error(Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(th, "throwable");
            this.throwable = th;
        }

        public final Throwable getThrowable() {
            return this.throwable;
        }
    }
}
