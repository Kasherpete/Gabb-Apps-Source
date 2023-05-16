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
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\u0018\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u000fH\u0016J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010!\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\"\u0010\"\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010$\u001a\u00020\u0004H\u0016J\u0012\u0010$\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0012\u0010'\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0010\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u0004H\u0016J\u0014\u0010,\u001a\u0004\u0018\u00010\u00112\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u001c\u0010,\u001a\u0004\u0018\u00010\u00112\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u00020\u0017H\u0016¨\u00060"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/instrumentation/gestures/NoOpWindowCallback;", "Landroid/view/Window$Callback;", "()V", "dispatchGenericMotionEvent", "", "event", "Landroid/view/MotionEvent;", "dispatchKeyEvent", "Landroid/view/KeyEvent;", "dispatchKeyShortcutEvent", "dispatchPopulateAccessibilityEvent", "Landroid/view/accessibility/AccessibilityEvent;", "dispatchTouchEvent", "dispatchTrackballEvent", "onActionModeFinished", "", "mode", "Landroid/view/ActionMode;", "onActionModeStarted", "onAttachedToWindow", "onContentChanged", "onCreatePanelMenu", "featureId", "", "menu", "Landroid/view/Menu;", "onCreatePanelView", "Landroid/view/View;", "onDetachedFromWindow", "onMenuItemSelected", "item", "Landroid/view/MenuItem;", "onMenuOpened", "onPanelClosed", "onPreparePanel", "view", "onSearchRequested", "searchEvent", "Landroid/view/SearchEvent;", "onWindowAttributesChanged", "attrs", "Landroid/view/WindowManager$LayoutParams;", "onWindowFocusChanged", "hasFocus", "onWindowStartingActionMode", "callback", "Landroid/view/ActionMode$Callback;", "type", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: NoOpWindowCallback.kt */
public final class NoOpWindowCallback implements Window.Callback {
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onActionModeFinished(ActionMode actionMode) {
    }

    public void onActionModeStarted(ActionMode actionMode) {
    }

    public void onAttachedToWindow() {
    }

    public void onContentChanged() {
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    public View onCreatePanelView(int i) {
        return null;
    }

    public void onDetachedFromWindow() {
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    public void onPanelClosed(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        return false;
    }

    public boolean onSearchRequested() {
        return false;
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        return false;
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
    }

    public void onWindowFocusChanged(boolean z) {
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return null;
    }
}
