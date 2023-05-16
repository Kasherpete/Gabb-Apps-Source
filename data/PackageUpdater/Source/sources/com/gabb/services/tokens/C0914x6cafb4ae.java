package com.gabb.services.tokens;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.gabb.services.tokens.internal.extensions.ErrorResult;
import com.gabb.services.tokens.internal.extensions.Success;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo20734d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\n¸\u0006\u0000"}, mo20735d2 = {"com/gabb/services/tokens/internal/extensions/ContextKt$connectService$3$connection$1", "Landroid/content/ServiceConnection;", "onNullBinding", "", "name", "Landroid/content/ComponentName;", "onServiceConnected", "binder", "Landroid/os/IBinder;", "onServiceDisconnected", "tokens_release"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
/* renamed from: com.gabb.services.tokens.TokenManager$getAllowedServices$$inlined$connectService$default$1 */
/* compiled from: context.kt */
public final class C0914x6cafb4ae implements ServiceConnection {
    final /* synthetic */ CancellableContinuation $it;

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public C0914x6cafb4ae(CancellableContinuation cancellableContinuation) {
        this.$it = cancellableContinuation;
    }

    public void onNullBinding(ComponentName componentName) {
        if (this.$it.isActive()) {
            ErrorResult errorResult = new ErrorResult(new ServiceInitializationException());
            Result.Companion companion = Result.Companion;
            this.$it.resumeWith(Result.m176constructorimpl(errorResult));
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            if (this.$it.isActive()) {
                ErrorResult errorResult = new ErrorResult(new ServiceInitializationException());
                Result.Companion companion = Result.Companion;
                this.$it.resumeWith(Result.m176constructorimpl(errorResult));
            }
        } else if (this.$it.isActive()) {
            Success success = new Success(iBinder, false, 2, (DefaultConstructorMarker) null);
            Result.Companion companion2 = Result.Companion;
            this.$it.resumeWith(Result.m176constructorimpl(success));
        }
    }
}