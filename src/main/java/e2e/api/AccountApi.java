package e2e.api;

import e2e.utils.ConfigLoader;
import io.restassured.response.Response;

public class AccountApi extends baseApi {

    public static final String XREF_RATED_MOVIES = "/movie/top_rated";
    public static final String XREF_ADD_FAVORITE = "/account/" + ConfigLoader.getInstance().getAccountId() + "/favorite";

    public static Response getRatedMovies() {
        return get(XREF_RATED_MOVIES);
    }

    public static Response postAddFavotireMovie(Object payLoad) {
        return post(XREF_ADD_FAVORITE,payLoad );
    }


}
