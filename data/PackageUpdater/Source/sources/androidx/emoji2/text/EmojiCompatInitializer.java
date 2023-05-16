package androidx.emoji2.text;

import android.content.Context;
import android.os.Build;
import androidx.core.p003os.TraceCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class EmojiCompatInitializer implements Initializer<Boolean> {
    private static final long STARTUP_THREAD_CREATION_DELAY_MS = 500;
    private static final String S_INITIALIZER_THREAD_NAME = "EmojiCompatInitializer";

    public Boolean create(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        EmojiCompat.init((EmojiCompat.Config) new BackgroundDefaultConfig(context));
        delayUntilFirstResume(context);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void delayUntilFirstResume(Context context) {
        final Lifecycle lifecycle = ((LifecycleOwner) AppInitializer.getInstance(context).initializeComponent(ProcessLifecycleInitializer.class)).getLifecycle();
        lifecycle.addObserver(new DefaultLifecycleObserver() {
            public void onResume(LifecycleOwner lifecycleOwner) {
                EmojiCompatInitializer.this.loadEmojiCompatAfterDelay();
                lifecycle.removeObserver(this);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void loadEmojiCompatAfterDelay() {
        ConcurrencyHelpers.mainHandlerAsync().postDelayed(new LoadEmojiCompatRunnable(), STARTUP_THREAD_CREATION_DELAY_MS);
    }

    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    static class LoadEmojiCompatRunnable implements Runnable {
        LoadEmojiCompatRunnable() {
        }

        public void run() {
            try {
                TraceCompat.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.isConfigured()) {
                    EmojiCompat.get().load();
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    static class BackgroundDefaultConfig extends EmojiCompat.Config {
        protected BackgroundDefaultConfig(Context context) {
            super(new BackgroundDefaultLoader(context));
            setMetadataLoadStrategy(1);
        }
    }

    static class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {
        private final Context mContext;

        BackgroundDefaultLoader(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public void load(EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            ThreadPoolExecutor createBackgroundPriorityExecutor = ConcurrencyHelpers.createBackgroundPriorityExecutor(EmojiCompatInitializer.S_INITIALIZER_THREAD_NAME);
            createBackgroundPriorityExecutor.execute(new C0307xfb0118ab(this, metadataRepoLoaderCallback, createBackgroundPriorityExecutor));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: doLoad */
        public void mo6844x5cc8028a(final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, final ThreadPoolExecutor threadPoolExecutor) {
            try {
                FontRequestEmojiCompatConfig create = DefaultEmojiCompatConfig.create(this.mContext);
                if (create != null) {
                    create.setLoadingExecutor(threadPoolExecutor);
                    create.getMetadataRepoLoader().load(new EmojiCompat.MetadataRepoLoaderCallback() {
                        public void onLoaded(MetadataRepo metadataRepo) {
                            try {
                                metadataRepoLoaderCallback.onLoaded(metadataRepo);
                            } finally {
                                threadPoolExecutor.shutdown();
                            }
                        }

                        public void onFailed(Throwable th) {
                            try {
                                metadataRepoLoaderCallback.onFailed(th);
                            } finally {
                                threadPoolExecutor.shutdown();
                            }
                        }
                    });
                    return;
                }
                throw new RuntimeException("EmojiCompat font provider not available on this device.");
            } catch (Throwable th) {
                metadataRepoLoaderCallback.onFailed(th);
                threadPoolExecutor.shutdown();
            }
        }
    }
}
