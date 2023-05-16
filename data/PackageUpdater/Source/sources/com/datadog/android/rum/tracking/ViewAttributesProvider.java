package com.datadog.android.rum.tracking;

import android.view.View;
import java.util.Map;
import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H&¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/rum/tracking/ViewAttributesProvider;", "", "extractAttributes", "", "view", "Landroid/view/View;", "attributes", "", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ViewAttributesProvider.kt */
public interface ViewAttributesProvider {
    void extractAttributes(View view, Map<String, Object> map);
}
