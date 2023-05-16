package dagger.hilt.android;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.Contexts;
import javax.annotation.Nonnull;

public final class EntryPointAccessors {
    @Nonnull
    public static <T> T fromApplication(Context context, Class<T> cls) {
        return EntryPoints.get(Contexts.getApplication(context.getApplicationContext()), cls);
    }

    @Nonnull
    public static <T> T fromActivity(Activity activity, Class<T> cls) {
        return EntryPoints.get(activity, cls);
    }

    @Nonnull
    public static <T> T fromFragment(Fragment fragment, Class<T> cls) {
        return EntryPoints.get(fragment, cls);
    }

    @Nonnull
    public static <T> T fromView(View view, Class<T> cls) {
        return EntryPoints.get(view, cls);
    }

    private EntryPointAccessors() {
    }
}
