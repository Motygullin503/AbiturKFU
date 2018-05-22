package ru.kpfu.itis.abiturkfu.model.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.abiturkfu.App;
import ru.kpfu.itis.abiturkfu.model.database_module.AbiturientDatabase;
import ru.kpfu.itis.abiturkfu.model.entities.Course;
import ru.kpfu.itis.abiturkfu.model.entities.Facility;
import ru.kpfu.itis.abiturkfu.model.entities.PartnerAndCommittee;
import ru.kpfu.itis.abiturkfu.model.entities.Result;
import ru.kpfu.itis.abiturkfu.model.entities.Speciality;
import ru.kpfu.itis.abiturkfu.model.network_module.AbiturientService;

/**
 * Repository class
 * Use this class to get any information in view in {@link LiveData} form
 */
public class AbiturientRepository {
    private static final int STATUS_OK = 200;

    @Inject
    AbiturientService service;

    @Inject
    AbiturientDatabase database;

    public AbiturientRepository() {
        App.getComponent().inject(this);
    }

    public ResponseLiveData<List<Facility>> getAllFacilities(boolean forceNetLoad) {
        ResponseLiveData<List<Facility>> data = new ResponseLiveData<>(() -> database.getFacilityDao().getAllFacilities());
        if (forceNetLoad) {
            Loader<List<Facility>> loader = result -> {
                database.getFacilityDao().clearAll();
                database.getFacilityDao().insertAll(result.toArray(new Facility[result.size()]));
            };
            loader.load(service.getAllFacilities(), data);
        }
        return data;
    }

    public ResponseLiveData<Facility> getFacilityById(final int id, boolean forceNetLoad) {
        ResponseLiveData<Facility> data = new ResponseLiveData<>(() -> database.getFacilityDao().getFacilityById(id));
        if (forceNetLoad) {
            Loader<Facility> loader = body -> database.getFacilityDao().insertAll(body);
            loader.load(service.getFacilityById(id), data);
        }
        return data;
    }

    /**
     * This method download only from network
     */
    public ResponseLiveData<List<Facility>> getFacilitiesByFilter(@Nullable List<String> cities, @Nullable List<String> forms, @Nullable List<String> types) {
        ResponseLiveData<List<Facility>> data = new ResponseLiveData<>();
        Loader<List<Facility>> loader = result -> {
            database.getFacilityDao().insertAll(result.toArray(new Facility[result.size()]));
            data.postBody(result);
        };
        loader.load(service.getAllFacilitiesByFilter(cities, forms, types), data);
        return data;
    }

    public ResponseLiveData<List<Speciality>> getSpecialitiesByFacilityId(int facilityId) {
        ResponseLiveData<List<Speciality>> data = new ResponseLiveData<>();
        Loader<List<Speciality>> loader = data::postBody;
        loader.load(service.getSpecialitiesByFacilityId(facilityId), data);
        return data;
    }

    public ResponseLiveData<Speciality> getSpecialityById(int id) {
        ResponseLiveData<Speciality> data = new ResponseLiveData<>();
        Loader<Speciality> loader = data::postBody;
        loader.load(service.getSpecialitiesById(id), data);
        return data;
    }

    public ResponseLiveData<List<Course>> getCourses() {
        ResponseLiveData<List<Course>> data = new ResponseLiveData<>();
        Loader<List<Course>> loader = data::postBody;
        loader.load(service.getCourses(), data);
        return data;
    }

    public ResponseLiveData<Course> getCourseById(int id) {
        ResponseLiveData<Course> data = new ResponseLiveData<>();
        Loader<Course> loader = data::postBody;
        loader.load(service.getCourseById(id), data);
        return data;
    }

    public ResponseLiveData<PartnerAndCommittee> getAboutUs() {
        ResponseLiveData<PartnerAndCommittee> data = new ResponseLiveData<>();
        Loader<PartnerAndCommittee> loader = data::postBody;
        loader.load(service.getAboutUs(), data);
        return data;
    }

    /**
     * Use this for default loading and error handling
     * Result returns to 'set' method
     * Make {@link ResponseLiveData}.postBody(body) in {@code set} method if you don`t use caching
     */
    private interface Loader<T> {
        void set(T body);

        @SuppressLint("CheckResult")
        default void load(Observable<Result<T>> observable, ResponseLiveData<T> responseLiveData) {
            responseLiveData.postStatus(ResponseLiveData.Status.LOADING);
            observable
                    .observeOn(Schedulers.computation())
                    .switchMap(result -> {
                        if (result.getStatus() != STATUS_OK) {
                            return ObservableError.error(new Exception(result.getErrors()));
                        }
                        return Observable.just(result);
                    })
                    .subscribe(
                            tResult -> {
                                responseLiveData.postStatus(ResponseLiveData.Status.HANDLE);
                                set(tResult.getBody());
                            },
                            throwable -> {
                                responseLiveData.postStatus(ResponseLiveData.Status.HANDLE);
                                responseLiveData.postError(throwable);
                            }
                    );
        }

    }
}
