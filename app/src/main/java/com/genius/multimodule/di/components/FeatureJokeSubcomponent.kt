package com.genius.multimodule.di.components

import com.genius.joke.di.FeatureRandomJokeScope
import com.genius.multimodule.MainActivity
import com.genius.multimodule.di.modules.FeatureJokeModule
import dagger.Subcomponent

@FeatureRandomJokeScope
@Subcomponent(modules = [FeatureJokeModule::class])
interface FeatureJokeSubcomponent {
    fun inject(inject: MainActivity)
}