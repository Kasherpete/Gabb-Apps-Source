package kotlin.collections;

import kotlin.Metadata;

@Metadata(mo20734d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo20735d2 = {"Lkotlin/collections/CollectionSystemProperties;", "", "()V", "brittleContainsOptimizationEnabled", "", "kotlin-stdlib"}, mo20736k = 1, mo20737mv = {1, 6, 0}, mo20739xi = 48)
/* compiled from: CollectionsJVM.kt */
public final class CollectionSystemProperties {
    public static final CollectionSystemProperties INSTANCE = new CollectionSystemProperties();
    public static final boolean brittleContainsOptimizationEnabled;

    private CollectionSystemProperties() {
    }

    static {
        String property = System.getProperty("kotlin.collections.convert_arg_to_set_in_removeAll");
        brittleContainsOptimizationEnabled = property != null ? Boolean.parseBoolean(property) : false;
    }
}
