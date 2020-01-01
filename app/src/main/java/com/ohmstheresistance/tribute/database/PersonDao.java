package com.ohmstheresistance.tribute.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PersonDao {


    @Insert
    void addPerson(Person person);

    @Update
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person person);

    @Query("DELETE FROM person_table")
    void deleteAllPersons();

    @Query("SELECT * FROM person_table ORDER BY personID ASC")
    LiveData<List<Person>> getAllPersons();
}

