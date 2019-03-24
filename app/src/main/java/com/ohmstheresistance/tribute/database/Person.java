package com.ohmstheresistance.tribute.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int personID;

    private String personName;
    private String personGender;

    public Person(String personName, String personGender) {
        this.personName = personName;
        this.personGender = personGender;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonGender() {
        return personGender;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }
}
