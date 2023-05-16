package com.datadog.android.rum.internal.instrumentation.gestures;

import android.content.Context;
import android.view.MotionEvent;
import androidx.core.view.GestureDetectorCompat;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesDetectorWrapper;", "", "context", "Landroid/content/Context;", "gestureListener", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesListener;", "(Landroid/content/Context;Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesListener;)V", "defaultGesturesDetector", "Landroidx/core/view/GestureDetectorCompat;", "(Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesListener;Landroidx/core/view/GestureDetectorCompat;)V", "onTouchEvent", "", "event", "Landroid/view/MotionEvent;", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: GesturesDetectorWrapper.kt */
public final class GesturesDetectorWrapper {
    private final GestureDetectorCompat defaultGesturesDetector;
    private final GesturesListener gestureListener;

    public GesturesDetectorWrapper(GesturesListener gesturesListener, GestureDetectorCompat gestureDetectorCompat) {
        Intrinsics.checkNotNullParameter(gesturesListener, "gestureListener");
        Intrinsics.checkNotNullParameter(gestureDetectorCompat, "defaultGesturesDetector");
        this.gestureListener = gesturesListener;
        this.defaultGesturesDetector = gestureDetectorCompat;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GesturesDetectorWrapper(Context context, GesturesListener gesturesListener) {
        this(gesturesListener, new GestureDetectorCompat(context, gesturesListener));
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        Intrinsics.checkNotNullParameter(gesturesListener, "gestureListener");
    }

    public final void onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        this.defaultGesturesDetector.onTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1) {
            this.gestureListener.onUp(motionEvent);
        }
    }
}
