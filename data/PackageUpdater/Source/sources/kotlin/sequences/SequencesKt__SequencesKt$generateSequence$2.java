package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo20735d2 = {"<anonymous>", "T", "", "invoke", "()Ljava/lang/Object;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Sequences.kt */
final class SequencesKt__SequencesKt$generateSequence$2 extends Lambda implements Function0<T> {
    final /* synthetic */ T $seed;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SequencesKt__SequencesKt$generateSequence$2(T t) {
        super(0);
        this.$seed = t;
    }

    public final T invoke() {
        return this.$seed;
    }
}
