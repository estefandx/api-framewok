package e2e.api;

import e2e.api.enums.Route;
import io.restassured.response.Response;

import java.util.Map;

public class ListApi extends  baseApi {

    public static final String XREF_CREATE_LIST = "/list";
    public static final String XREF_MOVIE_EXIST_LIST = "/list/{list_id}/item_status";
    private static final String XREF_ADD_ITEM_TO_LIST = "/list/{list_id}/add_item";


    public static Response postCreateList(Object payLoad) {
        return post(XREF_CREATE_LIST,payLoad );
    }

    public static Response getMoviExistList (String idList, Map<String, String> queryParams){
        String path = String.format(Route.XREF_MOVIE_EXIST_LIST, idList);
        return get(path, queryParams);
    }

    public static Response addItemToList(String listId, Object payLoad) {
        String path = String.format(Route.XREF_ADD_ITEM_TO_LIST, listId);
        return post(path, payLoad);
    }

}
