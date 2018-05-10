package ru.kpfu.itis.abiturkfu.model.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.model.database_module.AbiturientDatabase;
import ru.kpfu.itis.abiturkfu.model.entities.Facility;
import ru.kpfu.itis.abiturkfu.model.entities.Result;
import ru.kpfu.itis.abiturkfu.model.network_module.AbiturientService;

/**
 * Repository class
 * Use this class to get any information in view in {@link LiveData} form
 */
public class AbiturientRepository {
    private static final int STATUS_OK = 200;
    private static MutableLiveData<String> statusLiveData;

    @Inject
    AbiturientService service;

    @Inject
    AbiturientDatabase database;

    public AbiturientRepository() {
        App.getComponent().inject(this);
        statusLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Facility>> getAllFacilities(boolean forceNetLoad) {
        if (forceNetLoad) {
            updateAllFacilities();
        } else {
            statusLiveData.postValue(null);
        }
        return database.getFacilityDao().getAllFacilities();
    }

    public LiveData<Facility> getFacilityById(int id, boolean forceNetLoad) {
        if (forceNetLoad) {
            updateFacilityById(id);
        } else {
            statusLiveData.postValue(null);
        }
        return database.getFacilityDao().getFacilityById(id);
    }

    /**
     * This method download only from network
     */
    public LiveData<List<Facility>> getFacilitiesByFilter(@Nullable List<String> cities, @Nullable List<String> forms, @Nullable List<String> types) {
        MutableLiveData<List<Facility>> data = new MutableLiveData<>();
        Loader<List<Facility>> loader = result -> {
            database.getFacilityDao().insertAll(result.getBody().toArray(new Facility[result.getBody().size()]));
            data.postValue(result.getBody());
        };
        loader.load(service.getAllFacilitiesByFilter(cities, forms, types));
        return data;
    }

    @SuppressLint("CheckResult")
    private void updateAllFacilities() {
        Loader<List<Facility>> loader = result -> {
            database.getFacilityDao().clearAll();
            database.getFacilityDao().insertAll(result.getBody().toArray(new Facility[result.getBody().size()]));
        };
        loader.load(service.getAllFacilities());
    }

    @SuppressLint("CheckResult")
    private void updateFacilityById(int id) {
        Loader<Facility> loader = result -> database.getFacilityDao().insertAll(result.getBody());
        loader.load(service.getFacilityById(id));
    }

    /**
     * This is {@link LiveData} where came all errors in loading time
     */
    public LiveData<String> getStatusLiveData() {
        return statusLiveData;
    }

    /**
     * Use this for default loading and error handling
     * Result returns to 'set' method
     */
    private interface Loader<T> {
        void set(Result<T> result);

        @SuppressLint("CheckResult")
        default void load(Observable<Result<T>> observable) {
            observable
                    .observeOn(Schedulers.computation())
                    .switchMap(listResult -> {
                        if (listResult.getStatus() != STATUS_OK) {
                            return ObservableError.error(new Exception(listResult.getErrors()));
                        }
                        return Observable.just(listResult);
                    })
                    .subscribe(
                            this::set,
                            throwable -> statusLiveData.postValue(throwable.getMessage()),
                            () -> statusLiveData.postValue(null)
                    );
        }

    }
}
