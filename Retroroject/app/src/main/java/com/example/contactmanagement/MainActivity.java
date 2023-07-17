package com.example.contactmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ContactApi contactApi;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        txtContacts=findViewById(R.id.txt1);
        ;
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit=RetrofitClient.getClient("http://10.0.2.2:8081/");
        contactApi=retrofit.create(ContactApi.class);
        Toast.makeText(MainActivity.this, "first of entries here", Toast.LENGTH_SHORT).show();

        // get the users
        Call<List<Contact>> call=contactApi.getAllContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                Toast.makeText(MainActivity.this, "before of entries here", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()) {
                    List<Contact> contacts = response.body();
                    Toast.makeText(MainActivity.this, contacts.size()+" of entries here", Toast.LENGTH_SHORT).show();
                    Log.d("MainActivity", "Contact count: " + contacts.size());

//                    for(int i=0;i<contacts.size();i++){
//                        txtContacts.append("Id: " +contacts.get(i).getContactId()+"\n" +
//                                        "First name: " +contacts.get(i).getFirstName()+"\n" +
//                                        "last name: " +contacts.get(i).getLastName()+"\n" +
//                                        "phone number: " +contacts.get(i).getPhoneNo()+"\n" +
//                                        "image url: " +contacts.get(i).getImageUrl()+"\n\n"
//                                );
//                    }
                    contactAdapter=new ContactAdapter(contacts);
                    recyclerView.setAdapter(contactAdapter);

                }else{
                    Log.e("MainActivity", "API call failed with response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.e("MainActivity", "API call failed: " + t.getMessage());
                call.cancel();
            }
        });


    }
    private void getContacts(){

    }
}