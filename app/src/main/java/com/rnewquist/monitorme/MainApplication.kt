package com.rnewquist.monitorme

import android.app.Application
import android.widget.TextView

import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .inMemory()
                        .build())
    }
}
