package com.ohmstheresistance.tribute.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PersonRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;

    public PersonRepository(Application application) {
        PersonDatabase database = PersonDatabase.getInstance(application);
        personDao = database.personDao();
        allPersons = personDao.getAllPersons();
    }

    public void addPerson(Person person) {
        new InsertNoteAsyncTask(personDao).execute(person);
    }

    public void updatePerson(Person person) {
        new UpdateNoteAsyncTask(personDao).execute(person);
    }

    public void deletePerson(Person person) {
        new DeleteNoteAsyncTask(personDao).execute(person);
    }

    public void deleteAllPersons() {
        new DeleteAllNotesAsyncTask(personDao).execute();
    }

    public LiveData<List<Person>> getAllPersons() {
        return allPersons;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private InsertNoteAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... notes) {
            personDao.addPerson(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private UpdateNoteAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.updatePerson(persons[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private DeleteNoteAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... notes) {
            personDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao personDao;

        private DeleteAllNotesAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.deleteAllNotes();
            return null;
        }
    }
}