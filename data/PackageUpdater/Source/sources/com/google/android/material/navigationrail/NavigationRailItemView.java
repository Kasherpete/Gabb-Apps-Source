package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import com.google.android.material.C0915R;
import com.google.android.material.navigation.NavigationBarItemView;

final class NavigationRailItemView extends NavigationBarItemView {
    public NavigationRailItemView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (View.MeasureSpec.getMode(i2) == 0) {
            setMeasuredDimension(getMeasuredWidthAndState(), View.resolveSizeAndState(Math.max(getMeasuredHeight(), View.MeasureSpec.getSize(i2)), i2, 0));
        }
    }

    /* access modifiers changed from: protected */
    public int getItemLayoutResId() {
        return C0915R.C0920layout.mtrl_navigation_rail_item;
    }

    /* access modifiers changed from: protected */
    public int getItemDefaultMarginResId() {
        return C0915R.dimen.mtrl_navigation_rail_icon_margin;
    }
}
