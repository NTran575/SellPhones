package com.dynamicdevz.sellphones.presenter;

import android.content.Context;

import com.dynamicdevz.sellphones.model.data.Phone;
import com.dynamicdevz.sellphones.model.db.PhoneDBHelp;

public class PhonePresenter implements PresenterContract.Presenter {

    private Context context;

    private PresenterContract.PhoneView view;
    private PhoneDBHelp dbHelper;

    public PhonePresenter(PresenterContract.PhoneView view) {
        this.view = view;
        dbHelper = new PhoneDBHelp(view.getContext());
    }

    @Override
    public void getPhones() {

        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    view.displayPhones(dbHelper.getAllPhones());
                } catch(Exception e){
                    view.displayError(e.getMessage());
                }
            }
        }.start();

    }

    @Override
    public void insertNewPhone(Phone phone) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    dbHelper.insertPhone(phone);
                    getPhones();
                } catch (Exception e) {
                    view.displayError(e.getMessage());
                }
            }
        }.start();
    }
}
