package com.ohmstheresistance.tribute.database;


import java.util.List;

import io.reactivex.Flowable;

public class PersonRepository implements ModifyPerson{

    private static PersonRepository personInstance;
    private PersonDao personDao;

    public PersonRepository(PersonDao personDao) {
        this.personDao = personDao;
    }

    public static PersonRepository getPersonInstance(PersonDao personDao){
        if(personInstance == null){

            personInstance = new PersonRepository(personDao);
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