package poly.fpt.hung.wallpaperhd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import poly.fpt.hung.wallpaperhd.adapter.CategoryAdapter;
import poly.fpt.hung.wallpaperhd.model.Post;
import poly.fpt.hung.wallpaperhd.retrofit.APIClient;
import poly.fpt.hung.wallpaperhd.retrofit.DataClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category extends FragmentData {
    private DataClient dataClient;
    private RecyclerView rc;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Post> postList;
    private int i1 = 0;
    private List<Integer> id = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category, container, false);
        dataClient = APIClient.getData();
        rc=view.findViewById(R.id.rc);

//        loadCategori();

        dataClient.getData().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                else {
                    postList = response.body();

                    for (Post post:postList) {
                        for (int i : post.getCategories()) {
                            id.add(i);
                        }
                    }
                    linearLayoutManager=new LinearLayoutManager(getContext());
                    categoryAdapter=new CategoryAdapter(getContext(), postList, id);
                    rc.setLayoutManager(linearLayoutManager);
                    rc.setHasFixedSize(true);
                    rc.setAdapter(categoryAdapter);
                    view.findViewById(R.id.loading).setVisibility(View.GONE);
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        return view;

    }

    private void loadCategori() {
        dataClient.getDataALL().enqueue(new Callback<List<ABC>>() {
            @Override
            public void onResponse(Call<List<ABC>> call, Response<List<ABC>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
//                for (ABC abc : response.body()) {
//
//                    for (WpFeaturedmedium_ wpFeaturedmedium_ : abc.getEmbedded().getWpFeaturedmedia()) {
//                        Log.e("TAG", wpFeaturedmedium_.getSourceUrl());
//                        img.add(wpFeaturedmedium_.getSourceUrl());
//                    }
//
//                    title.add(abc.getTitle().getRendered());
//
//                }
//                for (int i=0;i<title.size();i++){
//                    position.add(getposition(i,response.body()));
//                }
                else {

                }
                view.findViewById(R.id.loading).setVisibility(View.GONE);
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ABC>> call, Throwable t) {
                loadCategori();
            }
        });
    }

    public int getposition(final int i,List<ABC> abcs){
        i1=0;
        String[] words = abcs.get(i).getContent().getRendered().split("\\s");
        for (String w : words) {
            if (w.startsWith("src=")) {
                i1++;
                Log.e("III",i1+"");
            }
        }




        return i1;
    }
}
