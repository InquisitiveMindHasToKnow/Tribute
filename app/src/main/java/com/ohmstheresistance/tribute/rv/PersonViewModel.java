package com.ohmstheresistance.tribute.rv;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.database.PersonDao;
import com.ohmstheresistance.tribute.database.PersonRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PersonViewModel extends AndroidViewModel {
    private PersonRepository personRepository;
    private PersonDao personDao;


    public MutableLiveData<List<Person>> allPersons = new MutableLiveData<>();
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        personDao = personRepository.getPersonDao();
    }

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public void getAllPersons() {
        compositeDisposable.add(Single.just(personDao.getAllPersons())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                            allPersons.setValue(items);
                        },
                        Throwable::printStackTrace));
    }

    public void updatePerson(Person person) {
        personRepository.updatePerson(person);
    }

    public void deletePerson(Person person) {
        personRepository.deletePerson(person);
    }

    public void deleteAllPersons() {
        personRepository.deleteAllPersons();
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
