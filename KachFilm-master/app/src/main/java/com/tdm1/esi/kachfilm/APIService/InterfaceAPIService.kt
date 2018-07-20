package com.tdm1.esi.kachfilm.APIService

/**
 * Created by mac on 17/06/2018.
 */


import com.tdm1.esi.kachfilm.DataClass.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface InterfaceAPIService {


    @Headers("Accept:application/json")
    @GET("genre/movie/list")
    fun getGenresListeForMovie( @Query("api_key") api_key:String):Observable<Response<ResponseGenresMovie>>


    @Headers("Accept:application/json")
    @GET("movie/now_playing")
    fun getFilmEnProjection( @Query("api_key") api_key:String):Observable<Response<ResponseMovieNow>>


    @Headers("Accept:application/json")
    @GET("movie/{movie_id}/similar")
    fun getSimilaireMovie(@Path("movie_id")movie_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseMovieSimilaire>>


    @Headers("Accept:application/json")
    @GET("movie/{movie_id}/reviews")
    fun getCritiquesFilm(@Path("movie_id")movie_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseCritique>>

    @Headers("Accept:application/json")
    @GET("movie/{movie_id}/credits")
    fun getActorLies(@Path("movie_id")movie_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseActorLies>>

    @Headers("Accept:application/json")
    @GET("person/popular")
    fun getStars(@Query("api_key") api_key:String):Observable<Response<ResponseStars>>


    @Headers("Accept:application/json")
    @GET("person/{person_id}/images")
    fun getStarImage(@Path("person_id")person_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseImageStar>>

    @Headers("Accept:application/json")
    @GET("person/{person_id}")
    fun getInfoStar(@Path("person_id")person_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseInfoStar>>

    @Headers("Accept:application/json")
    @GET("tv/on_the_air")
    fun getSeriesEnCours( @Query("api_key") api_key:String):Observable<Response<ReponseSeries>>

    @Headers("Accept:application/json")
    @GET("tv/popular")
    fun getSeriesPopulaire( @Query("api_key") api_key:String):Observable<Response<ReponseSeries>>


    @Headers("Accept:application/json")
    @GET("tv/{tv_id}/reviews")
    fun getCritiquesSerie(@Path("tv_id")tv_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseCritique>>

    @Headers("Accept:application/json")
    @GET("tv/{tv_id}/similar")
    fun getSimilaireSerie(@Path("tv_id")movie_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseSerieSimilaire>>

    @Headers("Accept:application/json")
    @GET("tv/{tv_id}")
    fun getDetailSerie(@Path("tv_id")tv_id:Int, @Query("api_key") api_key:String):Observable<Response<ResponseSerieDetail>>


    companion object {

        val URL = "https://api.themoviedb.org/3/"

        var instance: InterfaceAPIService ? = null

        fun create(): InterfaceAPIService  {

            if (instance == null) {
                val retrofit = Retrofit.Builder()
                        .addCallAdapterFactory(
                                RxJava2CallAdapterFactory.create())
                        .addConverterFactory(
                                GsonConverterFactory.create())
                        .baseUrl(URL)
                        .build()
                instance = retrofit.create(InterfaceAPIService ::class.java)
            }
            return instance!!

        }

    }
}