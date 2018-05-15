package ru.kpfu.itis.abiturkfu.model.repository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

public class ResponseLiveData<T> {
    protected MutableLiveData<T> bodyLiveData;
    protected MutableLiveData<Status> statusLiveData;
    protected MutableLiveData<Throwable> throwableLiveData;

    public ResponseLiveData() {
        bodyLiveData = new MutableLiveData<>();
        statusLiveData = new MutableLiveData<>();
        throwableLiveData = new MutableLiveData<>();
        statusLiveData.postValue(Status.HANDLE);
    }

    public ResponseLiveData(CachingDataProvider<T> dataProvider) {
        bodyLiveData = new MutableLiveData<>();
        statusLiveData = new MutableLiveData<>();
        throwableLiveData = new MutableLiveData<>();
        statusLiveData.postValue(Status.HANDLE);
        dataProvider.getCachedData().observeForever(this::postBody);
    }

    public void observe(LifecycleOwner owner, Observer<T> bodyObserver, Observer<Status> statusObserver, Observer<Throwable> errorObserver) {
        bodyLiveData.observe(owner, bodyObserver);
        statusLiveData.observe(owner, statusObserver);
        throwableLiveData.observe(owner, errorObserver);
    }

    public LiveData<T> getBodyLiveData() {
        return bodyLiveData;
    }

    public LiveData<Status> getStatusLiveData() {
        return statusLiveData;
    }

    public LiveData<Throwable> getThrowableLiveData() {
        return throwableLiveData;
    }

    public void postStatus(Status status) {
        statusLiveData.postValue(status);
    }

    public void postBody(T body) {
        bodyLiveData.postValue(body);
    }

    public void postError(Throwable throwable) {
        throwableLiveData.postValue(throwable);
    }

    public enum Status {
        HANDLE,
        LOADING
    }

    public interface CachingDataProvider<T> {
        LiveData<T> getCachedData();
    }
}
