package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, mo20735d2 = {"<anonymous>", "", "it", "Landroid/animation/Animator;"}, mo20736k = 3, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* compiled from: Animator.kt */
public final class AnimatorKt$addListener$4 extends Lambda implements Function1<Animator, Unit> {
    public static final AnimatorKt$addListener$4 INSTANCE = new AnimatorKt$addListener$4();

    public AnimatorKt$addListener$4() {
        super(1);
    }

    public final void invoke(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Animator) obj);
        return Unit.INSTANCE;
    }
}
