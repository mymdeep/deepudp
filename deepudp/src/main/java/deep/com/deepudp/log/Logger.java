package deep.com.deepudp.log;

import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangfei on 2017/11/29.
 */

public class Logger {
    private static final int KEYLENGTH = 10;
    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char MIDDLE_CORNER = '├';
    private static final String INDENT = "     ";
    private static final char HORIZONTAL_LINE = '│';
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String DOUBLE_DIVIDER_AQ = "───────────────────问题─────────────────────";
    private static final String SINGLE_DIVIDER_AQ = "───────────────────解决方案─────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String TOP_BORDER_AQ = TOP_LEFT_CORNER + DOUBLE_DIVIDER_AQ + DOUBLE_DIVIDER;
    private static final String AQ = MIDDLE_CORNER + SINGLE_DIVIDER_AQ + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;
    private static final int JSON_INDENT = 2;
    private static final String TAG = "deepSQL";
    public static boolean debug = true;
    public static void jsonObject(JSONObject jsonObject){
        try {
            String message = jsonObject.toString(JSON_INDENT);
            if (debug){
                Log.e(TAG,message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void jsonArry(JSONArray jsonArry){
        try {
            String message = jsonArry.toString(JSON_INDENT);
            if (debug) {
                Log.e(TAG, message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void aq(int priority,String a,String q){

        UInterface log = getLogger(priority);
        log.log(TAG,TOP_BORDER_AQ);

            log.log(TAG,HORIZONTAL_LINE+INDENT+a);

                log.log(TAG,AQ);
        log.log(TAG,HORIZONTAL_LINE+INDENT+q);
        log.log(TAG,BOTTOM_BORDER);
    }
    public static void mutlInfo(int priority,String... args){
        if (debug) {
            int length = args.length;
            UInterface log = getLogger(priority);
            log.log(TAG, TOP_BORDER);
            for (int i = 0; i < length; i++) {
                log.log(TAG, HORIZONTAL_LINE + INDENT + args[i]);
                if (i != length - 1) {
                    log.log(TAG, MIDDLE_BORDER);
                }

            }
            log.log(TAG, BOTTOM_BORDER);
        }
    }

    public static void error(String message){
        if (debug) {
            UInterface log = getLogger(0);
            log.log(TAG, "『" + "error』" + " [  " + message + "  ] ");
        }
    }
    public static void single(int priority,String message){
        if (debug) {
            UInterface log = getLogger(priority);
            log.log(TAG, " [  " + message + "  ] ");
        }
    }
    public static void bundle(int priority,Bundle bundle){
        if (debug) {
            UInterface log = getLogger(priority);
            log.log(TAG, TOP_BORDER);

            log.log(TAG, HORIZONTAL_LINE + "key" + HORIZONTAL_LINE + "value");
            log.log(TAG, MIDDLE_BORDER);
            for (String key : bundle.keySet()) {

                String message = HORIZONTAL_LINE + key + HORIZONTAL_LINE + bundle.get(key).toString();
                log.log(TAG, message);

                log.log(TAG, MIDDLE_BORDER);

            }
            log.log(TAG, BOTTOM_BORDER);
        }
    }
    public static UInterface getLogger(int priority){
        switch (priority){
            case 0:
                return new E();

            case 1:
                return new W();
              default:
                  return new W();
        }

    }
}
