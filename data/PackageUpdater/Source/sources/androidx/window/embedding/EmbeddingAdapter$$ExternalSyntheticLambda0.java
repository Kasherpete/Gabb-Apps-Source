package androidx.window.embedding;

import android.util.Pair;
import java.util.Set;
import java.util.function.Predicate;

public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ EmbeddingAdapter f$0;
    public final /* synthetic */ Set f$1;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda0(EmbeddingAdapter embeddingAdapter, Set set) {
        this.f$0 = embeddingAdapter;
        this.f$1 = set;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.m131translateActivityIntentPredicates$lambda3(this.f$0, this.f$1, (Pair) obj);
    }
}
