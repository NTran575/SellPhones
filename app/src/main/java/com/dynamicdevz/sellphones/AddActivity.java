package com.dynamicdevz.sellphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dynamicdevz.sellphones.R;
import com.dynamicdevz.sellphones.databinding.ActivityAddBinding;
import com.dynamicdevz.sellphones.model.data.Phone;
import com.dynamicdevz.sellphones.model.data.Phone.Manufacturer;
import com.dynamicdevz.sellphones.model.db.PhoneDBHelp;
import com.dynamicdevz.sellphones.presenter.PhonePresenter;
import com.dynamicdevz.sellphones.presenter.PresenterContract;
import com.dynamicdevz.sellphones.adapter.PhoneAdapter;

import java.util.List;

public class AddActivity extends AppCompatActivity implements PhoneAdapter.PhoneDelegate, PresenterContract.PhoneView{


    private ActivityAddBinding binding;
    private Manufacturer setManufacturer = Manufacturer.SAMSUNG;
    private PhoneAdapter phoneAdapter = new PhoneAdapter(this);
    private PresenterContract.Presenter phonePresenter;


    private String[] options = {
            Manufacturer.SAMSUNG.name(),
            Manufacturer.LG.name(),
            Manufacturer.APPLE.name(),
            Manufacturer.VIVO.name(),
            Manufacturer.TCL_ALCATEL.name(),
            Manufacturer.MOTOROLA.name()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.phoneListview.setAdapter(phoneAdapter);
        phonePresenter = new PhonePresenter(this);
        phonePresenter.getPhones();

        //select manufacturer
        binding.brandSpinner.setAdapter( new ArrayAdapter<String>(this, R.layout.spinner_item,R.id.manufacturer_name, options ));
        binding.brandSpinner.setSelection(0);
        binding.brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setManufacturer = Manufacturer.valueOf(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//  Do  nothing
            }
        });

        binding.insertPhoneButton.setOnClickListener(view -> {
            //Model name
            String modelName = binding.titleEdittext.getText().toString().trim();
            //Year
            int Yearinput = Integer.parseInt(binding.yearEdittext.getText().toString());
            //price
            double priceinput = Double.parseDouble(binding.priceEdittext.getText().toString());


            Phone newPhone = new Phone(setManufacturer,modelName, priceinput,Yearinput);
            phonePresenter.insertNewPhone(newPhone);
        });

    }

    @Override
    public void selectPhone(Phone phone) {
        Toast.makeText(this, phone.getModel(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, PhoneDetailsActivity.class);
        intent.putExtra("PHONE_DATA", phone);
        startActivity(intent);
    }

    @Override
    public void displayPhones(List<Phone> phones) {
        phoneAdapter.setPhoneList(phones);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public Context getContext() {
        return this;
    }

}
