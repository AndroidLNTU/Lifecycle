package k.studio.lifecycle

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        "MyApp onCreate".logD()
    }
}