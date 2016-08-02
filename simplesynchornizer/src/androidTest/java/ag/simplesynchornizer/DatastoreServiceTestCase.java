package ag.simplesynchornizer;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;


public class DatastoreServiceTestCase extends AndroidTestCase {

    private JSONObject insert(DatastoreService service) throws JSONException, DatastoreServiceException {
        JSONObject in = new JSONObject();
        in.put("hello", "Hi! I'am only testing");
        service.insert("uol_hello", in.toString());
        return in;
    }

    private JSONObject select(DatastoreService service) throws DatastoreServiceException{
        return service.select("uol_hello");
    }

    private void delete(DatastoreService service) throws DatastoreServiceException {
        service.delete("uol_hello");
    }

    public void testInsertSelectDelete() throws JSONException, DatastoreServiceException {
        DatastoreHelper helper = new DatastoreHelper(getContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        DatastoreService service = new DatastoreService(database);
        database.beginTransaction();
        //test insert and select
        JSONObject in = insert(service);
        JSONObject data = select(service);
        assertNotNull(data);
        assertEquals(in.toString(), data.toString());
        //test delete
        delete(service);
        assertNull(select(service));
        //close
        helper.close();
        //
        database.close();
    }
}

