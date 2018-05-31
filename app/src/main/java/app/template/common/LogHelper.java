package app.template.common;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import app.template.TemplateApp;

public class LogHelper {

    private static final String FILE_NAME_FEEDBACK = "LogFeedback.txt";
    private static final String APP_PATH = Environment.getExternalStorageDirectory().getPath() + "/app/";

    /**
     * private constructor to prevent instances of this class
     */
    private LogHelper() {
    }

    /**
     * Send a DEBUG log message
     * @param object Object to write
     */
    public static void d(Object object) {
        if(TemplateApp.getIsDebuggable()){
            LogHelper.d(object);
        }
    }

    /**
     * Send a DEBUG log message
     * @param message String message to log
     * @param args Objects
     */
    public static void d(String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null){
            // do something for a debug build
            LogHelper.d(message,args);
        }
    }

    /**
     * Send a VERBOSE log message
     * @param message String message
     * @param args Objects
     */
    public static void v(String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null){
            LogHelper.v(message, args);
        }
    }

    /**
     * Send INFORMATION log message
     * @param message String message
     * @param args Objects
     */
    public static void i(String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null) {
            LogHelper.i(message, args);
        }
    }

    /**
     * Send ERROR log message
     * @param throwable Throwable error
     * @param message String message
     * @param args Objects
     */
    public static void e(Throwable throwable, String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null) {
            LogHelper.e(throwable, message, args);
            f(message, FILE_NAME_FEEDBACK);
        }
    }

    /**
     * Send Error log message
     * @param message String message
     * @param args Objects
     */
    public static void e(String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null) {
            LogHelper.e(message, args);
            f(message, FILE_NAME_FEEDBACK);
        }
    }

    /**
     * send What a Terrible Failure log message
     * @param message String message
     * @param args Objects
     */
    public static void wtf(String message, Object... args) {
        if(TemplateApp.getIsDebuggable() && message != null) {
            LogHelper.wtf(message, args);
        }
    }

    /**
     * Send a JSON log message
     * @param json String the json to show
     */
    public static void json(String json) {
        if(TemplateApp.getIsDebuggable() && json != null) {
            LogHelper.json(json);
        }
    }

    /**
     * Send a XML log message
     * @param xml String the xml to show
     */
    public static void xml(String xml) {
        if(TemplateApp.getIsDebuggable() && xml != null) {
            LogHelper.xml(xml);
        }
    }

    /**
     * Logs the message in a directory
     * @param message
     * @param filename
     */
    public static void f(String message, String filename) {
        String state = Environment.getExternalStorageState();
        File logFile;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File dir = new File(APP_PATH);
            if (!dir.exists()) {
                LogHelper.d("Create directory");
                dir.mkdirs();
            }

            logFile = new File(APP_PATH + filename);
        } else {
            logFile = new File(TemplateApp.getInstance().getCacheDir(),  filename);
        }

        if (!logFile.exists()) {
            try {
                Boolean log = logFile.createNewFile();
                LogHelper.d("Create File on f %s", log);
            } catch (IOException ex) {
                LogHelper.i(ex.getMessage());
            }
        }

        FileWriter fw = null;

        try {
            //BufferedWriter for performance, true to set append to file flag
            fw = new FileWriter(logFile, true);
            BufferedWriter buf = new BufferedWriter(fw);

            buf.write(message);
            buf.newLine();
            buf.flush();
            buf.close();
            fw.close();
        }
        catch (IOException ex) {
            Log.e("", ex.getMessage());
        }
        finally {
            if(fw!=null) {
                try {
                    fw.close();
                } catch(Exception ex) {
                    Log.i("", ex.getMessage());
                }
            }
        }

    }

    /**
     * Clear the log
     * @param filename
     */
    public static void clearLog(String filename) {
        String state = Environment.getExternalStorageState();
        File logFile;

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File dir = new File(APP_PATH);
            if (!dir.exists()) {
                LogHelper.d("Created Directory");
                dir.mkdirs();
            }

            logFile = new File(APP_PATH + filename);
        } else {
            logFile = new File(TemplateApp.getInstance().getCacheDir(),  filename);
        }

        FileWriter fw = null;
        try {
            //BufferedWriter for performance, true to set append to file flag
            fw = new FileWriter(logFile, false);
            PrintWriter pwOb = new PrintWriter(fw, false);
            pwOb.flush();
            pwOb.close();
        }
        catch (IOException ex) {
            e(ex.getMessage());
        }
        finally {
            if(fw!=null) {
                try {
                    fw.close();
                } catch(Exception ex) {
                    LogHelper.e(ex.getMessage());
                }
            }
        }
    }

}
