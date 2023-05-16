package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import com.datadog.android.core.internal.CoreFeature;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\fJ\u0015\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0003J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0015\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0016H\u0001¢\u0006\u0002\b\u001dJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, mo20735d2 = {"Landroidx/window/layout/WindowMetricsCalculatorCompat;", "Landroidx/window/layout/WindowMetricsCalculator;", "()V", "TAG", "", "computeCurrentWindowMetrics", "Landroidx/window/layout/WindowMetrics;", "activity", "Landroid/app/Activity;", "computeMaximumWindowMetrics", "computeWindowBoundsIceCreamSandwich", "Landroid/graphics/Rect;", "computeWindowBoundsIceCreamSandwich$window_release", "computeWindowBoundsN", "computeWindowBoundsN$window_release", "computeWindowBoundsP", "computeWindowBoundsP$window_release", "computeWindowBoundsQ", "computeWindowBoundsQ$window_release", "getCutoutForDisplay", "Landroid/view/DisplayCutout;", "display", "Landroid/view/Display;", "getNavigationBarHeight", "", "context", "Landroid/content/Context;", "getRealSizeForDisplay", "Landroid/graphics/Point;", "getRealSizeForDisplay$window_release", "getRectSizeFromDisplay", "", "bounds", "window_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: WindowMetricsCalculatorCompat.kt */
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public static final WindowMetricsCalculatorCompat INSTANCE = new WindowMetricsCalculatorCompat();
    private static final String TAG = "WindowMetricsCalculatorCompat";

    private WindowMetricsCalculatorCompat() {
    }

    static {
        Intrinsics.checkNotNullExpressionValue("WindowMetricsCalculatorCompat", "WindowMetricsCalculatorC…at::class.java.simpleName");
    }

    public WindowMetrics computeCurrentWindowMetrics(Activity activity) {
        Rect rect;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Build.VERSION.SDK_INT >= 30) {
            rect = ActivityCompatHelperApi30.INSTANCE.currentWindowBounds(activity);
        } else if (Build.VERSION.SDK_INT >= 29) {
            rect = computeWindowBoundsQ$window_release(activity);
        } else if (Build.VERSION.SDK_INT >= 28) {
            rect = computeWindowBoundsP$window_release(activity);
        } else if (Build.VERSION.SDK_INT >= 24) {
            rect = computeWindowBoundsN$window_release(activity);
        } else {
            rect = computeWindowBoundsIceCreamSandwich$window_release(activity);
        }
        return new WindowMetrics(rect);
    }

    public WindowMetrics computeMaximumWindowMetrics(Activity activity) {
        Rect rect;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Build.VERSION.SDK_INT >= 30) {
            rect = ActivityCompatHelperApi30.INSTANCE.maximumWindowBounds(activity);
        } else {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "display");
            Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
            rect = new Rect(0, 0, realSizeForDisplay$window_release.x, realSizeForDisplay$window_release.y);
        }
        return new WindowMetrics(rect);
    }

    public final Rect computeWindowBoundsQ$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            Object invoke = obj.getClass().getDeclaredMethod("getBounds", new Class[0]).invoke(obj, new Object[0]);
            if (invoke != null) {
                return new Rect((Rect) invoke);
            }
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
        } catch (NoSuchFieldException e) {
            Log.w(TAG, e);
            return computeWindowBoundsP$window_release(activity);
        } catch (NoSuchMethodException e2) {
            Log.w(TAG, e2);
            return computeWindowBoundsP$window_release(activity);
        } catch (IllegalAccessException e3) {
            Log.w(TAG, e3);
            return computeWindowBoundsP$window_release(activity);
        } catch (InvocationTargetException e4) {
            Log.w(TAG, e4);
            return computeWindowBoundsP$window_release(activity);
        }
    }

    public final Rect computeWindowBoundsP$window_release(Activity activity) {
        DisplayCutout cutoutForDisplay;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect rect = new Rect();
        Configuration configuration = activity.getResources().getConfiguration();
        try {
            Field declaredField = Configuration.class.getDeclaredField("windowConfiguration");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(configuration);
            if (ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
                Object invoke = obj.getClass().getDeclaredMethod("getBounds", new Class[0]).invoke(obj, new Object[0]);
                if (invoke != null) {
                    rect.set((Rect) invoke);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
            } else {
                Object invoke2 = obj.getClass().getDeclaredMethod("getAppBounds", new Class[0]).invoke(obj, new Object[0]);
                if (invoke2 != null) {
                    rect.set((Rect) invoke2);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.graphics.Rect");
                }
            }
        } catch (NoSuchFieldException e) {
            Log.w(TAG, e);
            getRectSizeFromDisplay(activity, rect);
        } catch (NoSuchMethodException e2) {
            Log.w(TAG, e2);
            getRectSizeFromDisplay(activity, rect);
        } catch (IllegalAccessException e3) {
            Log.w(TAG, e3);
            getRectSizeFromDisplay(activity, rect);
        } catch (InvocationTargetException e4) {
            Log.w(TAG, e4);
            getRectSizeFromDisplay(activity, rect);
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        DisplayCompatHelperApi17 displayCompatHelperApi17 = DisplayCompatHelperApi17.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(defaultDisplay, "currentDisplay");
        displayCompatHelperApi17.getRealSize(defaultDisplay, point);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            int navigationBarHeight = getNavigationBarHeight(activity);
            if (rect.bottom + navigationBarHeight == point.y) {
                rect.bottom += navigationBarHeight;
            } else if (rect.right + navigationBarHeight == point.x) {
                rect.right += navigationBarHeight;
            } else if (rect.left == navigationBarHeight) {
                rect.left = 0;
            }
        }
        if ((rect.width() < point.x || rect.height() < point.y) && !ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity) && (cutoutForDisplay = getCutoutForDisplay(defaultDisplay)) != null) {
            if (rect.left == DisplayCompatHelperApi28.INSTANCE.safeInsetLeft(cutoutForDisplay)) {
                rect.left = 0;
            }
            if (point.x - rect.right == DisplayCompatHelperApi28.INSTANCE.safeInsetRight(cutoutForDisplay)) {
                rect.right += DisplayCompatHelperApi28.INSTANCE.safeInsetRight(cutoutForDisplay);
            }
            if (rect.top == DisplayCompatHelperApi28.INSTANCE.safeInsetTop(cutoutForDisplay)) {
                rect.top = 0;
            }
            if (point.y - rect.bottom == DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(cutoutForDisplay)) {
                rect.bottom += DisplayCompatHelperApi28.INSTANCE.safeInsetBottom(cutoutForDisplay);
            }
        }
        return rect;
    }

    private final void getRectSizeFromDisplay(Activity activity, Rect rect) {
        activity.getWindowManager().getDefaultDisplay().getRectSize(rect);
    }

    public final Rect computeWindowBoundsN$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Rect rect = new Rect();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getRectSize(rect);
        if (!ActivityCompatHelperApi24.INSTANCE.isInMultiWindowMode(activity)) {
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "defaultDisplay");
            Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
            int navigationBarHeight = getNavigationBarHeight(activity);
            if (rect.bottom + navigationBarHeight == realSizeForDisplay$window_release.y) {
                rect.bottom += navigationBarHeight;
            } else if (rect.right + navigationBarHeight == realSizeForDisplay$window_release.x) {
                rect.right += navigationBarHeight;
            }
        }
        return rect;
    }

    public final Rect computeWindowBoundsIceCreamSandwich$window_release(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Intrinsics.checkNotNullExpressionValue(defaultDisplay, "defaultDisplay");
        Point realSizeForDisplay$window_release = getRealSizeForDisplay$window_release(defaultDisplay);
        Rect rect = new Rect();
        if (realSizeForDisplay$window_release.x == 0 || realSizeForDisplay$window_release.y == 0) {
            defaultDisplay.getRectSize(rect);
        } else {
            rect.right = realSizeForDisplay$window_release.x;
            rect.bottom = realSizeForDisplay$window_release.y;
        }
        return rect;
    }

    public final Point getRealSizeForDisplay$window_release(Display display) {
        Intrinsics.checkNotNullParameter(display, "display");
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayCompatHelperApi17.INSTANCE.getRealSize(display, point);
        } else {
            try {
                Method declaredMethod = Display.class.getDeclaredMethod("getRealSize", new Class[]{Point.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(display, new Object[]{point});
            } catch (NoSuchMethodException e) {
                Log.w(TAG, e);
            } catch (IllegalAccessException e2) {
                Log.w(TAG, e2);
            } catch (InvocationTargetException e3) {
                Log.w(TAG, e3);
            }
        }
        return point;
    }

    private final int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", CoreFeature.DEFAULT_SOURCE_NAME);
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private final DisplayCutout getCutoutForDisplay(Display display) {
        try {
            Constructor<?> constructor = Class.forName("android.view.DisplayInfo").getConstructor(new Class[0]);
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance(new Object[0]);
            Method declaredMethod = display.getClass().getDeclaredMethod("getDisplayInfo", new Class[]{newInstance.getClass()});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(display, new Object[]{newInstance});
            Field declaredField = newInstance.getClass().getDeclaredField("displayCutout");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(newInstance);
            if (obj instanceof DisplayCutout) {
                return (DisplayCutout) obj;
            }
        } catch (ClassNotFoundException e) {
            Log.w(TAG, e);
        } catch (NoSuchMethodException e2) {
            Log.w(TAG, e2);
        } catch (NoSuchFieldException e3) {
            Log.w(TAG, e3);
        } catch (IllegalAccessException e4) {
            Log.w(TAG, e4);
        } catch (InvocationTargetException e5) {
            Log.w(TAG, e5);
        } catch (InstantiationException e6) {
            Log.w(TAG, e6);
        }
        return null;
    }
}
