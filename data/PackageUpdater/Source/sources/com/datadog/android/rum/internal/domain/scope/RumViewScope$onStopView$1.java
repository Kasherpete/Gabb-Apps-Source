package com.datadog.android.rum.internal.domain.scope;

import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.internal.domain.RumContext;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "currentContext", "Lcom/datadog/android/rum/internal/domain/RumContext;", "invoke", "(Lcom/datadog/android/rum/internal/domain/RumContext;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: RumViewScope.kt */
final class RumViewScope$onStopView$1 extends Lambda implements Function1<RumContext, Boolean> {
    final /* synthetic */ RumViewScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RumViewScope$onStopView$1(RumViewScope rumViewScope) {
        super(1);
        this.this$0 = rumViewScope;
    }

    public final Boolean invoke(RumContext rumContext) {
        Intrinsics.checkNotNullParameter(rumContext, "currentContext");
        boolean z = true;
        if (Intrinsics.areEqual((Object) rumContext.getSessionId(), (Object) this.this$0.sessionId) && !Intrinsics.areEqual((Object) rumContext.getViewId(), (Object) this.this$0.getViewId$dd_sdk_android_release())) {
            LogUtilsKt.debugWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), RumViewScope.RUM_CONTEXT_UPDATE_IGNORED_AT_STOP_VIEW_MESSAGE, (Throwable) null, (Map) null, 6, (Object) null);
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
