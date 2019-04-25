package com.sales.taxes.component;

import com.sales.taxes.model.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleBuilder {

    public Article articleBuilder (String line) {
        List<String> list = new ArrayList<>(Arrays.asList(line.replace(" at ", " ").split(" ")));
        Article article = new Article();
        return article.createArticle(list);
    }
}
