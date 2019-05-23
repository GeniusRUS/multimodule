package com.genius.joke

import com.genius.repository.IFeatureRandomJokeRepository

class FeatureRandomJoke(private val repository: IFeatureRandomJokeRepository) {

    suspend fun fetchJoke(): String {
        return String.format(repository.getString(R.string.string_template), repository.fetchJoke())
    }
}