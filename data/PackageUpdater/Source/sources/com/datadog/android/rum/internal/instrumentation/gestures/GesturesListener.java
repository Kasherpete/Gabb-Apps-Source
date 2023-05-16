package com.datadog.android.rum.internal.instrumentation.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import androidx.core.view.ScrollingView;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.Logger;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.RumMonitor;
import com.datadog.android.rum.tracking.InteractionPredicate;
import com.datadog.android.rum.tracking.NoOpInteractionPredicate;
import com.datadog.android.rum.tracking.ViewAttributesProvider;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0000\u0018\u0000 E2\u00020\u0001:\u0001EB-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\"\u0010\u001c\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011H\u0002J\"\u0010\u001f\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011H\u0002J\u001a\u0010 \u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00162\u0006\u0010!\u001a\u00020\u001bH\u0002J6\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160&2\u0006\u0010\f\u001a\u00020\rH\u0002J(\u0010'\u001a\u00020(2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\rH\u0002J\u0010\u0010*\u001a\u00020(2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010+\u001a\u00020(2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010,\u001a\u00020(2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020(2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0010\u0010.\u001a\u00020(2\u0006\u0010!\u001a\u00020\u001bH\u0016J(\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0011H\u0016J\u0010\u00104\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001bH\u0016J(\u00105\u001a\u00020(2\u0006\u00100\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020\u00112\u0006\u00108\u001a\u00020\u0011H\u0016J\u0010\u00109\u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u001bH\u0016J\u0010\u0010:\u001a\u00020(2\u0006\u0010!\u001a\u00020\u001bH\u0016J\u000e\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u00020\u001bJ\b\u0010=\u001a\u00020\u0018H\u0002J.\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010@0?2\u0006\u0010A\u001a\u00020\u00162\u0006\u0010B\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020\u001bH\u0002R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0003X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006F"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesListener;", "Landroid/view/GestureDetector$OnGestureListener;", "windowReference", "Ljava/lang/ref/WeakReference;", "Landroid/view/Window;", "attributesProviders", "", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "interactionPredicate", "Lcom/datadog/android/rum/tracking/InteractionPredicate;", "(Ljava/lang/ref/WeakReference;[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;Lcom/datadog/android/rum/tracking/InteractionPredicate;)V", "[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "coordinatesContainer", "", "gestureDirection", "", "onTouchDownXPos", "", "onTouchDownYPos", "scrollEventType", "Lcom/datadog/android/rum/RumActionType;", "scrollTargetReference", "Landroid/view/View;", "closeScrollOrSwipeEventIfAny", "", "decorView", "onUpEvent", "Landroid/view/MotionEvent;", "findTargetForScroll", "x", "y", "findTargetForTap", "handleTapUp", "e", "handleViewGroup", "view", "Landroid/view/ViewGroup;", "stack", "Ljava/util/LinkedList;", "hitTest", "", "container", "isJetpackComposeView", "isScrollableView", "isValidScrollableTarget", "isValidTapTarget", "onDown", "onFling", "startDownEvent", "endUpEvent", "velocityX", "velocityY", "onLongPress", "onScroll", "currentMoveEvent", "distanceX", "distanceY", "onShowPress", "onSingleTapUp", "onUp", "event", "resetScrollEventParameters", "resolveAttributes", "", "", "scrollTarget", "targetId", "resolveGestureDirection", "endEvent", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: GesturesListener.kt */
public final class GesturesListener implements GestureDetector.OnGestureListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String MSG_NO_TARGET_SCROLL_SWIPE;
    /* access modifiers changed from: private */
    public static final String MSG_NO_TARGET_TAP = ("We could not find a valid target for the " + RumActionType.TAP.name() + " event.The DecorView was empty and either transparent or not clickable for this Activity.");
    public static final String SCROLL_DIRECTION_DOWN = "down";
    public static final String SCROLL_DIRECTION_LEFT = "left";
    public static final String SCROLL_DIRECTION_RIGHT = "right";
    public static final String SCROLL_DIRECTION_UP = "up";
    private final ViewAttributesProvider[] attributesProviders;
    private final int[] coordinatesContainer;
    private String gestureDirection;
    private final InteractionPredicate interactionPredicate;
    private float onTouchDownXPos;
    private float onTouchDownYPos;
    private RumActionType scrollEventType;
    private WeakReference<View> scrollTargetReference;
    private final WeakReference<Window> windowReference;

    public void onLongPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
    }

    public void onShowPress(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
    }

    public GesturesListener(WeakReference<Window> weakReference, ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate2) {
        Intrinsics.checkNotNullParameter(weakReference, "windowReference");
        Intrinsics.checkNotNullParameter(viewAttributesProviderArr, "attributesProviders");
        Intrinsics.checkNotNullParameter(interactionPredicate2, "interactionPredicate");
        this.windowReference = weakReference;
        this.attributesProviders = viewAttributesProviderArr;
        this.interactionPredicate = interactionPredicate2;
        this.coordinatesContainer = new int[2];
        this.gestureDirection = "";
        this.scrollTargetReference = new WeakReference<>((Object) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GesturesListener(WeakReference weakReference, ViewAttributesProvider[] viewAttributesProviderArr, InteractionPredicate interactionPredicate2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(weakReference, (i & 2) != 0 ? new ViewAttributesProvider[0] : viewAttributesProviderArr, (i & 4) != 0 ? new NoOpInteractionPredicate() : interactionPredicate2);
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        Window window = (Window) this.windowReference.get();
        handleTapUp(window == null ? null : window.getDecorView(), motionEvent);
        return false;
    }

    public boolean onDown(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        resetScrollEventParameters();
        this.onTouchDownXPos = motionEvent.getX();
        this.onTouchDownYPos = motionEvent.getY();
        return false;
    }

    public final void onUp(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Window window = (Window) this.windowReference.get();
        closeScrollOrSwipeEventIfAny(window == null ? null : window.getDecorView(), motionEvent);
        resetScrollEventParameters();
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        Intrinsics.checkNotNullParameter(motionEvent, "startDownEvent");
        Intrinsics.checkNotNullParameter(motionEvent2, "endUpEvent");
        this.scrollEventType = RumActionType.SWIPE;
        return false;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        View findTargetForScroll;
        Intrinsics.checkNotNullParameter(motionEvent, "startDownEvent");
        Intrinsics.checkNotNullParameter(motionEvent2, "currentMoveEvent");
        RumMonitor rumMonitor = GlobalRum.get();
        Window window = (Window) this.windowReference.get();
        View decorView = window == null ? null : window.getDecorView();
        if (!(decorView == null || this.scrollEventType != null || (findTargetForScroll = findTargetForScroll(decorView, motionEvent.getX(), motionEvent.getY())) == null)) {
            this.scrollTargetReference = new WeakReference<>(findTargetForScroll);
            rumMonitor.startUserAction(RumActionType.CUSTOM, "", MapsKt.emptyMap());
            this.scrollEventType = RumActionType.SCROLL;
        }
        return false;
    }

    private final void closeScrollOrSwipeEventIfAny(View view, MotionEvent motionEvent) {
        RumActionType rumActionType = this.scrollEventType;
        if (rumActionType != null) {
            RumMonitor rumMonitor = GlobalRum.get();
            View view2 = (View) this.scrollTargetReference.get();
            if (view != null && view2 != null) {
                rumMonitor.stopUserAction(rumActionType, GesturesUtilsKt.resolveTargetName(this.interactionPredicate, view2), resolveAttributes(view2, GesturesUtilsKt.resourceIdName(view2.getId()), motionEvent));
            }
        }
    }

    private final Map<String, Object> resolveAttributes(View view, String str, MotionEvent motionEvent) {
        int i = 0;
        Map<String, Object> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m78to(RumAttributes.ACTION_TARGET_CLASS_NAME, GesturesUtilsKt.targetClassName(view)), TuplesKt.m78to(RumAttributes.ACTION_TARGET_RESOURCE_ID, str));
        String resolveGestureDirection = resolveGestureDirection(motionEvent);
        this.gestureDirection = resolveGestureDirection;
        mutableMapOf.put(RumAttributes.ACTION_GESTURE_DIRECTION, resolveGestureDirection);
        ViewAttributesProvider[] viewAttributesProviderArr = this.attributesProviders;
        int length = viewAttributesProviderArr.length;
        while (i < length) {
            ViewAttributesProvider viewAttributesProvider = viewAttributesProviderArr[i];
            i++;
            viewAttributesProvider.extractAttributes(view, mutableMapOf);
        }
        return mutableMapOf;
    }

    private final void resetScrollEventParameters() {
        this.scrollTargetReference.clear();
        this.scrollEventType = null;
        this.gestureDirection = "";
        this.onTouchDownYPos = 0.0f;
        this.onTouchDownXPos = 0.0f;
    }

    private final void handleTapUp(View view, MotionEvent motionEvent) {
        View findTargetForTap;
        if (view != null && (findTargetForTap = findTargetForTap(view, motionEvent.getX(), motionEvent.getY())) != null) {
            int i = 0;
            Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m78to(RumAttributes.ACTION_TARGET_CLASS_NAME, GesturesUtilsKt.targetClassName(findTargetForTap)), TuplesKt.m78to(RumAttributes.ACTION_TARGET_RESOURCE_ID, GesturesUtilsKt.resourceIdName(findTargetForTap.getId())));
            ViewAttributesProvider[] viewAttributesProviderArr = this.attributesProviders;
            int length = viewAttributesProviderArr.length;
            while (i < length) {
                ViewAttributesProvider viewAttributesProvider = viewAttributesProviderArr[i];
                i++;
                viewAttributesProvider.extractAttributes(findTargetForTap, mutableMapOf);
            }
            GlobalRum.get().addUserAction(RumActionType.TAP, GesturesUtilsKt.resolveTargetName(this.interactionPredicate, findTargetForTap), mutableMapOf);
        }
    }

    private final View findTargetForTap(View view, float f, float f2) {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst(view);
        View view2 = null;
        boolean z = true;
        while (!linkedList.isEmpty()) {
            View view3 = (View) linkedList.removeFirst();
            if (linkedList.isEmpty()) {
                Intrinsics.checkNotNullExpressionValue(view3, "view");
                if (isJetpackComposeView(view3)) {
                    z = false;
                }
            }
            boolean z2 = z;
            Intrinsics.checkNotNullExpressionValue(view3, "view");
            View view4 = isValidTapTarget(view3) ? view3 : view2;
            if (view3 instanceof ViewGroup) {
                handleViewGroup((ViewGroup) view3, f, f2, linkedList, this.coordinatesContainer);
            }
            z = z2;
            view2 = view4;
        }
        if (view2 == null && z) {
            Logger.i$default(RuntimeUtilsKt.getDevLogger(), MSG_NO_TARGET_TAP, (Throwable) null, (Map) null, 6, (Object) null);
        }
        return view2;
    }

    private final View findTargetForScroll(View view, float f, float f2) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(view);
        boolean z = true;
        while (!linkedList.isEmpty()) {
            View view2 = (View) linkedList.removeFirst();
            if (linkedList.isEmpty()) {
                Intrinsics.checkNotNullExpressionValue(view2, "view");
                if (isJetpackComposeView(view2)) {
                    z = false;
                }
            }
            boolean z2 = z;
            Intrinsics.checkNotNullExpressionValue(view2, "view");
            if (isValidScrollableTarget(view2)) {
                return view2;
            }
            if (view2 instanceof ViewGroup) {
                handleViewGroup((ViewGroup) view2, f, f2, linkedList, this.coordinatesContainer);
            }
            z = z2;
        }
        if (!z) {
            return null;
        }
        Logger.i$default(RuntimeUtilsKt.getDevLogger(), MSG_NO_TARGET_SCROLL_SWIPE, (Throwable) null, (Map) null, 6, (Object) null);
        return null;
    }

    private final void handleViewGroup(ViewGroup viewGroup, float f, float f2, LinkedList<View> linkedList, int[] iArr) {
        int childCount = viewGroup.getChildCount();
        int i = 0;
        while (i < childCount) {
            int i2 = i + 1;
            View childAt = viewGroup.getChildAt(i);
            Intrinsics.checkNotNullExpressionValue(childAt, "child");
            if (hitTest(childAt, f, f2, iArr)) {
                linkedList.add(childAt);
            }
            i = i2;
        }
    }

    private final boolean isValidTapTarget(View view) {
        return view.isClickable() && view.getVisibility() == 0;
    }

    private final boolean isValidScrollableTarget(View view) {
        return view.getVisibility() == 0 && isScrollableView(view);
    }

    private final boolean isScrollableView(View view) {
        return ScrollingView.class.isAssignableFrom(view.getClass()) || AbsListView.class.isAssignableFrom(view.getClass());
    }

    private final boolean hitTest(View view, float f, float f2, int[] iArr) {
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        int width = view.getWidth();
        int height = view.getHeight();
        if (f < ((float) i) || f > ((float) (i + width)) || f2 < ((float) i2) || f2 > ((float) (i2 + height))) {
            return false;
        }
        return true;
    }

    private final String resolveGestureDirection(MotionEvent motionEvent) {
        float x = motionEvent.getX() - this.onTouchDownXPos;
        float y = motionEvent.getY() - this.onTouchDownYPos;
        return Math.abs(x) > Math.abs(y) ? x > 0.0f ? SCROLL_DIRECTION_RIGHT : SCROLL_DIRECTION_LEFT : y > 0.0f ? SCROLL_DIRECTION_DOWN : SCROLL_DIRECTION_UP;
    }

    private final boolean isJetpackComposeView(View view) {
        String name = view.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "view::class.java.name");
        return StringsKt.startsWith$default(name, "androidx.compose.ui.platform.ComposeView", false, 2, (Object) null);
    }

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesListener$Companion;", "", "()V", "MSG_NO_TARGET_SCROLL_SWIPE", "", "getMSG_NO_TARGET_SCROLL_SWIPE$dd_sdk_android_release", "()Ljava/lang/String;", "MSG_NO_TARGET_TAP", "getMSG_NO_TARGET_TAP$dd_sdk_android_release", "SCROLL_DIRECTION_DOWN", "SCROLL_DIRECTION_LEFT", "SCROLL_DIRECTION_RIGHT", "SCROLL_DIRECTION_UP", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: GesturesListener.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getMSG_NO_TARGET_TAP$dd_sdk_android_release() {
            return GesturesListener.MSG_NO_TARGET_TAP;
        }

        public final String getMSG_NO_TARGET_SCROLL_SWIPE$dd_sdk_android_release() {
            return GesturesListener.MSG_NO_TARGET_SCROLL_SWIPE;
        }
    }

    static {
        String name = RumActionType.SCROLL.name();
        MSG_NO_TARGET_SCROLL_SWIPE = "We could not find a valid target for the " + name + " or " + RumActionType.SWIPE.name() + " event. The DecorView was empty and either transparent or not clickable for this Activity.";
    }
}
