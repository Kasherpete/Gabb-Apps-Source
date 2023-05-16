package com.datadog.android.core.internal.utils;

import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.tracking.ComponentPredicate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0006\u001aC\u0010\u0007\u001a\u00020\b\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\nH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0007\n\u0005\b20\u0001¨\u0006\f"}, mo20735d2 = {"resolveViewName", "", "T", "", "Lcom/datadog/android/rum/tracking/ComponentPredicate;", "component", "(Lcom/datadog/android/rum/tracking/ComponentPredicate;Ljava/lang/Object;)Ljava/lang/String;", "runIfValid", "", "operation", "Lkotlin/Function1;", "(Lcom/datadog/android/rum/tracking/ComponentPredicate;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "dd-sdk-android_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ComponentPredicateExt.kt */
public final class ComponentPredicateExtKt {
    public static final /* synthetic */ <T> void runIfValid(ComponentPredicate<T> componentPredicate, T t, Function1<? super T, Unit> function1) {
        Intrinsics.checkNotNullParameter(componentPredicate, "<this>");
        Intrinsics.checkNotNullParameter(t, "component");
        Intrinsics.checkNotNullParameter(function1, "operation");
        if (componentPredicate.accept(t)) {
            try {
                function1.invoke(t);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Internal operation failed", e, (Map) null, 4, (Object) null);
            }
        }
    }

    public static final /* synthetic */ <T> String resolveViewName(ComponentPredicate<T> componentPredicate, T t) {
        Intrinsics.checkNotNullParameter(componentPredicate, "<this>");
        Intrinsics.checkNotNullParameter(t, "component");
        String viewName = componentPredicate.getViewName(t);
        CharSequence charSequence = viewName;
        return charSequence == null || StringsKt.isBlank(charSequence) ? ViewUtilsKt.resolveViewUrl((Object) t) : viewName;
    }
}
