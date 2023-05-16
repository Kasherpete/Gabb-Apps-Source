package com.datadog.opentracing.decorators;

import com.datadog.trace.api.Config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DDDecoratorsFactory {
    public static List<AbstractDecorator> createBuiltinDecorators() {
        ArrayList arrayList = new ArrayList();
        for (AbstractDecorator abstractDecorator : Arrays.asList(new AbstractDecorator[]{new DBTypeDecorator(), new ForceManualDropDecorator(), new ForceManualKeepDecorator(), new PeerServiceDecorator(), new ServiceNameDecorator(), new ServiceNameDecorator("service", false), new ServletContextDecorator()})) {
            if (Config.get().isRuleEnabled(abstractDecorator.getClass().getSimpleName())) {
                arrayList.add(abstractDecorator);
            }
        }
        for (String serviceNameDecorator : Config.get().getSplitByTags()) {
            arrayList.add(new ServiceNameDecorator(serviceNameDecorator, true));
        }
        return arrayList;
    }
}
