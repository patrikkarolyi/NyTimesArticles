package com.bme.mdt72t.nytimesarticles.interactor;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bme.mdt72t.nytimesarticles.network.RESTHelper;
import com.bme.mdt72t.nytimesarticles.repository.ArticleRepository;
import com.bme.mdt72t.nytimesarticles.repository.GetLocalArticlesTask;
import com.bme.mdt72t.nytimesarticles.repository.SetLocalArticlesTask;
import com.bme.mdt72t.nytimesarticles.model.Article;
import com.bme.mdt72t.nytimesarticles.ui.main.PresenterInterface;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Interactor {

    private RESTHelper restHelper;
    private ArticleRepository articleRepository;
    private Application application;


    public Interactor(Application application){
        this.articleRepository= new ArticleRepository(application);
        this.restHelper = new RESTHelper();
        this.application = application;
    }


    public boolean isFirstRun() {
        return application.getSharedPreferences("run", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
    }

    public void getLocalArticles(PresenterInterface presenter) {
        new GetLocalArticlesTask(presenter,articleRepository).execute();
    }

    public void setLocalArticles(List<Article> articles) {
        new SetLocalArticlesTask(articles,articleRepository).execute();
        application.getSharedPreferences("run", MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun", false)
                .apply();
    }

    public void getRESTArticles(PresenterInterface presenter){
        restHelper.getArticle(presenter);
    }

    public boolean isInternetAvailable() {
        NetworkInfo info = ((ConnectivityManager)
                application.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            return info != null && info.isConnected();
    }
}
