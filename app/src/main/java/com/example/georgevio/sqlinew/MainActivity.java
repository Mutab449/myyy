package com.example.georgevio.sqlinew;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//George
    DBHelper mydb;
int s;
    Button bttnshow1;
    Button bttnshowall;
    Button bttnadd;
    Button btnDeleteAll;
    Button btnDelete;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;
    EditText editTextPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        editTextName = (EditText)findViewById(R.id.editName);
        editTextPhone = (EditText)findViewById(R.id.editPhone);
        editTextEmail = (EditText)findViewById(R.id.editEmail);
        editTextStreet = (EditText)findViewById(R.id.editStreet);
        editTextPlace = (EditText)findViewById(R.id.editPlace);

        bttnadd = (Button) findViewById(R.id.bttnAdd);
        bttnshow1 = (Button) findViewById(R.id.bttnShow1);
        bttnshowall = (Button) findViewById(R.id.bttnShowAll);
        btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        bttnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();
                String getStreet = editTextStreet.getText().toString();
                String getPlace = editTextPlace.getText().toString();
                if(getName.isEmpty() || getPhone.isEmpty() || getEmail.isEmpty() || getStreet.isEmpty() || getPlace.isEmpty()){
                    Toast.makeText(getApplicationContext(),"you must insert data on all fields", Toast.LENGTH_SHORT).show();
                }

                else
                if (mydb.insertContact(getName, getPhone, getEmail, getStreet, getPlace)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + getName + ", " + getPhone + "," + getEmail, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });

        bttnshow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                String nn =editTextName.getText().toString();
                Cursor getID=mydb.getID(nn);
                if (getID.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    int did        = getID.getInt(getID.getColumnIndex("id"));
                    String dName   = getID.getString(getID.getColumnIndex("name"));
                    String dPhone  = getID.getString(getID.getColumnIndex("phone"));
                    String dEmail  = getID.getString(getID.getColumnIndex("email"));
                    String dStreet = getID.getString(getID.getColumnIndex("street"));
                    String dPlace  = getID.getString(getID.getColumnIndex("place"));
                    Toast.makeText(getApplicationContext(),
                            "rec: "+ did+" ," + dName + ", " + dPhone + ", " + dEmail + ", " + dStreet + ", " + dPlace, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getID.close();
            }
        });

        bttnshowall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on Result Button");
                ArrayList<String> fetchAll = new ArrayList<String>();
                fetchAll=mydb.getAllContacts();
                for (String a:fetchAll)
                    Log.v("georgeLog:", a.toString());
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    Log.v("georgeLog:", "intent executed");
                    intent.putStringArrayListExtra("fetchAll",fetchAll);
                    Log.v("georgeLog:","fetchALL executed");
                    startActivity(intent);
                    Log.v("georgeLog:", "startActivity executed");
            }
        });
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Delete All Contacts","Done");
                mydb.deleteAllContact();
                Toast.makeText(getApplicationContext(),
                        "ALL contacts are Deleted", Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String getName = editTextName.getText().toString();
                if(getName.isEmpty()){
                    Toast.makeText(getApplicationContext(),"you must insert data on field Name", Toast.LENGTH_SHORT).show();
                }
                else{
                Log.v("georgeLog", "clicked on fetch");
                String nn =editTextName.getText().toString();
                mydb.deleteContactByName(nn);
                    Toast.makeText(getApplicationContext(),
                            "rec: " + nn+" Deleted", Toast.LENGTH_LONG).show();}
            }
        });


        // to delete a record
        //find the record you want, get its id, and use the following
        //mydb.deleteContact (id);

    }
}
