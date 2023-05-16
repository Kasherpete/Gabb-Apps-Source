package androidx.preference;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.C0026R;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.TypedArrayUtils;

public class PreferenceCategory extends PreferenceGroup {
    public boolean isEnabled() {
        return false;
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, C0526R.attr.preferenceCategoryStyle, 16842892));
    }

    public PreferenceCategory(Context context) {
        this(context, (AttributeSet) null);
    }

    public boolean shouldDisableDependents() {
        return !super.isEnabled();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        TextView textView;
        super.onBindViewHolder(preferenceViewHolder);
        if (Build.VERSION.SDK_INT >= 28) {
            preferenceViewHolder.itemView.setAccessibilityHeading(true);
        } else if (Build.VERSION.SDK_INT < 21) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(C0026R.attr.colorAccent, typedValue, true) && (textView = (TextView) preferenceViewHolder.findViewById(16908310)) != null && textView.getCurrentTextColor() == ContextCompat.getColor(getContext(), C0526R.C0527color.preference_fallback_accent_color)) {
                textView.setTextColor(typedValue.data);
            }
        }
    }
}
