package androidx.window.layout;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "Landroidx/window/layout/WindowMetricsCalculator;", "it", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WindowMetricsCalculator.kt */
final class WindowMetricsCalculator$Companion$decorator$1 extends Lambda implements Function1<WindowMetricsCalculator, WindowMetricsCalculator> {
    public static final WindowMetricsCalculator$Companion$decorator$1 INSTANCE = new WindowMetricsCalculator$Companion$decorator$1();

    WindowMetricsCalculator$Companion$decorator$1() {
        super(1);
    }

    public final WindowMetricsCalculator invoke(WindowMetricsCalculator windowMetricsCalculator) {
        Intrinsics.checkNotNullParameter(windowMetricsCalculator, "it");
        return windowMetricsCalculator;
    }
}
