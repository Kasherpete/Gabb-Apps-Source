package com.gabb.packageupdater.data.inject;

import com.datadog.trace.api.DDSpanTypes;
import com.gabb.data.GabbDatabase;
import com.gabb.data.dao.AppDao;
import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/gabb/packageupdater/data/inject/DatabaseDaoModule;", "", "()V", "providesAppDao", "Lcom/gabb/data/dao/AppDao;", "db", "Lcom/gabb/data/GabbDatabase;", "data-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* compiled from: DatabaseInject.kt */
public final class DatabaseDaoModule {
    public static final DatabaseDaoModule INSTANCE = new DatabaseDaoModule();

    private DatabaseDaoModule() {
    }

    @Provides
    public final AppDao providesAppDao(GabbDatabase gabbDatabase) {
        Intrinsics.checkNotNullParameter(gabbDatabase, DDSpanTypes.COUCHBASE);
        return gabbDatabase.appDao();
    }
}
