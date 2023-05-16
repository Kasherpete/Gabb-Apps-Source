package com.gabb.packageupdater.p005di;

import android.content.ContentResolver;
import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* renamed from: com.gabb.packageupdater.di.AppModule_ProvidesContentResolverFactory */
public final class AppModule_ProvidesContentResolverFactory implements Factory<ContentResolver> {
    private final Provider<Context> contextProvider;

    public AppModule_ProvidesContentResolverFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public ContentResolver get() {
        return providesContentResolver(this.contextProvider.get());
    }

    public static AppModule_ProvidesContentResolverFactory create(Provider<Context> provider) {
        return new AppModule_ProvidesContentResolverFactory(provider);
    }

    public static ContentResolver providesContentResolver(Context context) {
        return (ContentResolver) Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.providesContentResolver(context));
    }
}
