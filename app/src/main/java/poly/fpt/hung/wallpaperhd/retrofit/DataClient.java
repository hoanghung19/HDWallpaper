package poly.fpt.hung.wallpaperhd.retrofit;

import java.util.List;

import poly.fpt.hung.wallpaperhd.ABC;
import poly.fpt.hung.wallpaperhd.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataClient {

      //Code GET ALL DATA
    @GET("/wp-json/wp/v2/posts?_embed")
    Call<List<ABC>> getDataALL();

//    @FormUrlEncoded
//    @GET("wp-json/wp/v2/media/{id}")
//        Call<List<CategoryModel>> getDataCategory(@Path("id")int id);


    @GET("/wp-json/wp/v2/posts")
    Call<List<Post>> getCategoryPosition(@Query("category") String category);
      //Code GET CATEGORY POSITION


//    @GET("/wp-json/wp/v2/posts")
//    Call<List<PostModel>> getCategoryPosition(@Query("category") String category);
    @GET("/wp-json/wp/v2/posts?_embed")
    Call<List<Post>> getData();


}
