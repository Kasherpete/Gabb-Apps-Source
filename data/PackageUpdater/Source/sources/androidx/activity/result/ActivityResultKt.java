package androidx.activity.result;

import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20733bv = {1, 0, 3}, mo20734d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u000f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002H\u0002¨\u0006\u0005"}, mo20735d2 = {"component1", "", "Landroidx/activity/result/ActivityResult;", "component2", "Landroid/content/Intent;", "activity-ktx_release"}, mo20736k = 2, mo20737mv = {1, 4, 1})
/* compiled from: ActivityResult.kt */
public final class ActivityResultKt {
    public static final int component1(ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(activityResult, "$this$component1");
        return activityResult.getResultCode();
    }

    public static final Intent component2(ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(activityResult, "$this$component2");
        return activityResult.getData();
    }
}
