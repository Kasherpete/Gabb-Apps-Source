package com.datadog.android.core.internal.persistence;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b`\u0018\u0000 \u0007*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u0007J\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\b"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/Serializer;", "T", "", "serialize", "", "model", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Serializer.kt */
public interface Serializer<T> {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String ERROR_SERIALIZING = "Error serializing %s model";

    String serialize(T t);

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/Serializer$Companion;", "", "()V", "ERROR_SERIALIZING", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Serializer.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String ERROR_SERIALIZING = "Error serializing %s model";

        private Companion() {
        }
    }
}
