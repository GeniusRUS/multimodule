package com.genius.multimodule.di.components

import android.content.Context
import com.genius.repository.di.FeatureJokeRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureJokeRepositoryModule::class])
interface AppComponent {

    val context: Context

    fun featureJoke(): FeatureJokeSubcomponent

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }
}