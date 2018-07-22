package leanplumdemo.dpom.com.leanplumdemo;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;

// For tracking user sessions.
import com.leanplum.LeanplumActivityHelper;
// For push notifications.
//import com.leanplum.LeanplumPushService;

import com.leanplum.annotations.Parser;
//import com.leanplum.annotations.Variable;
//import com.leanplum.callbacks.StartCallback;
//import com.leanplum.callbacks.VariablesChangedCallback;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.urbanairship.UAirship;

import java.util.Map;
import java.util.HashMap;

public class LeanPlumDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String age = "old_enough";
        String gender = "male";
        String email = "dfpomeroy@gmail.com";

        /**
         * LeanPlum
         */
        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        //  For session lifecyle tracking.
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        Leanplum.setAppIdForDevelopmentMode("app_pCn3Pxqu1sJRRRDkCHr5VsuRZAikCPTwp2SSgR4b8iY",
                "dev_tmHYxO1c3oodo3aPouZB93M4q8Ea7Malu76G5xc2ivc");

        // Optional: Tracks all screens in your app as states in Leanplum.
        Leanplum.trackAllAppScreens();

        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("age", age);
        attributes.put("gender", gender);
        attributes.put("email", email);
        // This will only run once per session, even if the activity is restarted.
        Leanplum.start(this, attributes);

        /**
         * Google Firebase
         */
        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mFirebaseAnalytics.setUserProperty("lpAge", age);
        mFirebaseAnalytics.setUserProperty("lpGender", gender);
        mFirebaseAnalytics.setUserProperty("email", email);

        /**
         * Urban Airship
         */
        UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
        UAirship.shared().getPushManager().setPushEnabled(true);

        UAirship.shared().getAnalytics()
                .editAssociatedIdentifiers()
                .addIdentifier("age", age)
                .addIdentifier("gender", gender)
                .addIdentifier("email", email)
                .apply();

        Log.d("LeanPlumDemoApplication","LeanPlumDemoApplication.onCreate done.");
    }
}
