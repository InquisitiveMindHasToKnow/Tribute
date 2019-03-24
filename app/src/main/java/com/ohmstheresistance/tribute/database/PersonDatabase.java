package com.ohmstheresistance.tribute.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    private static PersonDatabase instance;

    public abstract PersonDao personDao();

    public static synchronized PersonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PersonDatabase.class, "person_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao noteDao;

        private PopulateDbAsyncTask(PersonDatabase db) {
            noteDao = db.personDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.addPerson(new Person("Omar", "Male"));
            noteDao.addPerson(new Person("Gemma", "Female"));
            noteDao.addPerson(new Person("Catherine", "Female"));
            return null;
        }
    }
}