package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.CallAdapter;

final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    private final Executor callbackExecutor;

    DefaultCallAdapterFactory(@Nullable Executor executor) {
        this.callbackExecutor = executor;
    }

    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        final Executor executor = null;
        if (getRawType(type) != Call.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            final Type parameterUpperBound = Utils.getParameterUpperBound(0, (ParameterizedType) type);
            if (!Utils.isAnnotationPresent(annotationArr, SkipCallbackExecutor.class)) {
                executor = this.callbackExecutor;
            }
            return new CallAdapter<Object, Call<?>>() {
                public Type responseType() {
                    return parameterUpperBound;
                }

                public Call<Object> adapt(Call<Object> call) {
                    return executor == null ? call : new ExecutorCallbackCall(executor, call);
                }
            };
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }

    static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor executor, Call<T> call) {
            this.callbackExecutor = executor;
            this.delegate = call;
        }

        public void enqueue(final Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            this.delegate.enqueue(new Callback<T>() {
                public void onResponse(Call<T> call, Response<T> response) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(new C1472xee4a199d(this, callback, response));
                }

                /* renamed from: lambda$onResponse$0$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1 */
                public /* synthetic */ void mo24943xddacc936(Callback callback, Response response) {
                    if (ExecutorCallbackCall.this.delegate.isCanceled()) {
                        callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                    } else {
                        callback.onResponse(ExecutorCallbackCall.this, response);
                    }
                }

                /* renamed from: lambda$onFailure$1$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1 */
                public /* synthetic */ void mo24942x714e864(Callback callback, Throwable th) {
                    callback.onFailure(ExecutorCallbackCall.this, th);
                }

                public void onFailure(Call<T> call, Throwable th) {
                    ExecutorCallbackCall.this.callbackExecutor.execute(new C1471xee4a199c(this, callback, th));
                }
            });
        }

        public boolean isExecuted() {
            return this.delegate.isExecuted();
        }

        public Response<T> execute() throws IOException {
            return this.delegate.execute();
        }

        public void cancel() {
            this.delegate.cancel();
        }

        public boolean isCanceled() {
            return this.delegate.isCanceled();
        }

        public Call<T> clone() {
            return new ExecutorCallbackCall(this.callbackExecutor, this.delegate.clone());
        }

        public Request request() {
            return this.delegate.request();
        }

        public Timeout timeout() {
            return this.delegate.timeout();
        }
    }
}
