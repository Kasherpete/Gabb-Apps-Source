package com.datadog.android.rum.internal.domain.scope;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/domain/scope/ViewUpdatePredicate;", "", "canUpdateView", "", "isViewComplete", "event", "Lcom/datadog/android/rum/internal/domain/scope/RumRawEvent;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ViewUpdatePredicate.kt */
public interface ViewUpdatePredicate {
    boolean canUpdateView(boolean z, RumRawEvent rumRawEvent);
}
