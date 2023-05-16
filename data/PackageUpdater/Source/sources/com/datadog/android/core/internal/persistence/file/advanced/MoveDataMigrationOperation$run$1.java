package com.datadog.android.core.internal.persistence.file.advanced;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo20734d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, mo20735d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, mo20736k = 3, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: MoveDataMigrationOperation.kt */
final class MoveDataMigrationOperation$run$1 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ MoveDataMigrationOperation this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoveDataMigrationOperation$run$1(MoveDataMigrationOperation moveDataMigrationOperation) {
        super(0);
        this.this$0 = moveDataMigrationOperation;
    }

    public final Boolean invoke() {
        return Boolean.valueOf(this.this$0.getFileHandler$dd_sdk_android_release().moveFiles(this.this$0.getFromDir$dd_sdk_android_release(), this.this$0.getToDir$dd_sdk_android_release()));
    }
}
