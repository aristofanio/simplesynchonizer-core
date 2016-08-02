package ag.cordova.plugin.simplesynchronizer.dev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import ag.simplesynchornizer.SimpleSynchronizer;
import ag.simplesynchornizer.SimpleSynchronizerException;
import ag.simplesynchornizer.SimpleSynchronizerImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ag.cordova.plugin.simplesynchronizer.dev.R.layout.activity_main);
        //
        final TextView tv = (TextView) findViewById(R.id.tv);
        final Button btn = (Button) findViewById(R.id.btn);
        final Button btm = (Button) findViewById(R.id.btm);
        final SimpleSynchronizer simpleSynchronizer = new SimpleSynchronizerImpl();
        //
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    simpleSynchronizer.register(MainActivity.this,
                            "load-google-books", "GET",
                            "https://www.googleapis.com/books/v1/volumes?q=android"
                    );
                }
                catch (SimpleSynchronizerException e) {
                    Log.v("AGDebug", "Fail in sample on btnclick.", e);
                }
            }
        });
        //
        btm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        try {
                            final JSONObject json = simpleSynchronizer.request(
                                    MainActivity.this, "load-google-books"
                            );
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.setText(json.toString());
                                }
                            });
                        }
                        catch (SimpleSynchronizerException e) {
                            Log.v("AGDebug", "Fail in sample on btnclick.", e);
                        }
                    }
                };
                t.start();
            }
        });

    }
}

