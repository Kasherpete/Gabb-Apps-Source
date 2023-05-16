package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@JvmInline
@Metadata(mo20734d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b@\u0018\u0000 %*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0003$%&B\u0016\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0019\u0010\u0005J\r\u0010\u001a\u001a\u00028\u0000¢\u0006\u0004\b\u001b\u0010\u0005J\u0010\u0010\u001c\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\u0001\u0003\u0001\u0004\u0018\u00010\u0002ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, mo20735d2 = {"Lkotlinx/coroutines/channels/ChannelResult;", "T", "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getHolder$annotations", "()V", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "isFailure", "isFailure-impl", "isSuccess", "isSuccess-impl", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "getOrThrow", "getOrThrow-impl", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Closed", "Companion", "Failed", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: Channel.kt */
public final class ChannelResult<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Failed failed = new Failed();
    private final Object holder;

    @Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, mo20735d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Failed;", "", "()V", "toString", "", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Channel.kt */
    public static class Failed {
        public String toString() {
            return "Failed";
        }
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ChannelResult m1621boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static <T> Object m1622constructorimpl(Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1623equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).m1633unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1624equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    public static /* synthetic */ void getHolder$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1628hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean equals(Object obj) {
        return m1623equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m1628hashCodeimpl(this.holder);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m1633unboximpl() {
        return this.holder;
    }

    private /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m1631isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    /* renamed from: isFailure-impl  reason: not valid java name */
    public static final boolean m1630isFailureimpl(Object obj) {
        return obj instanceof Failed;
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m1629isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* renamed from: getOrNull-impl  reason: not valid java name */
    public static final T m1626getOrNullimpl(Object obj) {
        if (!(obj instanceof Failed)) {
            return obj;
        }
        return null;
    }

    /* renamed from: getOrThrow-impl  reason: not valid java name */
    public static final T m1627getOrThrowimpl(Object obj) {
        if (!(obj instanceof Failed)) {
            return obj;
        }
        if (obj instanceof Closed) {
            Closed closed = (Closed) obj;
            if (closed.cause != null) {
                throw closed.cause;
            }
        }
        throw new IllegalStateException(("Trying to call 'getOrThrow' on a failed channel result: " + obj).toString());
    }

    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m1625exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.cause;
        }
        return null;
    }

    @Metadata(mo20734d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo20735d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Closed;", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "cause", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Channel.kt */
    public static final class Closed extends Failed {
        public final Throwable cause;

        public Closed(Throwable th) {
            this.cause = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual((Object) this.cause, (Object) ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    @Metadata(mo20734d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\n\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u0007H\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\r\u0010\u000eJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\u0010\u001a\u0002H\u0007H\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, mo20735d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Companion;", "", "()V", "failed", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "closed", "Lkotlinx/coroutines/channels/ChannelResult;", "E", "cause", "", "closed-JP2dKIU", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "failure", "failure-PtdJZtk", "()Ljava/lang/Object;", "success", "value", "success-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: Channel.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* renamed from: success-JP2dKIU  reason: not valid java name */
        public final <E> Object m1636successJP2dKIU(E e) {
            return ChannelResult.m1622constructorimpl(e);
        }

        /* renamed from: failure-PtdJZtk  reason: not valid java name */
        public final <E> Object m1635failurePtdJZtk() {
            return ChannelResult.m1622constructorimpl(ChannelResult.failed);
        }

        /* renamed from: closed-JP2dKIU  reason: not valid java name */
        public final <E> Object m1634closedJP2dKIU(Throwable th) {
            return ChannelResult.m1622constructorimpl(new Closed(th));
        }
    }

    public String toString() {
        return m1632toStringimpl(this.holder);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1632toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
