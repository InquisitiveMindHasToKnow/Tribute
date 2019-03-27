package com.ohmstheresistance.tribute.database;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private PersonDao personDao;
    private List<Person> allPersons = new ArrayList<>();

    public PersonRepository(Application application) {
        PersonDatabase database = PersonDatabase.getInstance(application);
        personDao = database.personDao();
    }

    public void addPerson(Person person) {
        new AddPersonAsyncTask(personDao).execute(person);
    }

    public void updatePerson(Person person) {
        new UpdatePersonAsyncTask(personDao).execute(person);
    }

    public void deletePerson(Person person) {
        new DeletePersonAsyncTask(personDao).execute(person);
    }

    public void deleteAllPersons() {
        new DeleteAllPersonsAsyncTask(personDao).execute();
    }

    public List<Person> getAllPersons() {
        return personDao.getAllPersons();
    }

    public PersonDao getPersonDao(){
        return personDao;
    }

    private static class AddPersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private AddPersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.addPerson(persons[0]);
            return null;
        }
    }

    private static class UpdatePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private UpdatePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.updatePerson(persons[0]);
            return null;
        }
    }

    private static class DeletePersonAsyncTask extends AsyncTask<Person, Void, Void> {
        private PersonDao personDao;

        private DeletePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.delete(persons[0]);
            return null;
        }
    }

    private static class DeleteAllPersonsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PersonDao personDao;

        private DeleteAllPersonsAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.deleteAllPersons();
            return null;
        }
    }
}