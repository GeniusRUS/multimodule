package com.genius.multimodule

import android.app.Application
import com.genius.core.Core
import com.genius.multimodule.di.components.AppComponent
import com.genius.multimodule.di.components.DaggerAppComponent
import com.genius.multimodule.di.components.FeatureJokeSubcomponent

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Core.context = applicationContext
        appComponent = DaggerAppComponent.builder()
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent

        private var featureJokeSubcomponent: FeatureJokeSubcomponent? = null

        fun getFeatureJokeSubcomponent(): FeatureJokeSubcomponent {
            if (featureJokeSubcomponent == null) {
                featureJokeSubcomponent = appComponent.featureJoke()
            }

            return featureJokeSubcomponent ?: throw IllegalStateException("$featureJokeSubcomponent must be initialized")
        }

        fun removeFeatureJokeSubcomponent() {
            featureJokeSubcomponent = null
        }
    }
}