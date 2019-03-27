package com.ohmstheresistance.tribute.rv;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class PersonViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public PersonViewModelFactory(Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(modelClass)){
            return (T) new PersonViewModel(application);
        }else{
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
