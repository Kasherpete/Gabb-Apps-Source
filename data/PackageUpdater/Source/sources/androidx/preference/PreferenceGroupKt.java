package androidx.preference;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(mo20734d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0003\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0002\u001a3\u0010\r\u001a\u00020\u000e*\u00020\u00032!\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0010H\bø\u0001\u0000\u001aH\u0010\u0013\u001a\u00020\u000e*\u00020\u000326\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000e0\u0014H\bø\u0001\u0000\u001a&\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u0002*\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\n¢\u0006\u0002\u0010\u001a\u001a\u0015\u0010\u0016\u001a\u00020\u0002*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0007H\u0002\u001a\r\u0010\u001b\u001a\u00020\u000b*\u00020\u0003H\b\u001a\r\u0010\u001c\u001a\u00020\u000b*\u00020\u0003H\b\u001a\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001e*\u00020\u0003H\u0002\u001a\u0015\u0010\u001f\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\n\u001a\u0015\u0010 \u001a\u00020\u000e*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\n\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u0007\n\u0005\b20\u0001¨\u0006!"}, mo20735d2 = {"children", "Lkotlin/sequences/Sequence;", "Landroidx/preference/Preference;", "Landroidx/preference/PreferenceGroup;", "getChildren", "(Landroidx/preference/PreferenceGroup;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroidx/preference/PreferenceGroup;)I", "contains", "", "preference", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "T", "key", "", "(Landroidx/preference/PreferenceGroup;Ljava/lang/CharSequence;)Landroidx/preference/Preference;", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "plusAssign", "preference-ktx_release"}, mo20736k = 2, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: PreferenceGroup.kt */
public final class PreferenceGroupKt {
    public static final <T extends Preference> T get(PreferenceGroup preferenceGroup, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(charSequence, "key");
        return preferenceGroup.findPreference(charSequence);
    }

    public static final Preference get(PreferenceGroup preferenceGroup, int i) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Preference preference = preferenceGroup.getPreference(i);
        Intrinsics.checkNotNullExpressionValue(preference, "getPreference(index)");
        return preference;
    }

    public static final boolean contains(PreferenceGroup preferenceGroup, Preference preference) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(preference, "preference");
        int preferenceCount = preferenceGroup.getPreferenceCount();
        int i = 0;
        while (i < preferenceCount) {
            int i2 = i + 1;
            if (Intrinsics.areEqual((Object) preferenceGroup.getPreference(i), (Object) preference)) {
                return true;
            }
            i = i2;
        }
        return false;
    }

    public static final void plusAssign(PreferenceGroup preferenceGroup, Preference preference) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(preference, "preference");
        preferenceGroup.addPreference(preference);
    }

    public static final void minusAssign(PreferenceGroup preferenceGroup, Preference preference) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(preference, "preference");
        preferenceGroup.removePreference(preference);
    }

    public static final int getSize(PreferenceGroup preferenceGroup) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        return preferenceGroup.getPreferenceCount();
    }

    public static final void forEach(PreferenceGroup preferenceGroup, Function1<? super Preference, Unit> function1) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(function1, "action");
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            function1.invoke(get(preferenceGroup, i));
        }
    }

    public static final void forEachIndexed(PreferenceGroup preferenceGroup, Function2<? super Integer, ? super Preference, Unit> function2) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        Intrinsics.checkNotNullParameter(function2, "action");
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            function2.invoke(Integer.valueOf(i), get(preferenceGroup, i));
        }
    }

    public static final Iterator<Preference> iterator(PreferenceGroup preferenceGroup) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        return new PreferenceGroupKt$iterator$1(preferenceGroup);
    }

    public static final Sequence<Preference> getChildren(PreferenceGroup preferenceGroup) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        return new PreferenceGroupKt$children$1(preferenceGroup);
    }

    public static final boolean isEmpty(PreferenceGroup preferenceGroup) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        return preferenceGroup.getPreferenceCount() == 0;
    }

    public static final boolean isNotEmpty(PreferenceGroup preferenceGroup) {
        Intrinsics.checkNotNullParameter(preferenceGroup, "<this>");
        return preferenceGroup.getPreferenceCount() != 0;
    }
}
