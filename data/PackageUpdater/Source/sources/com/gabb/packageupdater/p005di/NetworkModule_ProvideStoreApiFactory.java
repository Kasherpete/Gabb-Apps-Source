package com.gabb.packageupdater.p005di;

import com.gabb.packageupdater.network.interfaces.StoreApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideStoreApiFactory */
public final class NetworkModule_ProvideStoreApiFactory implements Factory<StoreApi> {
    private final Provider<Retrofit> retrofitProvider;

    public NetworkModule_ProvideStoreApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public StoreApi get() {
        return provideStoreApi(this.retrofitProvider.get());
    }

    public static NetworkModule_ProvideStoreApiFactory create(Provider<Retrofit> provider) {
        return new NetworkModule_ProvideStoreApiFactory(provider);
    }

    public static StoreApi provideStoreApi(Retrofit retrofit) {
        return (StoreApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideStoreApi(retrofit));
    }
}
