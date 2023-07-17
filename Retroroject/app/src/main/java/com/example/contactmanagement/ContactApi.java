package com.example.contactmanagement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactApi {
    @GET("contacts")
    Call<List<Contact>> getAllContacts();

    @POST("contacts")
    Call<Contact> createContact(@Body Contact contact);

    @PUT("contacts/{id}")
    Call<Contact> updateContact(@Path("id") int contactId,@Body Contact contact);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") int contactId);
}
