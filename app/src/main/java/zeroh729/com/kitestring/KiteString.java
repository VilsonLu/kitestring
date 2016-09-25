package zeroh729.com.kitestring;

import android.app.Application;
import android.content.Context;

import org.androidannotations.annotations.EApplication;

import zeroh729.com.kitestring.data.model.User;

@EApplication
public class KiteString extends Application{
    private static Context context;
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        user = new User();
    }

    public Context getContext(){
        return context;
    }
}
