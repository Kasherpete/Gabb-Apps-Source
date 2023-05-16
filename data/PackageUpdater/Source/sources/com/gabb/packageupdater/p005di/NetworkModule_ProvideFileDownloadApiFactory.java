package com.gabb.packageupdater.p005di;

import com.gabb.packageupdater.network.interfaces.FileDownloadApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

/* renamed from: com.gabb.packageupdater.di.NetworkModule_ProvideFileDownloadApiFactory */
public final class NetworkModule_ProvideFileDownloadApiFactory implements Factory<FileDownloadApi> {
    private final Provider<Retrofit> retrofitProvider;

    public NetworkModule_ProvideFileDownloadApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public FileDownloadApi get() {
        return provideFileDownloadApi(this.retrofitProvider.get());
    }

    public static NetworkModule_ProvideFileDownloadApiFactory create(Provider<Retrofit> provider) {
        return new NetworkModule_ProvideFileDownloadApiFactory(provider);
    }

    public static FileDownloadApi provideFileDownloadApi(Retrofit retrofit) {
        return (FileDownloadApi) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideFileDownloadApi(retrofit));
    }
}
