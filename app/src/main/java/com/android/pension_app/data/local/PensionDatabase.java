package com.android.pension_app.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.pension_app.data.local.dao.UserDao;
import com.android.pension_app.data.local.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
    public abstract class PensionDatabase extends RoomDatabase {
        public abstract UserDao userDao();
    }
