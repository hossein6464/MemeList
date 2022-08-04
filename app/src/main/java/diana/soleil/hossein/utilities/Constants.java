package diana.soleil.hossein.utilities;

public class Constants {
    // Api Link
    final public static String API_URL = "https://api.imgflip.com/get_memes";

    // DB Consts
    final public static String[] TABLE_COLUMNS     = {"id", "name", "url", "width", "height","box_count"};
    final public static String CREATE_TABLE        =
            "CREATE TABLE memes (id INTEGER , name TEXT, url TEXT, width INTEGER, height INTEGER, box_count INTEGER);";

    final public static String DATABASE_NAME       = "memes_database";
    final public static String SCHEMA_VERSION      = "1";
    final public static String TABLE_NAME          = "memes";

    // Model Design API
    final public static String DATA = "data";
    final public static String MEMES = "memes";
    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String URL = "url";
    final public static String WIDTH = "width";
    final public static String HEIGHT = "height";
    final public static String BOX_COUNT = "box_count";

    ///// Intent Constants
    public static final String BUNDLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY = "bundleToDetail";
    public static final String SERIALIZABLE_KEY_MAINACTIVITY_TO_DETAILACTIVTY = "serializableToDetail";

}
