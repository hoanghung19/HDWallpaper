package poly.fpt.hung.wallpaperhd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import poly.fpt.hung.wallpaperhd.retrofit.APIClient;
import poly.fpt.hung.wallpaperhd.retrofit.DataClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private DataClient dataClient;
    private ArrayList<String> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterIMG adapterIMG;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataClient = APIClient.getData();
        recyclerView = findViewById(R.id.ry);
        adapterIMG = new AdapterIMG(list, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setAdapter(adapterIMG);
        dataClient.getDataALL().enqueue(new Callback<List<ABC>>() {
            @Override
            public void onResponse(Call<List<ABC>> call, Response<List<ABC>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "ERR_CODE:"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                for (ABC abc:response.body()){
                    String[] words = abc.getContent().getRendered().split("\\s");

                    for (String w : words) {
                        if (w.startsWith("http:") && w.endsWith("jpg")) {
                            Log.e("CONTENT", w);
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<List<ABC>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
