package com.datadog.android.rum.internal.tracking;

import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0001¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0001J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0001R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer;", "", "()V", "viewsTimeAndState", "Ljava/util/WeakHashMap;", "Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer$ViewLoadingInfo;", "getLoadingTime", "", "view", "(Ljava/lang/Object;)Ljava/lang/Long;", "isFirstTimeLoading", "", "onCreated", "", "onDestroyed", "onFinishedLoading", "onPaused", "onStartLoading", "ViewLoadingInfo", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ViewLoadingTimer.kt */
public final class ViewLoadingTimer {
    private final WeakHashMap<Object, ViewLoadingInfo> viewsTimeAndState = new WeakHashMap<>();

    public final void onCreated(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        this.viewsTimeAndState.put(obj, new ViewLoadingInfo(Long.valueOf(System.nanoTime()), 0, false, false, 14, (DefaultConstructorMarker) null));
    }

    public final void onStartLoading(Object obj) {
        ViewLoadingInfo viewLoadingInfo;
        Intrinsics.checkNotNullParameter(obj, "view");
        if (this.viewsTimeAndState.containsKey(obj)) {
            viewLoadingInfo = this.viewsTimeAndState.get(obj);
        } else {
            ViewLoadingInfo viewLoadingInfo2 = new ViewLoadingInfo(Long.valueOf(System.nanoTime()), 0, false, false, 14, (DefaultConstructorMarker) null);
            this.viewsTimeAndState.put(obj, viewLoadingInfo2);
            viewLoadingInfo = viewLoadingInfo2;
        }
        if (viewLoadingInfo != null && viewLoadingInfo.getLoadingStart() == null) {
            viewLoadingInfo.setLoadingStart(Long.valueOf(System.nanoTime()));
        }
    }

    public final void onFinishedLoading(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        ViewLoadingInfo viewLoadingInfo = this.viewsTimeAndState.get(obj);
        if (viewLoadingInfo != null) {
            Long loadingStart = viewLoadingInfo.getLoadingStart();
            viewLoadingInfo.setLoadingTime(loadingStart != null ? System.nanoTime() - loadingStart.longValue() : 0);
            if (viewLoadingInfo.getFinishedLoadingOnce()) {
                viewLoadingInfo.setFirstTimeLoading(false);
            }
        }
    }

    public final void onDestroyed(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        this.viewsTimeAndState.remove(obj);
    }

    public final void onPaused(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        ViewLoadingInfo viewLoadingInfo = this.viewsTimeAndState.get(obj);
        if (viewLoadingInfo != null) {
            viewLoadingInfo.setLoadingTime(0);
            viewLoadingInfo.setLoadingStart((Long) null);
            viewLoadingInfo.setFirstTimeLoading(false);
            viewLoadingInfo.setFinishedLoadingOnce(true);
        }
    }

    public final Long getLoadingTime(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        ViewLoadingInfo viewLoadingInfo = this.viewsTimeAndState.get(obj);
        if (viewLoadingInfo == null) {
            return null;
        }
        return Long.valueOf(viewLoadingInfo.getLoadingTime());
    }

    public final boolean isFirstTimeLoading(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "view");
        ViewLoadingInfo viewLoadingInfo = this.viewsTimeAndState.get(obj);
        if (viewLoadingInfo == null) {
            return false;
        }
        return viewLoadingInfo.getFirstTimeLoading();
    }

    @Metadata(mo20734d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J8\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006$"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer$ViewLoadingInfo;", "", "loadingStart", "", "loadingTime", "firstTimeLoading", "", "finishedLoadingOnce", "(Ljava/lang/Long;JZZ)V", "getFinishedLoadingOnce", "()Z", "setFinishedLoadingOnce", "(Z)V", "getFirstTimeLoading", "setFirstTimeLoading", "getLoadingStart", "()Ljava/lang/Long;", "setLoadingStart", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getLoadingTime", "()J", "setLoadingTime", "(J)V", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Long;JZZ)Lcom/datadog/android/rum/internal/tracking/ViewLoadingTimer$ViewLoadingInfo;", "equals", "other", "hashCode", "", "toString", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: ViewLoadingTimer.kt */
    private static final class ViewLoadingInfo {
        private boolean finishedLoadingOnce;
        private boolean firstTimeLoading;
        private Long loadingStart;
        private long loadingTime;

        public static /* synthetic */ ViewLoadingInfo copy$default(ViewLoadingInfo viewLoadingInfo, Long l, long j, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                l = viewLoadingInfo.loadingStart;
            }
            if ((i & 2) != 0) {
                j = viewLoadingInfo.loadingTime;
            }
            long j2 = j;
            if ((i & 4) != 0) {
                z = viewLoadingInfo.firstTimeLoading;
            }
            boolean z3 = z;
            if ((i & 8) != 0) {
                z2 = viewLoadingInfo.finishedLoadingOnce;
            }
            return viewLoadingInfo.copy(l, j2, z3, z2);
        }

        public final Long component1() {
            return this.loadingStart;
        }

        public final long component2() {
            return this.loadingTime;
        }

        public final boolean component3() {
            return this.firstTimeLoading;
        }

        public final boolean component4() {
            return this.finishedLoadingOnce;
        }

        public final ViewLoadingInfo copy(Long l, long j, boolean z, boolean z2) {
            return new ViewLoadingInfo(l, j, z, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ViewLoadingInfo)) {
                return false;
            }
            ViewLoadingInfo viewLoadingInfo = (ViewLoadingInfo) obj;
            return Intrinsics.areEqual((Object) this.loadingStart, (Object) viewLoadingInfo.loadingStart) && this.loadingTime == viewLoadingInfo.loadingTime && this.firstTimeLoading == viewLoadingInfo.firstTimeLoading && this.finishedLoadingOnce == viewLoadingInfo.finishedLoadingOnce;
        }

        public int hashCode() {
            Long l = this.loadingStart;
            int hashCode = (((l == null ? 0 : l.hashCode()) * 31) + Long.hashCode(this.loadingTime)) * 31;
            boolean z = this.firstTimeLoading;
            boolean z2 = true;
            if (z) {
                z = true;
            }
            int i = (hashCode + (z ? 1 : 0)) * 31;
            boolean z3 = this.finishedLoadingOnce;
            if (!z3) {
                z2 = z3;
            }
            return i + (z2 ? 1 : 0);
        }

        public String toString() {
            Long l = this.loadingStart;
            long j = this.loadingTime;
            boolean z = this.firstTimeLoading;
            return "ViewLoadingInfo(loadingStart=" + l + ", loadingTime=" + j + ", firstTimeLoading=" + z + ", finishedLoadingOnce=" + this.finishedLoadingOnce + ")";
        }

        public ViewLoadingInfo(Long l, long j, boolean z, boolean z2) {
            this.loadingStart = l;
            this.loadingTime = j;
            this.firstTimeLoading = z;
            this.finishedLoadingOnce = z2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ViewLoadingInfo(Long l, long j, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(l, (i & 2) != 0 ? 0 : j, (i & 4) != 0 ? true : z, (i & 8) != 0 ? false : z2);
        }

        public final Long getLoadingStart() {
            return this.loadingStart;
        }

        public final void setLoadingStart(Long l) {
            this.loadingStart = l;
        }

        public final long getLoadingTime() {
            return this.loadingTime;
        }

        public final void setLoadingTime(long j) {
            this.loadingTime = j;
        }

        public final boolean getFirstTimeLoading() {
            return this.firstTimeLoading;
        }

        public final void setFirstTimeLoading(boolean z) {
            this.firstTimeLoading = z;
        }

        public final boolean getFinishedLoadingOnce() {
            return this.finishedLoadingOnce;
        }

        public final void setFinishedLoadingOnce(boolean z) {
            this.finishedLoadingOnce = z;
        }
    }
}
