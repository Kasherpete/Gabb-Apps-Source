package com.datadog.android.core.internal.persistence.file;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/EventMeta;", "", "eventSize", "", "(I)V", "asBytes", "", "getAsBytes", "()[B", "getEventSize", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: EventMeta.kt */
public final class EventMeta {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EVENT_SIZE_KEY = "ev_size";
    private final int eventSize;

    public static /* synthetic */ EventMeta copy$default(EventMeta eventMeta, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = eventMeta.eventSize;
        }
        return eventMeta.copy(i);
    }

    public final int component1() {
        return this.eventSize;
    }

    public final EventMeta copy(int i) {
        return new EventMeta(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EventMeta) && this.eventSize == ((EventMeta) obj).eventSize;
    }

    public int hashCode() {
        return Integer.hashCode(this.eventSize);
    }

    public String toString() {
        return "EventMeta(eventSize=" + this.eventSize + ")";
    }

    public EventMeta(int i) {
        this.eventSize = i;
    }

    public final int getEventSize() {
        return this.eventSize;
    }

    public final byte[] getAsBytes() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EVENT_SIZE_KEY, (Number) Integer.valueOf(getEventSize()));
        String jsonObject2 = jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jsonObject2, "JsonObject()\n           …              .toString()");
        byte[] bytes = jsonObject2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    @Metadata(mo20734d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, mo20735d2 = {"Lcom/datadog/android/core/internal/persistence/file/EventMeta$Companion;", "", "()V", "EVENT_SIZE_KEY", "", "fromBytes", "Lcom/datadog/android/core/internal/persistence/file/EventMeta;", "metaBytes", "", "dd-sdk-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: EventMeta.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EventMeta fromBytes(byte[] bArr) throws JsonParseException {
            Intrinsics.checkNotNullParameter(bArr, "metaBytes");
            try {
                return new EventMeta(JsonParser.parseString(new String(bArr, Charsets.UTF_8)).getAsJsonObject().get(EventMeta.EVENT_SIZE_KEY).getAsInt());
            } catch (IllegalStateException e) {
                throw new JsonParseException((Throwable) e);
            } catch (ClassCastException e2) {
                throw new JsonParseException((Throwable) e2);
            } catch (NumberFormatException e3) {
                throw new JsonParseException((Throwable) e3);
            } catch (NullPointerException e4) {
                throw new JsonParseException((Throwable) e4);
            }
        }
    }
}
