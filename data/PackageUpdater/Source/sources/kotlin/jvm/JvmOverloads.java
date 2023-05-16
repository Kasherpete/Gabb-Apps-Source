package kotlin.jvm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR})
@Retention(AnnotationRetention.BINARY)
@Documented
@java.lang.annotation.Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(mo20734d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, mo20735d2 = {"Lkotlin/jvm/JvmOverloads;", "", "kotlin-stdlib"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
/* compiled from: JvmPlatformAnnotations.kt */
public @interface JvmOverloads {
}
