package com.ohmstheresistance.tribute;

import android.content.Context;

import com.ohmstheresistance.tribute.database.PersonDatabase;
import com.ohmstheresistance.tribute.model.Buttons;
import com.ohmstheresistance.tribute.model.Fellows;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MethodTester {

    private PersonDatabase personDatabase;
    private Context testContext;

    @Test
    public void databaseChecker() {

        PersonDatabase personDatabaseExpected = PersonDatabase.getInstance(testContext);
        Assert.assertEquals(personDatabaseExpected, PersonDatabase.getInstance(testContext));

    }
}

