package app.template.common;

import android.content.Context;

import com.bugsnag.android.Bugsnag;

public class UtilsCrash {

    public static void configCrash(Context context, boolean val) {
        if(val) {
            Bugsnag.init(context);
            // TODO add the end point for Bugsnag service
            Bugsnag.setEndpoint("http://");
            Bugsnag.setSendThreads(val);
        } else {
            Bugsnag.init(context);
            Bugsnag.setEndpoint("");
            Bugsnag.setSendThreads(val);
        }

    }
}
