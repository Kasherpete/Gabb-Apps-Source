package kotlinx.parcelize;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Repeatable;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;
import kotlinx.parcelize.Parceler;

@Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.PROPERTY})
@Repeatable
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Target({ElementType.TYPE})
@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0010\b\u0001\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00010\u00032\u00020\u0004B\u0000¨\u0006\u0005"}, mo20735d2 = {"Lkotlinx/parcelize/TypeParceler;", "T", "P", "Lkotlinx/parcelize/Parceler;", "", "parcelize-runtime"}, mo20736k = 1, mo20737mv = {1, 5, 1}, mo20739xi = 48)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* compiled from: TypeParceler.kt */
public @interface TypeParceler<T, P extends Parceler<? super T>> {
}
