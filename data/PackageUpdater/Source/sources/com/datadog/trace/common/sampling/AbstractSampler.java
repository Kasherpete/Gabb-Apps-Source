package com.datadog.trace.common.sampling;

import com.datadog.opentracing.DDSpan;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Deprecated
public abstract class AbstractSampler implements Sampler {
    protected Map<String, Pattern> skipTagsPatterns = new HashMap();

    /* access modifiers changed from: protected */
    public abstract boolean doSample(DDSpan dDSpan);

    public boolean sample(DDSpan dDSpan) {
        for (Map.Entry next : this.skipTagsPatterns.entrySet()) {
            Object obj = dDSpan.getTags().get(next.getKey());
            if (obj != null) {
                if (((Pattern) next.getValue()).matcher(String.valueOf(obj)).matches()) {
                    return false;
                }
            }
        }
        return doSample(dDSpan);
    }

    @Deprecated
    public void addSkipTagPattern(String str, Pattern pattern) {
        this.skipTagsPatterns.put(str, pattern);
    }
}
