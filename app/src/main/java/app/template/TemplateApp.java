package app.template;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import app.template.common.UtilsCrash;
import app.template.s42.templateandroid.BuildConfig;

/**
 * All the application configuration
 */
public class TemplateApp extends Application {

    private static TemplateApp instance;
    private static Boolean isDebuggable;

    /**
     * Called when the application is starting before any activity, service or receiver objects have been created
     */
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        UtilsCrash.configCrash(this, true);

        try {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .tag(getPackageName())   // (Optional) Global tag for every log.
                    .build();
            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        } catch (Exception ex) {
            ex.getStackTrace();
        }

        isDebuggable =  ( 0 != ( getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) );

    }

    /**
     * Get application instance
     * @return MDMAgent object
     */
    public static TemplateApp getInstance(){
        return instance;
    }

    /**
     * Get if the app is debuggable or not
     * @return Boolean
     */
    public static Boolean getIsDebuggable() {
        return isDebuggable;
    }

    public static Boolean isSecureVersion() { return true; }

    public static String getCompleteVersion() {
        return BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE;
    }
}
