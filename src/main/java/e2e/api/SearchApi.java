package e2e.api;

import io.restassured.response.Response;

public class SearchApi extends baseApi {
    public static final String XREF_SEARCH_PATH = "/path";

    public static Response getEntityById (String id){

        return get(XREF_SEARCH_PATH + id);
    }
}
