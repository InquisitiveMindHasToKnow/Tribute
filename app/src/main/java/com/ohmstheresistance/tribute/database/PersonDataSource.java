package com.ohmstheresistance.tribute.database;

import java.util.List;

import io.reactivex.Flowable;

public class PersonDataSource implements ModifyPerson{

    private static PersonDataSource personInstance;
    private PersonDao personDao;

    public PersonDataSource(PersonDao personDao) {
        this.personDao = personDao;
    }

    public static PersonDataSource getPersonInstance(PersonDao personDao){
        if(personInstance == null){
            personInstance = new PersonDataSource(personDao);
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
    public Flowable<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }
}