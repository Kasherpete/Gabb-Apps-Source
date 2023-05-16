package kotlin.internal.jdk8;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.internal.jdk7.JDK7PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.random.jdk8.PlatformThreadLocalRandom;
import kotlin.ranges.IntRange;
import kotlin.text.MatchGroup;

@Metadata(mo20734d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0010"}, mo20735d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations;", "Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "()V", "defaultPlatformRandom", "Lkotlin/random/Random;", "getMatchResultNamedGroup", "Lkotlin/text/MatchGroup;", "matchResult", "Ljava/util/regex/MatchResult;", "name", "", "sdkIsNullOrAtLeast", "", "version", "", "ReflectSdkVersion", "kotlin-stdlib-jdk8"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: JDK8PlatformImplementations.kt */
public class JDK8PlatformImplementations extends JDK7PlatformImplementations {

    @Metadata(mo20734d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, mo20735d2 = {"Lkotlin/internal/jdk8/JDK8PlatformImplementations$ReflectSdkVersion;", "", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "kotlin-stdlib-jdk8"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
    /* compiled from: JDK8PlatformImplementations.kt */
    private static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE = new ReflectSdkVersion();
        public static final Integer sdkVersion;

        private ReflectSdkVersion() {
        }

        static {
            Integer num;
            Integer num2 = null;
            try {
                Object obj = Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
                num = obj instanceof Integer ? (Integer) obj : null;
            } catch (Throwable unused) {
                num = null;
            }
            if (num != null) {
                if (num.intValue() > 0) {
                    num2 = num;
                }
            }
            sdkVersion = num2;
        }
    }

    private final boolean sdkIsNullOrAtLeast(int i) {
        return ReflectSdkVersion.sdkVersion == null || ReflectSdkVersion.sdkVersion.intValue() >= i;
    }

    public MatchGroup getMatchResultNamedGroup(MatchResult matchResult, String str) {
        Intrinsics.checkNotNullParameter(matchResult, "matchResult");
        Intrinsics.checkNotNullParameter(str, "name");
        Matcher matcher = matchResult instanceof Matcher ? (Matcher) matchResult : null;
        if (matcher != null) {
            IntRange intRange = new IntRange(matcher.start(str), matcher.end(str) - 1);
            if (intRange.getStart().intValue() < 0) {
                return null;
            }
            String group = matcher.group(str);
            Intrinsics.checkNotNullExpressionValue(group, "matcher.group(name)");
            return new MatchGroup(group, intRange);
        }
        throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
    }

    public Random defaultPlatformRandom() {
        return sdkIsNullOrAtLeast(24) ? new PlatformThreadLocalRandom() : super.defaultPlatformRandom();
    }
}
