package com.datadog.opentracing.decorators;

import com.datadog.opentracing.DDSpanContext;

public abstract class AbstractDecorator {
    private String matchingTag;
    private Object matchingValue;
    private String replacementTag;
    private String replacementValue;

    public boolean shouldSetTag(DDSpanContext dDSpanContext, String str, Object obj) {
        if (getMatchingValue() != null && !getMatchingValue().equals(obj)) {
            return true;
        }
        if (getReplacementTag() != null) {
            str = getReplacementTag();
        }
        dDSpanContext.setTag(str, getReplacementValue() == null ? String.valueOf(obj) : getReplacementValue());
        return false;
    }

    public String getMatchingTag() {
        return this.matchingTag;
    }

    public void setMatchingTag(String str) {
        this.matchingTag = str;
    }

    public Object getMatchingValue() {
        return this.matchingValue;
    }

    public void setMatchingValue(Object obj) {
        this.matchingValue = obj;
    }

    public String getReplacementTag() {
        return this.replacementTag;
    }

    public void setReplacementTag(String str) {
        this.replacementTag = str;
    }

    public String getReplacementValue() {
        return this.replacementValue;
    }

    public void setReplacementValue(String str) {
        this.replacementValue = str;
    }
}
