package e2e.api;

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
        String path = XREF_MOVIE_EXIST_LIST.replace("{list_id}", idList);
        return get(path, queryParams);
    }

    public static Response addItemToList(String listId, Object payLoad) {

        String path = XREF_ADD_ITEM_TO_LIST.replace("{list_id}", listId);
        return post(path, payLoad);
    }

}
