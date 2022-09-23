package data;

public class Credentials {
    public static final String USER_USERNAME = System.getenv("MOVIEDB_USERNAME");
    public static final String USER_PASSWORD = System.getenv("MOVIEDB_PASSWORD");
    public static final String USER_API_KEY = System.getenv("MOVIEDB_API_KEY");
    public static String USER_REQUEST_TOKEN;
    public static String SESSION_ID;
}
