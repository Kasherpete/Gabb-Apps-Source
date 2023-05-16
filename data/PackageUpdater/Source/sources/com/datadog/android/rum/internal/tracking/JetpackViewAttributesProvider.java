package com.datadog.android.rum.internal.tracking;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import com.datadog.android.rum.RumAttributes;
import com.datadog.android.rum.tracking.ViewAttributesProvider;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

@Metadata(mo20734d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/rum/internal/tracking/JetpackViewAttributesProvider;", "Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "()V", "equals", "", "other", "", "extractAttributes", "", "view", "Landroid/view/View;", "attributes", "", "", "hashCode", "", "isDirectChildOfRecyclerView", "child", "resolveIdOrResourceName", "viewIdAsHexa", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: JetpackViewAttributesProvider.kt */
public final class JetpackViewAttributesProvider implements ViewAttributesProvider {
    public void extractAttributes(View view, Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(map, "attributes");
        ViewParent parent = view.getParent();
        while (parent != null) {
            if (!(parent instanceof RecyclerView) || view == null || !isDirectChildOfRecyclerView(view)) {
                view = parent instanceof View ? (View) parent : null;
                parent = parent.getParent();
            } else {
                map.put(RumAttributes.ACTION_TARGET_PARENT_INDEX, Integer.valueOf(((RecyclerView) parent).getChildAdapterPosition(view)));
                map.put(RumAttributes.ACTION_TARGET_PARENT_CLASSNAME, parent.getClass().getCanonicalName());
                map.put(RumAttributes.ACTION_TARGET_PARENT_RESOURCE_ID, resolveIdOrResourceName((View) parent));
                return;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return Intrinsics.areEqual((Object) getClass(), (Object) obj == null ? null : obj.getClass());
    }

    public int hashCode() {
        return getClass().hashCode();
    }

    private final boolean isDirectChildOfRecyclerView(View view) {
        return view.getLayoutParams() instanceof RecyclerView.LayoutParams;
    }

    private final String resolveIdOrResourceName(View view) {
        try {
            String resourceEntryName = view.getResources().getResourceEntryName(view.getId());
            if (resourceEntryName == null) {
                return viewIdAsHexa(view);
            }
            return resourceEntryName;
        } catch (Resources.NotFoundException unused) {
            return viewIdAsHexa(view);
        }
    }

    private final String viewIdAsHexa(View view) {
        String num = Integer.toString(view.getId(), CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        return "0x" + num;
    }
}
