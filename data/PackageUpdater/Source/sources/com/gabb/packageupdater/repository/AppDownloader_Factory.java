package com.gabb.packageupdater.repository;

import com.gabb.packageupdater.network.interfaces.FileDownloadApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AppDownloader_Factory implements Factory<AppDownloader> {
    private final Provider<ApkCache> apkCacheProvider;
    private final Provider<FileDownloadApi> fileDownloadApiProvider;

    public AppDownloader_Factory(Provider<FileDownloadApi> provider, Provider<ApkCache> provider2) {
        this.fileDownloadApiProvider = provider;
        this.apkCacheProvider = provider2;
    }

    public AppDownloader get() {
        return newInstance(this.fileDownloadApiProvider.get(), this.apkCacheProvider.get());
    }

    public static AppDownloader_Factory create(Provider<FileDownloadApi> provider, Provider<ApkCache> provider2) {
        return new AppDownloader_Factory(provider, provider2);
    }

    public static AppDownloader newInstance(FileDownloadApi fileDownloadApi, ApkCache apkCache) {
        return new AppDownloader(fileDownloadApi, apkCache);
    }
}
