package androidx.window.embedding;

import androidx.window.embedding.ExtensionEmbeddingBackend;
import java.util.List;

/* renamed from: androidx.window.embedding.ExtensionEmbeddingBackend$SplitListenerWrapper$$ExternalSyntheticLambda0 */
public final /* synthetic */ class C0765x5bf2d39c implements Runnable {
    public final /* synthetic */ ExtensionEmbeddingBackend.SplitListenerWrapper f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ C0765x5bf2d39c(ExtensionEmbeddingBackend.SplitListenerWrapper splitListenerWrapper, List list) {
        this.f$0 = splitListenerWrapper;
        this.f$1 = list;
    }

    public final void run() {
        ExtensionEmbeddingBackend.SplitListenerWrapper.m136accept$lambda1(this.f$0, this.f$1);
    }
}
