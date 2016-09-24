package zeroh729.com.kitestring.utils;

import android.util.Log;
import android.widget.Toast;

import zeroh729.com.kitestring.KiteString_;

public class _ {
    public static void showToast(String message){
        Toast.makeText(KiteString_.getInstance().getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void log(String message){
        Log.d("TEST", message);
    }

    public static void logError(String message){
        Log.e("TEST", message);
    }
}
