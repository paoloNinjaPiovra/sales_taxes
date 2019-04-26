package com.sales.taxes.component;

import com.sales.taxes.exceptions.SalesTaxesArticleException;
import com.sales.taxes.exceptions.SalesTaxesCalculationException;
import com.sales.taxes.model.Article;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticleBuilder {

    public Article articleBuilder (String line) {
        try {

            if (StringUtils.isEmpty(line))
                throw new SalesTaxesArticleException("No line found");
            //splitto la stringa per ottenere una List di String con gli elementi splittati a ogni spazio
            List<String> list = split(line);
            Article article = new Article();
            //creo l'oggetto Article a partire dalla List ottenuta
            return article.createArticle(list);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<String> split(String line) throws SalesTaxesCalculationException {
        //splitto la riga per e il carattere di divisione è lo spazio
        List<String> splittedLine = new ArrayList<>(Arrays.asList(line.replace(" at ", " ").split(" ")));

        if (null == splittedLine || splittedLine.isEmpty())
            throw new SalesTaxesCalculationException("Error in string splitting");

        return splittedLine;
    }
}
