package me.bort.destructo.retrofittest.network;

import java.util.List;

import me.bort.destructo.retrofittest.model.Photo1;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface1 {
    @GET("/photos")
    Call<List<Photo1>> getPhotos();
}
