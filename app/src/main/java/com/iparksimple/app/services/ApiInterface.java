package com.iparksimple.app.services;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("auth/register")
    Call<Result_signup> SignUp(@Field("email") String email,@Field("password")String password,@Field("phone")String Phone);

    @FormUrlEncoded
    @POST("auth/login")
    Call<Result_Login> Login(@Field("username") String username, @Field("password")String password);


    @FormUrlEncoded
    @POST("auth/forgot")
    Call<Result_forgot>ForgotPassword(@Field("username")String Username);

    @FormUrlEncoded
    @POST("auth/reset")
    Call<Result_reset>ResetPassword(HashMap<String,String>map);

    @GET("customer/profile")
    Call<Result_profile>Getprofile(@Header("authorization")String authorization);

    @FormUrlEncoded
    @POST("customer/update")
    Call<Result_UpdateProfile>Update_profile(@Header("authorization")String token,@FieldMap HashMap<String,String>map);

    @GET("auth/logout")
    Call<Result_logout>Logout();

    @GET("parking/map_search")
    Call<Result_map>GetHomeData(@Header("latitude") String Latitude,@Header("longitude")String Longitude,@Header("type")String Type,@Header("Distance")String Distance);

    @GET("lots?id=19")
    Call<Result_LotsDetail>Lots_details(@Field("id")String id);


    @FormUrlEncoded
    @POST("vehicle/add_vehicle")
    Call<Result_addvehicle>Add_vehicle(@Header("Authorization")String token,@FieldMap HashMap<String,String>map);


    @GET("vehicle/vehicle_list")
    Call<Result_vehicleList>VehicleList(@Header("authorization")String Token);








}
