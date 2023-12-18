package com.ginsebu.roomcompose

import android.content.Context
import androidx.room.Room
import com.ginsebu.roomcompose.contacts.ContactDatabase

interface AppModule {
    val db: ContactDatabase
}
class AppModuleImpl(
    private val appContext: Context
) : AppModule {
    override val db: ContactDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            ContactDatabase::class.java,
            "contacts.db",
        ).build()
    }
}