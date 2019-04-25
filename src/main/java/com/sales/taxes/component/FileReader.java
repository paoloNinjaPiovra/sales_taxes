package com.sales.taxes.component;

import com.sales.taxes.model.Article;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Article> read(String name) {
        BufferedReader reader;
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(name).getFile());
        List<Article> aritcles = new ArrayList<>();
        try {
            reader = new BufferedReader(new java.io.FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            ArticleBuilder lineParser = new ArticleBuilder();
            while (line != null) {
                aritcles.add(lineParser.articleBuilder(line));
                line = reader.readLine();

            }
            reader.close();
            return aritcles;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
