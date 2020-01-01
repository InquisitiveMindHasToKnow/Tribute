package com.ohmstheresistance.tribute.database;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private static PersonViewModel personInstance;
    private PersonDao personDao;

    public PersonViewModel(PersonDao personDao) {
        this.personDao = personDao;
    }

    public static PersonViewModel getPersonInstance(PersonDao personDao){
        if(personInstance == null){
            personInstance = new PersonViewModel(personDao);
        }
        return personInstance;

    }


    @Override
    public void addPerson(Person... persons) {

        personDao.addPerson(persons);
    }

    @Override
    public void updatePerson(Person... persons) {

        personDao.updatePerson(persons);
    }

    @Override
    public void deletePerson(Person person) {

        personDao.deletePerson(person);
    }

    @Override
    public void deleteAllPersons() {

        personDao.deleteAllPersons();
    }

    @Override
    public LiveData<List<Person>> getAllPersons() {

        return personDao.getAllPersons();
    }
}