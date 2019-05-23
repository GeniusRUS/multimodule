package com.genius.multimodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.genius.joke.FeatureRandomJoke
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    @Inject lateinit var feature1: FeatureRandomJoke

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseApplication.getFeatureJokeSubcomponent().inject(this)

        launch {
            tv_text.text = withContext(Dispatchers.IO) { feature1.fetchJoke() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        BaseApplication.removeFeatureJokeSubcomponent()
    }
}
