package com.ohmstheresistance.tribute.database;

import java.util.List;
import io.reactivex.Flowable;

public interface ModifyPerson {


        void addPerson(Person... persons);
        void updatePerson(Person... persons);
        void deletePerson(Person person);
        void deleteAllPersons();
        Flowable<List<Person>> getAllPersons();
    }




