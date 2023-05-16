package com.datadog.android.rum.internal.instrumentation.gestures;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.datadog.android.core.internal.utils.RuntimeUtilsKt;
import com.datadog.android.log.internal.utils.LogUtilsKt;
import com.datadog.android.rum.GlobalRum;
import com.datadog.android.rum.RumActionType;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.tracking.InteractionPredicate;
import com.datadog.android.rum.tracking.NoOpInteractionPredicate;
import com.datadog.android.rum.tracking.ViewAttributesProvider;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 I2\u00020\u0001:\u0001IBM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\u0010\u000fJ\u0019\u0010 \u001a\u00020!2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010\u000b0\u000bH\u0001J\u0012\u0010#\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0019\u0010&\u001a\u00020!2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010%0%H\u0001J\u0019\u0010'\u001a\u00020!2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010(0(H\u0001J\u0012\u0010)\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010\u000bH\u0016J\u0019\u0010*\u001a\u00020!2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010\u000b0\u000bH\u0001J\u0010\u0010+\u001a\u00020,2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010-\u001a\u00020,H\u0002J\u0019\u0010.\u001a\u00020,2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010/0/H\u0001J\u0019\u00100\u001a\u00020,2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010/0/H\u0001J\t\u00101\u001a\u00020,H\u0001J\t\u00102\u001a\u00020,H\u0001J\u001b\u00103\u001a\u00020!2\u0006\u0010\"\u001a\u0002042\b\b\u0001\u00105\u001a\u000206H\u0001J\u0013\u00107\u001a\u0004\u0018\u0001082\u0006\u0010\"\u001a\u000204H\u0001J\t\u00109\u001a\u00020,H\u0001J\u0018\u0010:\u001a\u00020!2\u0006\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020=H\u0016J\u001b\u0010>\u001a\u00020!2\u0006\u0010\"\u001a\u0002042\b\b\u0001\u00105\u001a\u000206H\u0001J\u001b\u0010?\u001a\u00020,2\u0006\u0010\"\u001a\u0002042\b\b\u0001\u00105\u001a\u000206H\u0001J'\u0010@\u001a\u00020!2\u0006\u0010\"\u001a\u0002042\n\b\u0001\u00105\u001a\u0004\u0018\u0001082\b\b\u0001\u0010A\u001a\u000206H\u0001J\t\u0010B\u001a\u00020!H\u0001J\u0019\u0010B\u001a\u00020!2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010C0CH\u0001J\u0019\u0010D\u001a\u00020,2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010E0EH\u0001J\u0011\u0010F\u001a\u00020,2\u0006\u0010\"\u001a\u00020!H\u0001J\u001b\u0010G\u001a\u0004\u0018\u00010/2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010H0HH\u0001J#\u0010G\u001a\u0004\u0018\u00010/2\u000e\u0010\"\u001a\n \u001b*\u0004\u0018\u00010H0H2\u0006\u00105\u001a\u000204H\u0001R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u00030\u00030\u001aX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006J"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/WindowCallbackWrapper;", "Landroid/view/Window$Callback;", "window", "Landroid/view/Window;", "wrappedCallback", "gesturesDetector", "Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesDetectorWrapper;", "interactionPredicate", "Lcom/datadog/android/rum/tracking/InteractionPredicate;", "copyEvent", "Lkotlin/Function1;", "Landroid/view/MotionEvent;", "targetAttributesProviders", "", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "(Landroid/view/Window;Landroid/view/Window$Callback;Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesDetectorWrapper;Lcom/datadog/android/rum/tracking/InteractionPredicate;Lkotlin/jvm/functions/Function1;[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;)V", "getCopyEvent", "()Lkotlin/jvm/functions/Function1;", "getGesturesDetector", "()Lcom/datadog/android/rum/internal/instrumentation/gestures/GesturesDetectorWrapper;", "getInteractionPredicate", "()Lcom/datadog/android/rum/tracking/InteractionPredicate;", "getTargetAttributesProviders", "()[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "[Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "windowReference", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getWindowReference$dd_sdk_android_release", "()Ljava/lang/ref/WeakReference;", "getWrappedCallback", "()Landroid/view/Window$Callback;", "dispatchGenericMotionEvent", "", "p0", "dispatchKeyEvent", "event", "Landroid/view/KeyEvent;", "dispatchKeyShortcutEvent", "dispatchPopulateAccessibilityEvent", "Landroid/view/accessibility/AccessibilityEvent;", "dispatchTouchEvent", "dispatchTrackballEvent", "handleBackEvent", "", "handleRemoteControlActionEvent", "onActionModeFinished", "Landroid/view/ActionMode;", "onActionModeStarted", "onAttachedToWindow", "onContentChanged", "onCreatePanelMenu", "", "p1", "Landroid/view/Menu;", "onCreatePanelView", "Landroid/view/View;", "onDetachedFromWindow", "onMenuItemSelected", "featureId", "item", "Landroid/view/MenuItem;", "onMenuOpened", "onPanelClosed", "onPreparePanel", "p2", "onSearchRequested", "Landroid/view/SearchEvent;", "onWindowAttributesChanged", "Landroid/view/WindowManager$LayoutParams;", "onWindowFocusChanged", "onWindowStartingActionMode", "Landroid/view/ActionMode$Callback;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WindowCallbackWrapper.kt */
public final class WindowCallbackWrapper implements Window.Callback {
    public static final String BACK_DEFAULT_TARGET_NAME = "back";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final boolean EVENT_CONSUMED = true;
    private final Function1<MotionEvent, MotionEvent> copyEvent;
    private final GesturesDetectorWrapper gesturesDetector;
    private final InteractionPredicate interactionPredicate;
    private final ViewAttributesProvider[] targetAttributesProviders;
    private final WeakReference<Window> windowReference;
    private final Window.Callback wrappedCallback;

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.wrappedCallback.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.wrappedCallback.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.wrappedCallback.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.wrappedCallback.dispatchTrackballEvent(motionEvent);
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.wrappedCallback.onActionModeFinished(actionMode);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.wrappedCallback.onActionModeStarted(actionMode);
    }

    public void onAttachedToWindow() {
        this.wrappedCallback.onAttachedToWindow();
    }

    public void onContentChanged() {
        this.wrappedCallback.onContentChanged();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "p1");
        return this.wrappedCallback.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        return this.wrappedCallback.onCreatePanelView(i);
    }

    public void onDetachedFromWindow() {
        this.wrappedCallback.onDetachedFromWindow();
    }

    public boolean onMenuOpened(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "p1");
        return this.wrappedCallback.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "p1");
        this.wrappedCallback.onPanelClosed(i, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "p2");
        return this.wrappedCallback.onPreparePanel(i, view, menu);
    }

    public boolean onSearchRequested() {
        return this.wrappedCallback.onSearchRequested();
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.wrappedCallback.onSearchRequested(searchEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.wrappedCallback.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        this.wrappedCallback.onWindowFocusChanged(z);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.wrappedCallback.onWindowStartingActionMode(callback);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return this.wrappedCallback.onWindowStartingActionMode(callback, i);
    }

    public WindowCallbackWrapper(Window window, Window.Callback callback, GesturesDetectorWrapper gesturesDetectorWrapper, InteractionPredicate interactionPredicate2, Function1<? super MotionEvent, MotionEvent> function1, ViewAttributesProvider[] viewAttributesProviderArr) {
        Intrinsics.checkNotNullParameter(window, "window");
        Intrinsics.checkNotNullParameter(callback, "wrappedCallback");
        Intrinsics.checkNotNullParameter(gesturesDetectorWrapper, "gesturesDetector");
        Intrinsics.checkNotNullParameter(interactionPredicate2, "interactionPredicate");
        Intrinsics.checkNotNullParameter(function1, "copyEvent");
        Intrinsics.checkNotNullParameter(viewAttributesProviderArr, "targetAttributesProviders");
        this.wrappedCallback = callback;
        this.gesturesDetector = gesturesDetectorWrapper;
        this.interactionPredicate = interactionPredicate2;
        this.copyEvent = function1;
        this.targetAttributesProviders = viewAttributesProviderArr;
        this.windowReference = new WeakReference<>(window);
    }

    public final Window.Callback getWrappedCallback() {
        return this.wrappedCallback;
    }

    public final GesturesDetectorWrapper getGesturesDetector() {
        return this.gesturesDetector;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WindowCallbackWrapper(Window window, Window.Callback callback, GesturesDetectorWrapper gesturesDetectorWrapper, InteractionPredicate interactionPredicate2, Function1 function1, ViewAttributesProvider[] viewAttributesProviderArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(window, callback, gesturesDetectorWrapper, (i & 8) != 0 ? new NoOpInteractionPredicate() : interactionPredicate2, (i & 16) != 0 ? C08621.INSTANCE : function1, (i & 32) != 0 ? new ViewAttributesProvider[0] : viewAttributesProviderArr);
    }

    public final InteractionPredicate getInteractionPredicate() {
        return this.interactionPredicate;
    }

    public final Function1<MotionEvent, MotionEvent> getCopyEvent() {
        return this.copyEvent;
    }

    public final ViewAttributesProvider[] getTargetAttributesProviders() {
        return this.targetAttributesProviders;
    }

    public final WeakReference<Window> getWindowReference$dd_sdk_android_release() {
        return this.windowReference;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null) {
            MotionEvent invoke = this.copyEvent.invoke(motionEvent);
            try {
                this.gesturesDetector.onTouchEvent(invoke);
            } catch (Exception e) {
                LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Error processing MotionEvent", e, (Map) null, 4, (Object) null);
            } catch (Throwable th) {
                invoke.recycle();
                throw th;
            }
            invoke.recycle();
        } else {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Received MotionEvent=null", (Throwable) null, (Map) null, 6, (Object) null);
        }
        try {
            return this.wrappedCallback.dispatchTouchEvent(motionEvent);
        } catch (Exception e2) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Wrapped callback failed processing MotionEvent", e2, (Map) null, 4, (Object) null);
            return true;
        }
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        GlobalRum.get().addUserAction(RumActionType.TAP, GesturesUtilsKt.resolveTargetName(this.interactionPredicate, menuItem), MapsKt.mutableMapOf(TuplesKt.m78to(RumAttributes.ACTION_TARGET_CLASS_NAME, menuItem.getClass().getCanonicalName()), TuplesKt.m78to(RumAttributes.ACTION_TARGET_RESOURCE_ID, GesturesUtilsKt.resourceIdName(menuItem.getItemId())), TuplesKt.m78to(RumAttributes.ACTION_TARGET_TITLE, menuItem.getTitle())));
        try {
            return this.wrappedCallback.onMenuItemSelected(i, menuItem);
        } catch (Exception e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Wrapped callback failed processing MenuItem selection", e, (Map) null, 4, (Object) null);
            return true;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent == null) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Received KeyEvent=null", (Throwable) null, (Map) null, 6, (Object) null);
        } else if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            handleBackEvent(keyEvent);
        } else if (keyEvent.getKeyCode() == 23 && keyEvent.getAction() == 1) {
            handleRemoteControlActionEvent();
        }
        try {
            return this.wrappedCallback.dispatchKeyEvent(keyEvent);
        } catch (Exception e) {
            LogUtilsKt.errorWithTelemetry$default(RuntimeUtilsKt.getSdkLogger(), "Wrapped callback failed processing KeyEvent", e, (Map) null, 4, (Object) null);
            return true;
        }
    }

    private final void handleRemoteControlActionEvent() {
        View currentFocus;
        Window window = (Window) this.windowReference.get();
        if (window != null && (currentFocus = window.getCurrentFocus()) != null) {
            int i = 0;
            Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.m78to(RumAttributes.ACTION_TARGET_CLASS_NAME, GesturesUtilsKt.targetClassName(currentFocus)), TuplesKt.m78to(RumAttributes.ACTION_TARGET_RESOURCE_ID, GesturesUtilsKt.resourceIdName(currentFocus.getId())));
            ViewAttributesProvider[] targetAttributesProviders2 = getTargetAttributesProviders();
            int length = targetAttributesProviders2.length;
            while (i < length) {
                ViewAttributesProvider viewAttributesProvider = targetAttributesProviders2[i];
                i++;
                viewAttributesProvider.extractAttributes(currentFocus, mutableMapOf);
            }
            GlobalRum.get().addUserAction(RumActionType.CLICK, GesturesUtilsKt.resolveTargetName(getInteractionPredicate(), currentFocus), mutableMapOf);
        }
    }

    private final void handleBackEvent(KeyEvent keyEvent) {
        String targetName = this.interactionPredicate.getTargetName(keyEvent);
        CharSequence charSequence = targetName;
        if (charSequence == null || charSequence.length() == 0) {
            targetName = BACK_DEFAULT_TARGET_NAME;
        }
        GlobalRum.get().addUserAction(RumActionType.CUSTOM, targetName, MapsKt.emptyMap());
    }

    @Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/WindowCallbackWrapper$Companion;", "", "()V", "BACK_DEFAULT_TARGET_NAME", "", "EVENT_CONSUMED", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: WindowCallbackWrapper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
