package com.android.pension_app.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.android.pension_app.data.local.PensionDatabase;
import com.android.pension_app.data.local.SharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {
    @Provides
    public PensionDatabase provideYourDatabase(Application application) {
        return Room.databaseBuilder(application, PensionDatabase.class, "pension_database")
                .build();
    }

    @Singleton
    @Provides
    public SharedPref providesSharedPref(@ApplicationContext Context context) {
        return new SharedPref(context);
    }
}

