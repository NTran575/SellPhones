package com.dynamicdevz.sellphones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.dynamicdevz.sellphones.R;
import com.dynamicdevz.sellphones.adapter.PhoneAdapter;
import com.dynamicdevz.sellphones.databinding.ActivityPhoneDetailsBinding;
import com.dynamicdevz.sellphones.model.data.Phone;
import com.dynamicdevz.sellphones.model.db.PhoneDBHelp;

import java.util.List;

public class PhoneDetailsActivity extends AppCompatActivity {

    private ActivityPhoneDetailsBinding binding;
    private Phone phone;
    private PhoneDBHelp db;
    private PhoneAdapter Padapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneDetailsBinding.inflate(
                getLayoutInflater()
        );
        setContentView(binding.getRoot());

        phone = getIntent().getParcelableExtra("PHONE_DATA");
        if(phone != null){
            binding.modelTextview.setText(phone.getModel());
            binding.ryearTextview.setText("Release in " + phone.getYear());
            binding.priceView.setText("Price: $"+phone.getPrice());



        }
        //summon db
        db=new PhoneDBHelp(this);


        binding.deletebutton.setOnClickListener(view ->{
            db.deletePhone(phone);
            readAllPhones();
            Intent intent=new Intent(this, SecondPageActivity.class);
            startActivity(intent);

        });
    }

    private void readAllPhones() {
        List<Phone> phonelist=db.getAllPhones();
        Padapter.setPhoneList(phonelist);
    }
}
