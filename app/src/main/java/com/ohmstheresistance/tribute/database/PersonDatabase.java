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

    private static final String DATABASE_NAME = "person_database";
    private static PersonDatabase personDatabaseInstance;
    public abstract PersonDao personDao();

    public static synchronized PersonDatabase getInstance(Context context) {
        if (personDatabaseInstance== null) {
            personDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    PersonDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return personDatabaseInstance;
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbAsyncTask(personDatabaseInstance).execute();
//        }
//    };
//
//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
//        private PersonDao personDao;
//
//        private PopulateDbAsyncTask(PersonDatabase db) {
//            personDao = db.personDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            personDao.addPerson(new Person("Omar", "soloproject@omar.org", "9191919191"));
//            personDao.addPerson(new Person("Gemma", "soloprojec2t@gemma.org", "9292929292"));
//            personDao.addPerson(new Person("Janet", "soloproject3@janet.org", "9393939393"));
//            personDao.addPerson(new Person("Troy", "soloproject4@troy.org", "9494949495"));
//            personDao.addPerson(new Person("Rayms", "soloproject5@rayms.org", "9595959595"));
//            personDao.addPerson(new Person("Dels", "soloproject6@dels.org", "9696969696"));
//            personDao.addPerson(new Person("Nathan", "soloproject7@nathan.org", "9797979797"));
//            personDao.addPerson(new Person("Emily", "soloproject8@emily.org", "9898989898"));
//            return null;
//        }
//    }
}