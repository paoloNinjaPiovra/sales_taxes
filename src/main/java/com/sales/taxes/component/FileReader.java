package com.sales.taxes.component;

import com.sales.taxes.exceptions.SalesTaxesFileReadException;
import com.sales.taxes.model.Article;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Article> read(String name) {
        try {
            BufferedReader reader;
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(name).getFile());

            if (null == file)
                throw new SalesTaxesFileReadException("File not found");

            List<Article> aritcles = new ArrayList<>();

            reader = new BufferedReader(new java.io.FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            ArticleBuilder lineParser = new ArticleBuilder();
            while (line != null) {
                //Scorro riga per riga il file e per ogni riga creo un oggetto di tipo Article
                aritcles.add(lineParser.articleBuilder(line));
                line = reader.readLine();

            }
            reader.close();
            return aritcles;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
