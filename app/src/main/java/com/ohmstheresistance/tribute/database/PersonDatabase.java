package com.ohmstheresistance.tribute.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;


@Database(entities = {Person.class}, version = 2)
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

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE person_table ADD COLUMN date_time TEXT");
        }
    };

}