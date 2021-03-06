package com.example.finallabassignmentc0773774;
//https://github.com/nathanjaswal/FinalLabAssignmentC0773774.git
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserM.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase = null;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database_user").allowMainThreadQueries().build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();
}


