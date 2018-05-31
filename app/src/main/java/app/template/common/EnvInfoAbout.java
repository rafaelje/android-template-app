package app.template.common;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

public class EnvInfoAbout {
    private Properties properties = new Properties();

    private Boolean isLoaded = false;

    public EnvInfoAbout(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("about.properties");
            properties.load(inputStream);
        } catch (Exception ex) {
            LogHelper.e(ex.getMessage());
            isLoaded = false;
        }
        isLoaded = true;
    }

    public Boolean getIsLoaded() {
        return isLoaded;
    }

    public String getVersion() {
        return properties.getProperty("about.version");
    }

    public String getBuild() {
        return properties.getProperty("about.build");
    }

    public String getDate() {
        return properties.getProperty("about.date");
    }

    public String getCommit() {
        return properties.getProperty("about.commit");
    }

    public String getCommitFull() {
        return properties.getProperty("about.commitFull");
    }

    public String getGithub() {
        return properties.getProperty("about.github");
    }

}
