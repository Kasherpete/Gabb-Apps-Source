package com.gabb.data.dao;

import com.gabb.data.entities.App;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(mo20734d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0011\u0010\r\u001a\u00020\u0003H§@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0013\"\u00020\tH§@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, mo20735d2 = {"Lcom/gabb/data/dao/AppDao;", "", "deletePackage", "", "app", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllApps", "", "Lcom/gabb/data/entities/App;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppsWithUri", "getAppsWithoutUri", "nuke", "updateWithUri", "packageName", "uri", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "([Lcom/gabb/data/entities/App;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: AppDao.kt */
public interface AppDao {
    Object deletePackage(String str, Continuation<? super Unit> continuation);

    Object getAllApps(Continuation<? super List<App>> continuation);

    Object getAppsWithUri(Continuation<? super List<App>> continuation);

    Object getAppsWithoutUri(Continuation<? super List<App>> continuation);

    Object nuke(Continuation<? super Unit> continuation);

    Object updateWithUri(String str, String str2, Continuation<? super Unit> continuation);

    Object upsert(App[] appArr, Continuation<? super Unit> continuation);
}
