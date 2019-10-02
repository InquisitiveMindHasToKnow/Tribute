package com.ohmstheresistance.tribute.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "person_table")
public class Person {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int personID;

    private String personName;
    private String personEmail;
    private String personPhoneNumber;
    private String personNotes;


    public Person( String personName, String personPhoneNumber, String personEmail, String personNotes) {

        this.personName = personName;
        this.personPhoneNumber = personPhoneNumber;
        this.personEmail = personEmail;
        this.personNotes = personNotes;
    }

    @Ignore
    public Person( int PersonID, String personName, String personPhoneNumber, String personEmail, String personNotes) {

        this.personID = getPersonID();
        this.personName = personName;
        this.personPhoneNumber = personPhoneNumber;
        this.personEmail = personEmail;
        this.personNotes = personNotes;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getPersonName() {

        return personName;
    }

    public String getPersonEmail() {

        return personEmail;
    }

    public String getPersonPhoneNumber() {

        return personPhoneNumber;
    }

    public String getPersonNotes(){
        return personNotes;
    }



    @Override
    public String toString() {
        return new StringBuilder(personName).append("\n").append(personPhoneNumber).append("\n").append(personEmail).append("\n").append(personNotes).toString();

    }

}
