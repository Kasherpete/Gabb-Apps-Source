package dagger.hilt.android.internal.managers;

import android.app.Application;
import android.app.Service;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.internal.GeneratedComponentManager;
import dagger.hilt.internal.Preconditions;

public final class ServiceComponentManager implements GeneratedComponentManager<Object> {
    private Object component;
    private final Service service;

    public interface ServiceComponentBuilderEntryPoint {
        ServiceComponentBuilder serviceComponentBuilder();
    }

    public ServiceComponentManager(Service service2) {
        this.service = service2;
    }

    public Object generatedComponent() {
        if (this.component == null) {
            this.component = createComponent();
        }
        return this.component;
    }

    private Object createComponent() {
        Application application = this.service.getApplication();
        Preconditions.checkState(application instanceof GeneratedComponentManager, "Hilt service must be attached to an @AndroidEntryPoint Application. Found: %s", application.getClass());
        return ((ServiceComponentBuilderEntryPoint) EntryPoints.get(application, ServiceComponentBuilderEntryPoint.class)).serviceComponentBuilder().service(this.service).build();
    }
}
