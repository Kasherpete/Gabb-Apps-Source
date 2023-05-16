package com.datadog.android.log.internal.domain.event;

import com.datadog.android.core.internal.constraints.DataConstraints;
import com.datadog.android.core.internal.constraints.DatadogDataConstraints;
import com.datadog.android.core.internal.persistence.Serializer;
import com.datadog.android.log.model.LogEvent;
import com.datadog.android.webview.internal.log.WebViewLogEventConsumer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/event/LogEventSerializer;", "Lcom/datadog/android/core/internal/persistence/Serializer;", "Lcom/datadog/android/log/model/LogEvent;", "dataConstraints", "Lcom/datadog/android/core/internal/constraints/DataConstraints;", "(Lcom/datadog/android/core/internal/constraints/DataConstraints;)V", "sanitizeTagsAndAttributes", "log", "serialize", "", "model", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: LogEventSerializer.kt */
public final class LogEventSerializer implements Serializer<LogEvent> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String USER_EXTRA_GROUP_VERBOSE_NAME = "user extra information";
    private final DataConstraints dataConstraints;

    public LogEventSerializer() {
        this((DataConstraints) null, 1, (DefaultConstructorMarker) null);
    }

    public LogEventSerializer(DataConstraints dataConstraints2) {
        Intrinsics.checkNotNullParameter(dataConstraints2, "dataConstraints");
        this.dataConstraints = dataConstraints2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LogEventSerializer(DataConstraints dataConstraints2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DatadogDataConstraints() : dataConstraints2);
    }

    public String serialize(LogEvent logEvent) {
        Intrinsics.checkNotNullParameter(logEvent, "model");
        String jsonElement = sanitizeTagsAndAttributes(logEvent).toJson().toString();
        Intrinsics.checkNotNullExpressionValue(jsonElement, "sanitizeTagsAndAttribute…odel).toJson().toString()");
        return jsonElement;
    }

    private final LogEvent sanitizeTagsAndAttributes(LogEvent logEvent) {
        LogEvent.Usr usr;
        String joinToString$default = CollectionsKt.joinToString$default(this.dataConstraints.validateTags(StringsKt.split$default((CharSequence) logEvent.getDdtags(), new String[]{WebViewLogEventConsumer.DDTAGS_SEPARATOR}, false, 0, 6, (Object) null)), WebViewLogEventConsumer.DDTAGS_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        Map validateAttributes$default = DataConstraints.DefaultImpls.validateAttributes$default(this.dataConstraints, logEvent.getAdditionalProperties(), (String) null, (String) null, (Set) null, 14, (Object) null);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : validateAttributes$default.entrySet()) {
            if (!StringsKt.isBlank((String) entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map map = linkedHashMap;
        LogEvent.Usr usr2 = logEvent.getUsr();
        if (usr2 == null) {
            usr = null;
        } else {
            usr = LogEvent.Usr.copy$default(usr2, (String) null, (String) null, (String) null, DataConstraints.DefaultImpls.validateAttributes$default(this.dataConstraints, usr2.getAdditionalProperties(), "usr", "user extra information", (Set) null, 8, (Object) null), 7, (Object) null);
        }
        return LogEvent.copy$default(logEvent, (LogEvent.Status) null, (String) null, (String) null, (String) null, (LogEvent.Logger) null, usr, (LogEvent.Network) null, (LogEvent.Error) null, joinToString$default, map, 223, (Object) null);
    }

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lcom/datadog/android/log/internal/domain/event/LogEventSerializer$Companion;", "", "()V", "USER_EXTRA_GROUP_VERBOSE_NAME", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: LogEventSerializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
