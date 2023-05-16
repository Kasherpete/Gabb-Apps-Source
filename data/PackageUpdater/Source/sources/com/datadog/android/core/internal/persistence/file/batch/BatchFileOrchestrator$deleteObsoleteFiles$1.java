package com.datadog.android.core.internal.persistence.file.batch;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo20735d2 = {"<anonymous>", "", "it", "Ljava/io/File;", "invoke", "(Ljava/io/File;)Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: BatchFileOrchestrator.kt */
final class BatchFileOrchestrator$deleteObsoleteFiles$1 extends Lambda implements Function1<File, Boolean> {
    final /* synthetic */ long $threshold;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BatchFileOrchestrator$deleteObsoleteFiles$1(long j) {
        super(1);
        this.$threshold = j;
    }

    public final Boolean invoke(File file) {
        Intrinsics.checkNotNullParameter(file, "it");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "it.name");
        Long longOrNull = StringsKt.toLongOrNull(name);
        return Boolean.valueOf((longOrNull == null ? 0 : longOrNull.longValue()) < this.$threshold);
    }
}
