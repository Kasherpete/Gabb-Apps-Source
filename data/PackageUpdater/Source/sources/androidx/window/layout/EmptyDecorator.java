package androidx.window.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, mo20735d2 = {"Landroidx/window/layout/EmptyDecorator;", "Landroidx/window/layout/WindowInfoTrackerDecorator;", "()V", "decorate", "Landroidx/window/layout/WindowInfoTracker;", "tracker", "window_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WindowInfoTracker.kt */
final class EmptyDecorator implements WindowInfoTrackerDecorator {
    public static final EmptyDecorator INSTANCE = new EmptyDecorator();

    public WindowInfoTracker decorate(WindowInfoTracker windowInfoTracker) {
        Intrinsics.checkNotNullParameter(windowInfoTracker, "tracker");
        return windowInfoTracker;
    }

    private EmptyDecorator() {
    }
}
