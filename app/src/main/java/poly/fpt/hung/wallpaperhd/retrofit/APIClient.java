package poly.fpt.hung.wallpaperhd.retrofit;

public class APIClient {
    public static  final String URL="http://asian.dotplays.com";
    public static DataClient getData(){
        return RetrofitClient.getClient(URL).create(DataClient.class);
    }
}
