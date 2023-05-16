package kotlin.sequences;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.BrittleContainsOptimizationKt;

@Metadata(mo20734d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¨\u0006\u0004"}, mo20735d2 = {"kotlin/sequences/SequencesKt___SequencesKt$minus$3", "Lkotlin/sequences/Sequence;", "iterator", "", "kotlin-stdlib"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: _Sequences.kt */
public final class SequencesKt___SequencesKt$minus$3 implements Sequence<T> {
    final /* synthetic */ Iterable<T> $elements;
    final /* synthetic */ Sequence<T> $this_minus;

    SequencesKt___SequencesKt$minus$3(Iterable<? extends T> iterable, Sequence<? extends T> sequence) {
        this.$elements = iterable;
        this.$this_minus = sequence;
    }

    public Iterator<T> iterator() {
        Collection<T> convertToSetForSetOperation = BrittleContainsOptimizationKt.convertToSetForSetOperation(this.$elements);
        if (convertToSetForSetOperation.isEmpty()) {
            return this.$this_minus.iterator();
        }
        return SequencesKt.filterNot(this.$this_minus, new SequencesKt___SequencesKt$minus$3$iterator$1(convertToSetForSetOperation)).iterator();
    }
}
