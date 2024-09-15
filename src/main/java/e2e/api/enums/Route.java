package e2e.api.enums;

public class Route {

    public static final String BASE_PATH = "/3";

    // Rutas de listas
    public static final String XREF_CREATE_LIST = BASE_PATH + "/list";
    public static final String XREF_MOVIE_EXIST_LIST = BASE_PATH + "/list/%s/item_status";
    public static final String XREF_ADD_ITEM_TO_LIST = BASE_PATH + "/list/%s/add_item";
}
