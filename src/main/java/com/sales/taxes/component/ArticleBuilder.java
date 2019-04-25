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

            List<String> list = split(line);
            Article article = new Article();
            return article.createArticle(list);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<String> split(String line) throws SalesTaxesCalculationException {
        List<String> splittedLine = new ArrayList<>(Arrays.asList(line.replace(" at ", " ").split(" ")));

        if (null == splittedLine || splittedLine.isEmpty())
            throw new SalesTaxesCalculationException("Error in string splitting");

        return splittedLine;
    }
}
