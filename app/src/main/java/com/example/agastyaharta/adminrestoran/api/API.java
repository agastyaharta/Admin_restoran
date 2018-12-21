package com.example.agastyaharta.adminrestoran.api;

import android.telephony.mbms.StreamingServiceInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {
    @GET("hidangan")
    Call<Value> hidangan();


    @FormUrlEncoded
    @POST("hidangan/menubaru")
    Call<Value> menubaru(@Field("nama_hidangan") String nama_hidangan,
                         @Field("deskripsi_hidangan") String deskripsi_hidangan,
                         @Field("harga_hidangan") String harga_hidangan,
                         @Field("kategori_hidangan") String kategori_hidangan);

//    @GET("admin")
//    Call<Value> admin();

    @FormUrlEncoded
    @POST("admin/login")
    Call<Value> adminlogin(@Field("username_pegawai") String username_pegawai,
                       @Field("password_pegawai") String password_pegawai);



}
