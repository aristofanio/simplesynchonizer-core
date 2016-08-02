package ag.simplesynchornizer;


import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;


public class DatastoreHelperTestCase extends AndroidTestCase{

    public void testSQLite(){
        DatastoreHelper helper = new DatastoreHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        assertNotNull(db);
        db.close();
    }

}
