package androidx.window.embedding;

import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.EmbeddingRule;
import androidx.window.extensions.embedding.SplitInfo;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import p006io.opentracing.tag.Tags;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u001c\u0010\b\u001a\u00020\u00042\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0016¨\u0006\r"}, mo20735d2 = {"Landroidx/window/embedding/EmptyEmbeddingComponent;", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "()V", "setEmbeddingRules", "", "splitRules", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "setSplitInfoCallback", "consumer", "Ljava/util/function/Consumer;", "", "Landroidx/window/extensions/embedding/SplitInfo;", "window_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: EmbeddingCompat.kt */
final class EmptyEmbeddingComponent implements ActivityEmbeddingComponent {
    public void setEmbeddingRules(Set<EmbeddingRule> set) {
        Intrinsics.checkNotNullParameter(set, "splitRules");
    }

    public void setSplitInfoCallback(Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(consumer, Tags.SPAN_KIND_CONSUMER);
    }
}
