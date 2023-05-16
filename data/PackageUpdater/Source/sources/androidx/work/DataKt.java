package androidx.work;

import androidx.work.Data;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\b¢\u0006\u0002\u0010\u0007\u001a!\u0010\b\u001a\u00020\t\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u0006*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0005H\b¨\u0006\f"}, mo20735d2 = {"workDataOf", "Landroidx/work/Data;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroidx/work/Data;", "hasKeyWithValueOfType", "", "T", "key", "work-runtime-ktx_release"}, mo20736k = 2, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: Data.kt */
public final class DataKt {
    public static final Data workDataOf(Pair<String, ? extends Object>... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        Data.Builder builder = new Data.Builder();
        int length = pairArr.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair = pairArr[i];
            i++;
            builder.put(pair.getFirst(), pair.getSecond());
        }
        Data build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "dataBuilder.build()");
        return build;
    }

    public static final /* synthetic */ <T> boolean hasKeyWithValueOfType(Data data, String str) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.reifiedOperationMarker(4, "T");
        return data.hasKeyWithValueOfType(str, Object.class);
    }
}
