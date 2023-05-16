package com.gabb.packageupdater.work;

import com.gabb.data.entities.App;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo20735d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: InstallerWorker.kt */
final class InstallerWorker$updateApp$installUri$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ App $app;
    final /* synthetic */ InstallerWorker<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InstallerWorker$updateApp$installUri$1(InstallerWorker<T> installerWorker, App app) {
        super(1);
        this.this$0 = installerWorker;
        this.$app = app;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        this.this$0.notifier.notifyUserOfDownloadingUpdates(this.$app.getPackageName(), Integer.valueOf(i));
    }
}
