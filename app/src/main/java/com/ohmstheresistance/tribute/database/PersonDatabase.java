package com.ohmstheresistance.tribute.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

        private static final String DATABASE_NAME = "person_database";
        private static com.ohmstheresistance.tribute.database.PersonDatabase personDatabaseInstance;
        public abstract PersonDao personDao();

        public static synchronized com.ohmstheresistance.tribute.database.PersonDatabase getInstance(Context context) {
            if (personDatabaseInstance== null) {
                personDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        com.ohmstheresistance.tribute.database.PersonDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return personDatabaseInstance;
        }

}