package androidx.window.embedding;

import android.app.Activity;
import java.util.Set;
import java.util.function.Predicate;

public final /* synthetic */ class EmbeddingAdapter$$ExternalSyntheticLambda3 implements Predicate {
    public final /* synthetic */ Set f$0;

    public /* synthetic */ EmbeddingAdapter$$ExternalSyntheticLambda3(Set set) {
        this.f$0 = set;
    }

    public final boolean test(Object obj) {
        return EmbeddingAdapter.m133translateActivityPredicates$lambda6(this.f$0, (Activity) obj);
    }
}
