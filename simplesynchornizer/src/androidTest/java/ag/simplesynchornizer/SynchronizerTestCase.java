package ag.simplesynchornizer;


import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.json.JSONException;
import org.json.JSONObject;

public class SynchronizerTestCase extends AndroidTestCase{

    public void testCall() throws JSONException, MapperException,
            SynchronizerException, DatastoreServiceException {
        //action
        String action = "search_books";
        //setup
        DatastoreHelper helper = new DatastoreHelper(getContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        DatastoreService service = new DatastoreService(database);
        Mapper mapper = new Mapper(database);
        Synchronizer synchronizer = new Synchronizer(service, mapper);
        //store a rest-server place for test
        mapper.put(action, new Mapper.Value(
                "GET", "https://www.googleapis.com/books/v1/volumes?q=android"
        ));
        Mapper.Value value = mapper.get(action);
        assertNotNull(value);
        //sync
        synchronizer.call(action);
        //select action
        JSONObject result = service.select(action);
        assertNotNull(result);
        assertEquals("books#volumes", result.getString("kind"));
        //
        mapper.clean();
        service.delete(action);
    }

}
