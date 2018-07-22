package leanplumdemo.dpom.com.leanplumdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

import com.urbanairship.analytics.CustomEvent;

import java.util.Map;
import java.util.HashMap;
import com.leanplum.Leanplum;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * Firebase
         */
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "instant-pot");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Instant Pot 6 Qt 7-in-1 Multi Use");
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "Kitchen & Dining");
        bundle.putString(FirebaseAnalytics.Param.QUANTITY, "1");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, null);

        /**
         * Urban Airship
         */
        CustomEvent.Builder builder = new CustomEvent.Builder("ADD_TO_CART")
                .addProperty("ITEM_ID", "instant-pot")
                .addProperty("ITEM_NAME", "Instant Pot 6 Qt 7-in-1 Multi Use")
                .addProperty("ITEM_CATEGORY", "Kitchen & Dining")
                .addProperty("QUANTITY", 1);
        builder.build().track();

        CustomEvent.Builder builder2 = new CustomEvent.Builder("ECOMMERCE_PURCHASE");
        builder2.build().track();

        /**
         * LeanPlum
         */
        Map<String, Object> purchaseParams = new HashMap<String, Object>();
        purchaseParams.put("ITEM_ID", "instant-pot");
        purchaseParams.put("ITEM_NAME", "Instant Pot 6 Qt 7-in-1 Multi Use");
        purchaseParams.put("ITEM_CATEGORY", "Kitchen & Dining");
        purchaseParams.put("QUANTITY", 1);

        Leanplum.track("ADD_TO_CART", 49.99, purchaseParams);
        Leanplum.trackPurchase(Leanplum.PURCHASE_EVENT_NAME, 49.99, "USD", purchaseParams);

        Log.d("MainActivity","MainActivity.onCreate done.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
