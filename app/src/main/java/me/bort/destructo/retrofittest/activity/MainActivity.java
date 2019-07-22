package me.bort.destructo.retrofittest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.bort.destructo.retrofittest.R;
import me.bort.destructo.retrofittest.model.AndroidVersion;
import me.bort.destructo.retrofittest.model.JSONResponse;
import me.bort.destructo.retrofittest.model.Photo1;
import me.bort.destructo.retrofittest.network.RequestInterface;
import me.bort.destructo.retrofittest.network.RequestInterface1;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<AndroidVersion> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //http logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();


        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();

                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                Log.d("berttest","berttest onResponse:" + data.get(1).getName());
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        RequestInterface1 request1 = retrofit1.create(RequestInterface1.class);
        Call<List<Photo1>> call1 = request1.getPhotos();


        call1.enqueue(new Callback<List<Photo1>>() {
            @Override
            public void onResponse(Call<List<Photo1>> call, Response<List<Photo1>> response) {

                List<Photo1> photos = response.body();

                //data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                //Log.d("berttest","berttest onResponse1:" + data.get(1).getName());
                for(int i=0; i<photos.size(); i++){
                    Log.d("berttest","berttest photo id:" + photos.get(i).getId());
                    Log.d("berttest", "berttest photo blah:" +photos.get(i).getBlah());
                }
            }

            @Override
            public void onFailure(Call<List<Photo1>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });



    }
}
