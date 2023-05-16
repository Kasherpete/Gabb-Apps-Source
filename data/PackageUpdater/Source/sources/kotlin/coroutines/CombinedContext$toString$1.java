package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo20735d2 = {"<anonymous>", "", "acc", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CoroutineContextImpl.kt */
final class CombinedContext$toString$1 extends Lambda implements Function2<String, CoroutineContext.Element, String> {
    public static final CombinedContext$toString$1 INSTANCE = new CombinedContext$toString$1();

    CombinedContext$toString$1() {
        super(2);
    }

    public final String invoke(String str, CoroutineContext.Element element) {
        Intrinsics.checkNotNullParameter(str, "acc");
        Intrinsics.checkNotNullParameter(element, "element");
        return str.length() == 0 ? element.toString() : str + ", " + element;
    }
}
