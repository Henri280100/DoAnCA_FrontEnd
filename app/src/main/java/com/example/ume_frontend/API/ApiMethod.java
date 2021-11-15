package com.example.ume_frontend.API;

import com.example.ume_frontend.Model.ListMessages;
import com.example.ume_frontend.Model.ListUser;
import com.example.ume_frontend.Model.UserModel;
import com.example.ume_frontend.Model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiMethod {
    @GET("api/Login/getuser")
    Call<UserModel> getUser(@Query("phoneNumber") String phoneNumber, @Query("password") String password);

    @POST("api/Login/register")
    Call<UserModel> createUser(@Body UserModel.Account userAccount);

    @GET("/api/Main")
    Call<ListUser> getListfriends (@Query("idUser") int idUser);
    @GET("/api/Main")
    Call<ListUser> getListChatfriends (@Query("idf") int idf);

    @PUT("api/Login/updateAvertar")
    Call<UserProfile> userProfile(@Query("idUser") int idUSer, @Query("urlAvarta") String urlAvarta);

    @GET("api/Main/find")
    Call<UserModel> searchingUser(@Query("phoneNumber") String phoneNumber);

    @GET("api/Main/updateAvertar/")
    Call<String> sendCodeToEmail(@Query("email") String email);

    @POST("api/Login/forgetpassword")
    Call<String> createNewPassword(@Query("email") String Email);

    @GET("/api/Chats")
    Call<ListMessages> getListchat (@Query("id") int id, @Query("idf") int idf);

    @POST("api/Chats/sendsms")
    Call<String> sendmess(@Body ListMessages.Data message);
}
