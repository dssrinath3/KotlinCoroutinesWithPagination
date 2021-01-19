package com.example.kotlincoroutinespagination.pagingdetails.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
        val id: Int,
        @Json(name = "backdrop_path") val backdrop_path: String?,
        @Json(name = "overview") val overview: String?,
        @Json(name = "adult") val adult: Boolean?,
        @Json(name = "genre_ids") val genreIds: List<Int>?,
        @Json(name = "original_language") val originalLanguage: String?,
        @Json(name = "original_title") val originalTitle: String?,
        @Json(name = "popularity") val popularity: Float?,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "release_date") val releaseDate: String?,
        @Json(name = "title") val title: String?,
        @Json(name = "video") val video: Boolean?,
        @Json(name = "vote_average") val voteAverage: Float?,
        @Json(name = "vote_count") val voteCount: Int?

)
