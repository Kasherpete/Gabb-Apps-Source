package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, mo20735d2 = {"<anonymous>", "", "e", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: ExceptionsConstructor.kt */
public final class ExceptionsConstructorKt$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {
    final /* synthetic */ Function1<Throwable, Throwable> $block;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExceptionsConstructorKt$safeCtor$1(Function1<? super Throwable, ? extends Throwable> function1) {
        super(1);
        this.$block = function1;
    }

    public final Throwable invoke(Throwable th) {
        Object obj;
        Function1<Throwable, Throwable> function1 = this.$block;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m176constructorimpl(function1.invoke(th));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m176constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m182isFailureimpl(obj)) {
            obj = null;
        }
        return (Throwable) obj;
    }
}
