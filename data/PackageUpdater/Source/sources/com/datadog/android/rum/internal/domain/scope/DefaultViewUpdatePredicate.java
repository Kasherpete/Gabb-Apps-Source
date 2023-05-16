package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.rum.internal.domain.scope.RumRawEvent;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/DefaultViewUpdatePredicate;", "Lcom/datadog/android/rum/internal/domain/scope/ViewUpdatePredicate;", "viewUpdateThreshold", "", "(J)V", "lastViewUpdateTimestamp", "canUpdateView", "", "isViewComplete", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: DefaultViewUpdatePredicate.kt */
public final class DefaultViewUpdatePredicate implements ViewUpdatePredicate {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final long VIEW_UPDATE_THRESHOLD_IN_NS = TimeUnit.SECONDS.toNanos(30);
    private long lastViewUpdateTimestamp;
    private final long viewUpdateThreshold;

    public DefaultViewUpdatePredicate() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public DefaultViewUpdatePredicate(long j) {
        this.viewUpdateThreshold = j;
        this.lastViewUpdateTimestamp = System.nanoTime() - VIEW_UPDATE_THRESHOLD_IN_NS;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultViewUpdatePredicate(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? VIEW_UPDATE_THRESHOLD_IN_NS : j);
    }

    public boolean canUpdateView(boolean z, RumRawEvent rumRawEvent) {
        Intrinsics.checkNotNullParameter(rumRawEvent, "event");
        boolean z2 = (rumRawEvent instanceof RumRawEvent.AddError) && ((RumRawEvent.AddError) rumRawEvent).isFatal();
        boolean z3 = System.nanoTime() - this.lastViewUpdateTimestamp > this.viewUpdateThreshold;
        if (!z && !z2 && !z3) {
            return false;
        }
        this.lastViewUpdateTimestamp = System.nanoTime();
        return true;
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/DefaultViewUpdatePredicate$Companion;", "", "()V", "VIEW_UPDATE_THRESHOLD_IN_NS", "", "getVIEW_UPDATE_THRESHOLD_IN_NS$dd_sdk_android_release", "()J", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: DefaultViewUpdatePredicate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getVIEW_UPDATE_THRESHOLD_IN_NS$dd_sdk_android_release() {
            return DefaultViewUpdatePredicate.VIEW_UPDATE_THRESHOLD_IN_NS;
        }
    }
}
