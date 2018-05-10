package ru.kpfu.itis.abiturkfu.model.network_module;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kpfu.itis.abiturkfu.BuildConfig;

@Module
public class NetModule {
    public static final String BASE_URL = "https://kpfu-abiturient-api.herokuapp.com/";

    @NonNull
    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
    }

    @NonNull
    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(BASE_URL)
                .client(client)
                .build();
    }

    /**
     * Service for loading from network
     * all requests in {@link AbiturientService} class
     */
    @NonNull
    @Provides
    @Singleton
    AbiturientService getService(Retrofit retrofit) {
        return retrofit.create(AbiturientService.class);
    }

    @NonNull
    @Provides
    @Singleton
    HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return interceptor;
    }
}
