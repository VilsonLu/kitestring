package zeroh729.com.kitestring;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@EApplication
public class KiteString extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public Context getContext(){
        return context;
    }
}
