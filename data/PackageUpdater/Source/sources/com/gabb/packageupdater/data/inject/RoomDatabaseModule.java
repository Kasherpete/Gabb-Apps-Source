package com.gabb.packageupdater.data.inject;

import android.content.Context;
import android.os.Debug;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.datadog.android.rum.internal.domain.event.RumEventSerializer;
import com.gabb.data.GabbDatabase;
import com.gabb.packageupdater.data.GabbRoomDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo20734d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, mo20735d2 = {"Lcom/gabb/packageupdater/data/inject/RoomDatabaseModule;", "", "()V", "provideDatabase", "Lcom/gabb/data/GabbDatabase;", "context", "Landroid/content/Context;", "data-android_release"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
@Module
/* compiled from: DatabaseInject.kt */
public final class RoomDatabaseModule {
    public static final RoomDatabaseModule INSTANCE = new RoomDatabaseModule();

    private RoomDatabaseModule() {
    }

    @Singleton
    @Provides
    public final GabbDatabase provideDatabase(@ApplicationContext Context context) {
        Intrinsics.checkNotNullParameter(context, RumEventSerializer.GLOBAL_ATTRIBUTE_PREFIX);
        RoomDatabase.Builder<GabbRoomDatabase> fallbackToDestructiveMigration = Room.databaseBuilder(context, GabbRoomDatabase.class, "gabb.db").fallbackToDestructiveMigration();
        Intrinsics.checkNotNullExpressionValue(fallbackToDestructiveMigration, "databaseBuilder(context,…kToDestructiveMigration()");
        if (Debug.isDebuggerConnected()) {
            fallbackToDestructiveMigration.allowMainThreadQueries();
        }
        GabbRoomDatabase build = fallbackToDestructiveMigration.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }
}
