package com.example.thanh_it.qlsv.login_sign_forgot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanh_it.qlsv.DBQLDATA.Account;
import com.example.thanh_it.qlsv.DBQLDATA.DBQL;
import com.example.thanh_it.qlsv.R;

import java.util.ArrayList;
import java.util.List;

public class ForgotActivity extends AppCompatActivity {
    private List<Account> mAccount = new ArrayList<>();

    private EditText phone;
    private Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        phone = findViewById(R.id.phonenumber);
        search =findViewById(R.id.forgotnow);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchphone();
            }
        });
    }

    private void searchphone() {
        DBQL sqLiteDatabase = new DBQL(ForgotActivity.this);
        SQLiteDatabase liteDatabase = sqLiteDatabase.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("Select * from ACCOUNT",null);
        Account account = new Account();
        if(cursor == null)
        {
            return;
        }
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                account.setUser(cursor.getString(1));
                account.setPass(cursor.getString(2));
                account.setNumber(cursor.getInt(3));
                mAccount.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        liteDatabase.close();
        sqLiteDatabase.close();
    }
}
