package com.ohmstheresistance.tribute.database;

import java.util.List;

import io.reactivex.Flowable;

public class PersonRepository implements ModifyPerson {

    private PersonDataSource personDataSource;
    private static PersonRepository personRepositoryInstance;

    public PersonRepository(PersonDataSource personDataSource) {
        this.personDataSource = personDataSource;
    }

    public static PersonRepository getInstance(PersonDataSource personDataSource){
        if (personRepositoryInstance == null){

            personRepositoryInstance = new PersonRepository(personDataSource);
        }
        return personRepositoryInstance;
    }

    @Override
    public void addPerson(Person... persons) {

        personDataSource.addPerson(persons);

    }

    @Override
    public void updatePerson(Person... persons) {
        personDataSource.updatePerson(persons);

    }

    @Override
    public void deletePerson(Person person) {

        personDataSource.deletePerson(person);
    }

    @Override
    public void deleteAllPersons() {

        personDataSource.deleteAllPersons();

    }

    @Override
    public Flowable<List<Person>> getAllPersons() {
        return personDataSource.getAllPersons();
    }
}
