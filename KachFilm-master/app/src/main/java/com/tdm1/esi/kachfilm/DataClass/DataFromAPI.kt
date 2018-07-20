package com.tdm1.esi.kachfilm.DataClass

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 * Created by mac on 17/06/2018.
 */

data class SpokenLanguage (

        @SerializedName("iso_639_1") val iso_639_1: String,
        @SerializedName("name") val name: String
)

data class ProductionCountrie(
        @SerializedName("iso_3166_1") val iso_3166_1: String,
        @SerializedName("name") val name: String

)

data class ProductionCompanie(

        @SerializedName("id") val id: Int,
        @SerializedName("logo_path") val logo_path: String,
        @SerializedName("name") val name: String,
        @SerializedName("origin_country") val origin_country: String
)

data class genre(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String
)


data class ResponseGenresMovie(
        @SerializedName("genres") val genres:Array<genre>
)

data class Movie(
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("backdrop_path") val backdrop_path: String,
        @SerializedName("belongs_to_collection") val belongs_to_collection: String,
        @SerializedName("budget") val budget: Double,
        @SerializedName("genres") val genres:Array<genre>,
        @SerializedName("homepage") val homepage: String,
        @SerializedName("id") val id: Int,
        @SerializedName("imdb_id") val imdb_id: String,
        @SerializedName("original_language") val original_language: String,
        @SerializedName("original_title") val original_title: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("production_companies") val production_companies:Array<ProductionCompanie>,
        @SerializedName("production_countries") val production_countries:Array<ProductionCountrie>,
        @SerializedName("release_date") val release_date: String,
        @SerializedName("revenue") val revenue: Double,
        @SerializedName("runtime") val runtime: String,
        @SerializedName("spoken_languages") val spoken_languages:Array<SpokenLanguage>,
        @SerializedName("status") val status: String,
        @SerializedName("tagline") val tagline: String,
        @SerializedName("title") val title: String,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val vote_average: Double,
        @SerializedName("vote_count") val vote_count: Int

)

data class IntervalleDate (
        @SerializedName("maximum") val maximum: String,
        @SerializedName("minimum") val minimum: String
)
data class ResponseMovieNow(

        @SerializedName("results") val results:Array<MovieNow>,
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val total_results: Int,
        //       @SerializedName("dates") val dares:Array<IntervalleDate>,
        @SerializedName("total_pages") val total_pages: Int
)

data class ResponseMovieSimilaire(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results:Array<MovieNow>,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("total_results") val total_results: Int


)


data class MovieNow(
        @SerializedName("vote_count") val vote_count: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val vote_average: Double,
        @SerializedName("title") val title: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("original_language") val original_language: String,
        @SerializedName("original_title") val original_title: String,
        @SerializedName("genre_ids") val genre_ids:Array<Int>,
        @SerializedName("backdrop_path") val backdrop_path: String,
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val release_date: String
)


data class ResponseCritique(
        @SerializedName("id") val id: Int,
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results:Array<Critique>,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("total_results") val total_results: Int

)

data class Critique(
        @SerializedName("id") val id: String,
        @SerializedName("author") val author: String,
        @SerializedName("content") val content: String,
        @SerializedName("url") val url: String
)


data class ResponseStars(

        @SerializedName("page") val page: Int,
        @SerializedName("results") val results:Array<Personne>,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("total_results") val total_results: Int

)

data class Personne(
        @SerializedName("id") val id: Int,
        @SerializedName("known_for") val known_for:Array<MovieNow>,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("name") val name: String
)

data class profiles(
        @SerializedName("file_path") val file_path: String
)

data class ResponseImageStar(

        @SerializedName("id") val id: Int,
        @SerializedName("profiles") val profiles:Array<profiles>
)

data class ResponseInfoStar(
        @SerializedName("id") val id: Int,
        @SerializedName("biography") val biography: String,
        @SerializedName("birthday") val birthday:String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("name") val name: String,
        @SerializedName("place_of_birth") val place_of_birth: String
)



data class SerieNow(
        @SerializedName("vote_count") val vote_count: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val vote_average: Double,
        @SerializedName("name") val name: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("original_language") val original_language: String,
        @SerializedName("original_name") val original_name: String,
        @SerializedName("genre_ids") val genre_ids:Array<Int>,
        @SerializedName("backdrop_path") val backdrop_path: String,
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("overview") val overview: String,
        @SerializedName("first_air_date") val first_air_date: String
)

data class ReponseSeries(

        @SerializedName("page") val page: Int,
        @SerializedName("results") val results:Array<SerieNow>,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("total_results") val total_results: Int

)


data class ResponseSerieSimilaire(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results:Array<SerieNow>,
        @SerializedName("total_pages") val total_pages: Int,
        @SerializedName("total_results") val total_results: Int

)

data class InfoSaison(
        @SerializedName("air_date") val air_date: String,
        @SerializedName("episode_count") val episode_count: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("season_number") val season_number: Int
)
data class ResponseSerieDetail(
        @SerializedName("seasons") val seasons:Array<InfoSaison>
)

data class ResponseActorLies(
@SerializedName("id") val id: Int,
@SerializedName("cast") val cast:Array<infoStarLies>

)

data class infoStarLies(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("profile_path") val profile_path: String

)