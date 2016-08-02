package ag.simplesynchornizer;

import android.util.Log;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static org.junit.Assert.*;


public class PremisseTest {
    @Test
    public void premisseResultStartEnds() throws Exception {
        Mapper.Value value =new Mapper.Value(
                "GET", "https://www.googleapis.com/books/v1/volumes?q=android"
        );
        //
        URL url = new URL(value.getPath());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(false);
        connection.setRequestMethod(value.getVerb());
        connection.setRequestProperty("Content-type", "application/json");
        InputStream inputStream = connection.getInputStream();
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        String line = "";
        StringBuffer sb = new StringBuffer();
        while ((line = reader.readLine()) != null){
            sb.append(line);
        }
        //Log.d("AGDebug", "received: " + sb.toString());
        streamReader.close();
        connection.disconnect();
        String data = sb.toString();
        //inserir resultado em database
        assertTrue(!"".equals(data));
        assertTrue(data.startsWith("{"));
        assertTrue(data.endsWith("}"));
    }
}