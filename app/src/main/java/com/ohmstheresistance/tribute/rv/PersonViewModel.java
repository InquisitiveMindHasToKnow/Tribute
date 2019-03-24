package com.ohmstheresistance.tribute.rv;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {
    private PersonRepository personRepository;
    private LiveData<List<Person>> allPersons;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        allPersons = personRepository.getAllPersons();
    }

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public void updatePerson (Person person) {
        personRepository.updatePerson(person);
    }

    public void deletePerson(Person person) {
        personRepository.deletePerson(person);
    }

    public void deleteAllPersons() {
        personRepository.deleteAllPersons();
    }

    public LiveData<List<Person>> getAllPersons() {
        return allPersons;
    }
}
