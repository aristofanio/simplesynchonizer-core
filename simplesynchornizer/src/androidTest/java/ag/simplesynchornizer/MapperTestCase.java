package ag.simplesynchornizer;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * Created by arigarcia on 8/1/16.
 */
public class MapperTestCase extends AndroidTestCase {

    public void test() throws MapperException {
        DatastoreHelper helper = new DatastoreHelper(getContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        database.beginTransaction();
        Mapper mapper = new Mapper(database);
        mapper.put("test", new Mapper.Value("GET", "http://teste.com"));
        System.out.println(database.isOpen());
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }
}
