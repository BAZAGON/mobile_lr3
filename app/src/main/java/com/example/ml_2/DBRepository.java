package com.example.ml_2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.concurrent.Callable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class DBRepository {
    private DAOChraracter daoChraracter;

    private List<Character> allcahrs;

    DBRepository(Application application){
        DataBase db = DataBase.getDataBase(application);
        daoChraracter = db.daoChraracter();
        allcahrs = getAllChars();
    }


    void Insert(Character chert) {
        new InsertCharacterAsyncTask(daoChraracter).execute(chert);
    }

    void ClearAll() {
        new ClearAllCharactersAsyncTask(daoChraracter).execute();
    }

    void UpdateCharacter(Character chert){new UpdateCharacterAsyncTask(daoChraracter).execute(chert);}

    Character getCharacter(int id){
        return getCharacterAsyncTask(id);
    }

    private static class InsertCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAOChraracter daoChraracter;


        private InsertCharacterAsyncTask(DAOChraracter daoChraracter) {
            this.daoChraracter = daoChraracter;
        }

        @Override
        protected Void doInBackground(Character... chars) {
            daoChraracter.addCharacter(chars[0]);

            return null;
        }
    }

    private static class UpdateCharacterAsyncTask extends AsyncTask<Character, Void, Void> {
        private DAOChraracter daoChraracter;


        private UpdateCharacterAsyncTask(DAOChraracter daoChraracter) {
            this.daoChraracter = daoChraracter;
        }

        @Override
        protected Void doInBackground(Character... chars) {
            daoChraracter.updateCharacter(chars[0]);

            return null;
        }
    }

    private static class ClearAllCharactersAsyncTask extends AsyncTask<Void, Void, Void> {
        private DAOChraracter daoChraracter;

        private ClearAllCharactersAsyncTask(DAOChraracter daoChraracter) {
            this.daoChraracter = daoChraracter;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoChraracter.deleteAll();

            return null;
        }
    }
    List<Character> getAllChars() {
        ExecutorService myExec = Executors.newSingleThreadExecutor();

        Callable<List<Character>> callable = () -> {
            return daoChraracter.getAll();
        };

        Future<List<Character>> future = myExec.submit(callable);
        List<Character> tmp = null;
        try {
            tmp = future.get();
        } catch (ExecutionException | InterruptedException e) {}
        return tmp;
    }

    Character getCharacterAsyncTask(int id) {
        ExecutorService myExec = Executors.newSingleThreadExecutor();

        Callable<Character> callable = () -> {
            return daoChraracter.getCharacter(id);
        };

        Future<Character> future = myExec.submit(callable);
        Character tmp = null;
        try {
            tmp = future.get();
        } catch (ExecutionException | InterruptedException e) {}
        return tmp;
    }
}
