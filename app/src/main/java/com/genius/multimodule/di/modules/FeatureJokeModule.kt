package com.genius.multimodule.di.modules

import com.genius.joke.FeatureRandomJoke
import com.genius.joke.di.FeatureRandomJokeScope
import com.genius.repository.IFeatureRandomJokeRepository
import dagger.Module
import dagger.Provides

@Module
class FeatureJokeModule {

    @Provides
    @FeatureRandomJokeScope
    fun provideFeatureRandomJoke(repository: IFeatureRandomJokeRepository): FeatureRandomJoke {
        return FeatureRandomJoke(repository)
    }
}