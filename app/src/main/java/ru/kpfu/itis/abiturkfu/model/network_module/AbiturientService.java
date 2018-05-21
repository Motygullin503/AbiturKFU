package ru.kpfu.itis.abiturkfu.model.network_module;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.kpfu.itis.abiturkfu.model.entities.Facility;
import ru.kpfu.itis.abiturkfu.model.entities.Result;
import ru.kpfu.itis.abiturkfu.model.entities.Speciality;


public interface AbiturientService {

    @GET("api/v1/facultet/")
    Observable<Result<List<Facility>>> getAllFacilities();

    @GET("api/v1/facultet/")
    Observable<Result<List<Facility>>> getAllFacilitiesByFilter(
            @Query("city[]") List<String> cities,
            @Query("form[]") List<String> forms,
            @Query("type[]") List<String> types);


    @GET("api/v1/facultet/{id}")
    Observable<Result<Facility>> getFacilityById(@Path("id") int id);

    @GET("/api/v1/specialty")
    Observable<Result<List<Speciality>>> getSpecialitiesByFacilityId(@Query("facultet_id") int facilityId);

    @GET("/api/v1/specialty/{id}")
    Observable<Result<Speciality>> getSpecialitiesById(@Path("id") int id);
}
