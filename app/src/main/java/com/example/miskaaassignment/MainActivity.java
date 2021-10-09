package com.example.miskaaassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.example.miskaaassignment.model.Crew;
import com.example.miskaaassignment.model.Database;
import com.example.miskaaassignment.utils.CrewAdapter;
import com.example.miskaaassignment.utils.serviceApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String URL = "https://api.spacexdata.com/v4/";
    RecyclerView recyclerView;
    Button refresh,delete;

    boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        refresh = findViewById(R.id.refresh_button);
        delete = findViewById(R.id.delete_data_button);

        if(Database.getDatabase(getApplicationContext()).getDao().dataExist()){
            recyclerView.setAdapter(new CrewAdapter(getApplicationContext(),Database.getDatabase(getApplicationContext()).getDao().getAllData()));
        }else{
            isConnected = checkConnection();
            if(isConnected){
                getData();
            }else{
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }

        refresh.setOnClickListener(view -> {
            if (!Database.getDatabase(getApplicationContext()).getDao().dataExist()) {
                getData();
            }
            recreate();
        });

        delete.setOnClickListener(view -> {
            Database.getDatabase(getApplicationContext()).getDao().deleteAllData();
            recreate();
        });

    }

    private boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


    private void getData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceApi api = retrofit.create(serviceApi.class);
        Call<List<Crew>> call = api.getInformation();
        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                List<Crew> data = response.body();
                Log.d("TAGGED",String.valueOf(data.size()));
                for(int i = 0; i < data.size(); i++){
                    Database.getDatabase(getApplicationContext()).getDao().insertAll(data.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {

            }
        });

    }
}