package android.whitewidget.com.boilerplateandroid.data.remote;

import android.whitewidget.com.boilerplateandroid.data.model.Article;
import android.whitewidget.com.boilerplateandroid.utils.OttoBus;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

@EBean
public class BaseService {
    private static final String API_ENDPOINT = "http://readytoparent.com.ph";

    @Bean
    OttoBus bus;

    public interface BaseTask{
        @GET("/articles/?json=get_tag_index")
        Call<Article> listRepos();

        //Add more api links here
    }

    private static BaseTask newService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(BaseTask.class);
    }

    @Background
    public void getArticle(){
        try {
            Call<Article> call = newService().listRepos();
            Article article = call.execute().body();
            bus.post(article);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
