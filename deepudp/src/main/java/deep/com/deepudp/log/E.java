package deep.com.deepudp.log;

import android.util.Log;

/**
 * Created by wangfei on 2017/11/29.
 */

public class E implements UInterface{
    @Override
    public void log(String tag, String message) {
        Log.e(tag,message);
    }
}
