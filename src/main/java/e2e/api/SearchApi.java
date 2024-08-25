package e2e.api;

import io.restassured.response.Response;

import java.util.Map;

public class SearchApi extends baseApi {
    public static final String XREF_SEARCH_PATH = "/path";
    public static final String XREF_SEARCH_Movies = "/search/movie";

    public static Response getEntityById (String id){

        return get(XREF_SEARCH_PATH + id);
    }

    public static Response getSearchMovie (Map<String, String> queryParams){
        return get(XREF_SEARCH_Movies,queryParams);
    }
}
