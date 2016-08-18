package hu.gergelyszaz.blackswanandroidtestapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.gergelyszaz.blackswanandroidtestapp.ItemList;
import hu.gergelyszaz.blackswanandroidtestapp.Movie;
import hu.gergelyszaz.blackswanandroidtestapp.TheMovieDB;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent implements ItemList{
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position, String movie) {
        return new DummyItem(String.valueOf(position), movie+ " Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static List<DummyItem> getItems() {
        TheMovieDB themoviedb=new TheMovieDB();
        List<String> movies= themoviedb.getTop20Movies();
        int position=1;
        for(String movie: movies){
            position++;
            DummyItem item=createDummyItem(position,movie);
            ITEMS.add(item);
        }

        return ITEMS;
    }

    @Override
    public void UpdateUI(List<Movie> movies) {

    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
