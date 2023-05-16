package androidx.room;

import androidx.sqlite.p004db.SupportSQLiteQuery;

public final /* synthetic */ class QueryInterceptorDatabase$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ QueryInterceptorDatabase f$0;
    public final /* synthetic */ SupportSQLiteQuery f$1;
    public final /* synthetic */ QueryInterceptorProgram f$2;

    public /* synthetic */ QueryInterceptorDatabase$$ExternalSyntheticLambda8(QueryInterceptorDatabase queryInterceptorDatabase, SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.f$0 = queryInterceptorDatabase;
        this.f$1 = supportSQLiteQuery;
        this.f$2 = queryInterceptorProgram;
    }

    public final void run() {
        this.f$0.m122lambda$query$8$androidxroomQueryInterceptorDatabase(this.f$1, this.f$2);
    }
}
