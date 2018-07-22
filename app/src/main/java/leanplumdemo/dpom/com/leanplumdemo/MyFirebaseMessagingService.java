package leanplumdemo.dpom.com.leanplumdemo;

import com.google.firebase.messaging.RemoteMessage;

import com.leanplum.internal.Log;
import com.leanplum.LeanplumPushFirebaseMessagingService;
import com.urbanairship.push.fcm.AirshipFirebaseMessagingService;

public class MyFirebaseMessagingService extends LeanplumPushFirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        /**
         * LeanPlum
         */
        super.onMessageReceived(remoteMessage);

        /**
         * Urban Airship
         */
        AirshipFirebaseMessagingService.processMessageSync(getApplicationContext(), remoteMessage);

        Log.i("### ", "Firebase message received");
    }
}
