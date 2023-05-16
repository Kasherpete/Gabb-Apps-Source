package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014J\b\u0010\u0019\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u001a"}, mo20735d2 = {"Landroidx/window/embedding/SplitPairFilter;", "", "primaryActivityName", "Landroid/content/ComponentName;", "secondaryActivityName", "secondaryActivityIntentAction", "", "(Landroid/content/ComponentName;Landroid/content/ComponentName;Ljava/lang/String;)V", "getPrimaryActivityName", "()Landroid/content/ComponentName;", "getSecondaryActivityIntentAction", "()Ljava/lang/String;", "getSecondaryActivityName", "equals", "", "other", "hashCode", "", "matchesActivityIntentPair", "primaryActivity", "Landroid/app/Activity;", "secondaryActivityIntent", "Landroid/content/Intent;", "matchesActivityPair", "secondaryActivity", "toString", "window_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: SplitPairFilter.kt */
public final class SplitPairFilter {
    private final ComponentName primaryActivityName;
    private final String secondaryActivityIntentAction;
    private final ComponentName secondaryActivityName;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0152  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SplitPairFilter(android.content.ComponentName r20, android.content.ComponentName r21, java.lang.String r22) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            java.lang.String r3 = "primaryActivityName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "secondaryActivityName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            r19.<init>()
            r0.primaryActivityName = r1
            r0.secondaryActivityName = r2
            r3 = r22
            r0.secondaryActivityIntentAction = r3
            java.lang.String r0 = r20.getPackageName()
            java.lang.String r3 = "primaryActivityName.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            java.lang.String r1 = r20.getClassName()
            java.lang.String r3 = "primaryActivityName.className"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.String r3 = r21.getPackageName()
            java.lang.String r4 = "secondaryActivityName.packageName"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.lang.String r2 = r21.getClassName()
            java.lang.String r4 = "secondaryActivityName.className"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r5 = r0
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r4 = r5.length()
            r11 = 1
            r12 = 0
            if (r4 != 0) goto L_0x004c
            r4 = r11
            goto L_0x004d
        L_0x004c:
            r4 = r12
        L_0x004d:
            if (r4 != 0) goto L_0x005f
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 != 0) goto L_0x005a
            r4 = r11
            goto L_0x005b
        L_0x005a:
            r4 = r12
        L_0x005b:
            if (r4 != 0) goto L_0x005f
            r4 = r11
            goto L_0x0060
        L_0x005f:
            r4 = r12
        L_0x0060:
            if (r4 == 0) goto L_0x0152
            r13 = r1
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            int r4 = r13.length()
            if (r4 != 0) goto L_0x006d
            r4 = r11
            goto L_0x006e
        L_0x006d:
            r4 = r12
        L_0x006e:
            if (r4 != 0) goto L_0x0080
            r4 = r2
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 != 0) goto L_0x007b
            r4 = r11
            goto L_0x007c
        L_0x007b:
            r4 = r12
        L_0x007c:
            if (r4 != 0) goto L_0x0080
            r4 = r11
            goto L_0x0081
        L_0x0080:
            r4 = r12
        L_0x0081:
            if (r4 == 0) goto L_0x0146
            java.lang.String r4 = "*"
            r6 = r4
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r15 = 2
            r14 = 0
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (java.lang.CharSequence) r6, (boolean) r12, (int) r15, (java.lang.Object) r14)
            if (r6 == 0) goto L_0x00a4
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            java.lang.String r6 = "*"
            int r5 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r5, (java.lang.String) r6, (int) r7, (boolean) r8, (int) r9, (java.lang.Object) r10)
            int r0 = r0.length()
            int r0 = r0 - r11
            if (r5 != r0) goto L_0x00a2
            goto L_0x00a4
        L_0x00a2:
            r0 = r12
            goto L_0x00a5
        L_0x00a4:
            r0 = r11
        L_0x00a5:
            java.lang.String r5 = "Wildcard in package name is only allowed at the end."
            if (r0 == 0) goto L_0x013c
            r0 = r4
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r13, (java.lang.CharSequence) r0, (boolean) r12, (int) r15, (java.lang.Object) r14)
            if (r0 == 0) goto L_0x00cd
            r0 = 0
            r16 = 0
            r17 = 6
            r18 = 0
            java.lang.String r6 = "*"
            r7 = r14
            r14 = r6
            r6 = r15
            r15 = r0
            int r0 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r13, (java.lang.String) r14, (int) r15, (boolean) r16, (int) r17, (java.lang.Object) r18)
            int r1 = r1.length()
            int r1 = r1 - r11
            if (r0 != r1) goto L_0x00cb
            goto L_0x00cf
        L_0x00cb:
            r0 = r12
            goto L_0x00d0
        L_0x00cd:
            r7 = r14
            r6 = r15
        L_0x00cf:
            r0 = r11
        L_0x00d0:
            java.lang.String r1 = "Wildcard in class name is only allowed at the end."
            if (r0 == 0) goto L_0x0132
            r13 = r3
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r0 = r4
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r13, (java.lang.CharSequence) r0, (boolean) r12, (int) r6, (java.lang.Object) r7)
            if (r0 == 0) goto L_0x00f7
            r15 = 0
            r16 = 0
            r17 = 6
            r18 = 0
            java.lang.String r14 = "*"
            int r0 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r13, (java.lang.String) r14, (int) r15, (boolean) r16, (int) r17, (java.lang.Object) r18)
            int r3 = r3.length()
            int r3 = r3 - r11
            if (r0 != r3) goto L_0x00f5
            goto L_0x00f7
        L_0x00f5:
            r0 = r12
            goto L_0x00f8
        L_0x00f7:
            r0 = r11
        L_0x00f8:
            if (r0 == 0) goto L_0x0128
            r13 = r2
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r13, (java.lang.CharSequence) r4, (boolean) r12, (int) r6, (java.lang.Object) r7)
            if (r0 == 0) goto L_0x011b
            r15 = 0
            r16 = 0
            r17 = 6
            r18 = 0
            java.lang.String r14 = "*"
            int r0 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r13, (java.lang.String) r14, (int) r15, (boolean) r16, (int) r17, (java.lang.Object) r18)
            int r2 = r2.length()
            int r2 = r2 - r11
            if (r0 != r2) goto L_0x011a
            goto L_0x011b
        L_0x011a:
            r11 = r12
        L_0x011b:
            if (r11 == 0) goto L_0x011e
            return
        L_0x011e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0128:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x0132:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x013c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r5.toString()
            r0.<init>(r1)
            throw r0
        L_0x0146:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Activity class name must not be empty."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0152:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Package name must not be empty"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPairFilter.<init>(android.content.ComponentName, android.content.ComponentName, java.lang.String):void");
    }

    public final ComponentName getPrimaryActivityName() {
        return this.primaryActivityName;
    }

    public final ComponentName getSecondaryActivityName() {
        return this.secondaryActivityName;
    }

    public final String getSecondaryActivityIntentAction() {
        return this.secondaryActivityIntentAction;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
        if (matchesActivityIntentPair(r6, r7) != false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean matchesActivityPair(android.app.Activity r6, android.app.Activity r7) {
        /*
            r5 = this;
            java.lang.String r0 = "primaryActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "secondaryActivity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            androidx.window.embedding.MatcherUtils r0 = androidx.window.embedding.MatcherUtils.INSTANCE
            android.content.ComponentName r1 = r6.getComponentName()
            android.content.ComponentName r2 = r5.primaryActivityName
            boolean r0 = r0.areComponentsMatching$window_release(r1, r2)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002a
            androidx.window.embedding.MatcherUtils r0 = androidx.window.embedding.MatcherUtils.INSTANCE
            android.content.ComponentName r3 = r7.getComponentName()
            android.content.ComponentName r4 = r5.secondaryActivityName
            boolean r0 = r0.areComponentsMatching$window_release(r3, r4)
            if (r0 == 0) goto L_0x002a
            r0 = r1
            goto L_0x002b
        L_0x002a:
            r0 = r2
        L_0x002b:
            android.content.Intent r3 = r7.getIntent()
            if (r3 == 0) goto L_0x0045
            if (r0 == 0) goto L_0x0043
            android.content.Intent r7 = r7.getIntent()
            java.lang.String r0 = "secondaryActivity.intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
            boolean r5 = r5.matchesActivityIntentPair(r6, r7)
            if (r5 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r1 = r2
        L_0x0044:
            r0 = r1
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.embedding.SplitPairFilter.matchesActivityPair(android.app.Activity, android.app.Activity):boolean");
    }

    public final boolean matchesActivityIntentPair(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "primaryActivity");
        Intrinsics.checkNotNullParameter(intent, "secondaryActivityIntent");
        if (!MatcherUtils.INSTANCE.areComponentsMatching$window_release(activity.getComponentName(), this.primaryActivityName) || !MatcherUtils.INSTANCE.areComponentsMatching$window_release(intent.getComponent(), this.secondaryActivityName)) {
            return false;
        }
        String str = this.secondaryActivityIntentAction;
        if (str == null || Intrinsics.areEqual((Object) str, (Object) intent.getAction())) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SplitPairFilter)) {
            return false;
        }
        SplitPairFilter splitPairFilter = (SplitPairFilter) obj;
        return Intrinsics.areEqual((Object) this.primaryActivityName, (Object) splitPairFilter.primaryActivityName) && Intrinsics.areEqual((Object) this.secondaryActivityName, (Object) splitPairFilter.secondaryActivityName) && Intrinsics.areEqual((Object) this.secondaryActivityIntentAction, (Object) splitPairFilter.secondaryActivityIntentAction);
    }

    public int hashCode() {
        int hashCode = ((this.primaryActivityName.hashCode() * 31) + this.secondaryActivityName.hashCode()) * 31;
        String str = this.secondaryActivityIntentAction;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + this.primaryActivityName + ", secondaryActivityName=" + this.secondaryActivityName + ", secondaryActivityAction=" + this.secondaryActivityIntentAction + '}';
    }
}
