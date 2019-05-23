package com.genius.repository

import android.content.Context
import androidx.annotation.StringRes
import com.genius.repository.di.Api

interface IFeatureRandomJokeRepository {
    suspend fun fetchJoke(): String
    fun getString(@StringRes resId: Int): String
}

class FeatureRepositoryImpl(private val api: Api, private val context: Context) : IFeatureRandomJokeRepository {

    override suspend fun fetchJoke(): String {
        return api.randomJoke.await().value.joke
    }

    override fun getString(@StringRes resId: Int): String = context.getString(resId)
}

