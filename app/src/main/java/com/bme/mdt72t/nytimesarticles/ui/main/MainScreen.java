package com.bme.mdt72t.nytimesarticles.ui.main;

import com.bme.mdt72t.nytimesarticles.model.ArticlesPOJO;

public interface MainScreen {
    void showArticles(ArticlesPOJO articlesPOJO);

    void showSnackbar();
    void hideSnackbar();
    void hideSwipeRefreshLayout();

    boolean loadArticleUrl(String url);


}