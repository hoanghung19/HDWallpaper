package poly.fpt.hung.wallpaperhd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import poly.fpt.hung.wallpaperhd.R;
import poly.fpt.hung.wallpaperhd.model.Post;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHodel> {
    private Context context;
    private List<Post> postList;
    private List<Integer> id;

    public CategoryAdapter(Context context, List<Post> postList, List<Integer> id) {
        this.context = context;
        this.id = id;
        this.postList = postList;
    }

    @NonNull
    @Override
    public CategoryHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryHodel(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHodel holder, int position) {

//        holder.textView.setText(title.get(position)+"("+positiona.get(position)+")");
//        Picasso.get().load(urlimg.get(position)).fit().transform(boderIMG(0, 5))
//                .centerCrop().error(R.drawable.ic_launcher_background).into(holder.imageView, new Callback() {
//            @Override
//            public void onSuccess() {
//                //code loading
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });

        Post post = postList.get(position);

        List<String> stringList = new ArrayList<>();
        //title
        String title = post.getTitle().getRendered();

        //số lượng bài post

        //ảnh bài post

        String rendered = post.getContent().getRendered();

        String[] words = rendered.split("\\s");

        for (String w : words) {

            if (w.startsWith("http:") && w.endsWith("jpg")) {
                stringList.add(w);
            }
        }

        holder.textView.setText(title + " (" + stringList.size() + ")");
        if(stringList.size()>0){
            Picasso.get().load(String.valueOf(stringList.get(0))).fit().transform(boderIMG(0, 5))
                    .centerCrop().error(R.drawable.ic_launcher_background).into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {
                    //code loading

                }

                @Override
                public void onError(Exception e) {

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class CategoryHodel extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public CategoryHodel(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_category);
            textView=itemView.findViewById(R.id.txt_category);
        }
    }

    private Transformation boderIMG(int boderW, int boderConer) {
        return new RoundedTransformationBuilder()
                .borderColor(Color.BLACK)
                .borderWidthDp(boderW)
                .cornerRadiusDp(boderConer)
                .oval(false)
                .build();

    }
}
