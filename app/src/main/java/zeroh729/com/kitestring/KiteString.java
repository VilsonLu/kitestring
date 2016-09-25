package zeroh729.com.kitestring;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

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
