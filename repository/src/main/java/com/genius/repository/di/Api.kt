package com.genius.repository.di

import androidx.annotation.StringDef
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @get:GET("$JOKES/random")
    val randomJoke: Deferred<Response<NorrisFactResponse>>

    @get:GET("$JOKES/count")
    val countOfJokes: Deferred<Response<Int>>

    @get:GET("$JOKES/categories")
    val categoriesOfJokes: Deferred<Response<List<String>>>

    @GET("$JOKES/random/{count}")
    fun randomJokes(
        @Path("count") count: Int = 20,
        @Query("limitTo") excludedCategories: Array<String>? = null
    ): Deferred<Response<List<NorrisFactResponse>>>

    companion object {
        const val SERVER = "https://api.icndb.com/"
        const val JOKES = "/jokes"
    }
}

data class Response<T>(@ResponseType val type: String, val value: T) {

    @StringDef(
        ResponseType.SUCCESS,
        ResponseType.FAILURE
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class ResponseType {
        companion object {
            const val SUCCESS = "success"
            const val FAILURE = "failure"
        }
    }
}

data class NorrisFactResponse(val id: Int, val joke: String, val categories: List<String>)