package com.sales.taxes.component;

import com.sales.taxes.model.Article;
import com.sales.taxes.model.RecipeOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculate {

    public Calculate() {

    }

    public RecipeOutput calculate(String file) {
        FileReader fileReader = new FileReader();
        List<Article> articles = fileReader.read(file);
        RecipeOutput recipeOutput = new RecipeOutput();
        Article articleOutput;
        for (Article article : articles) {
            switch (article.getTaxStatus()) {
                case TAX_IMPORTED:
                    articleOutput = taxImported(article);
                    break;
                case TAX_NOT_IMPORTED:
                    articleOutput = taxNotImported(article);
                    break;
                case NO_TAX_IMPORTED:
                    articleOutput = noTaxImported(article);
                    break;
                default:
                    articleOutput = noTaxNotImported(article);
                    break;
            }
            recipeOutput.getArticles().add(articleOutput);
            sumSalesTaxes(recipeOutput, articleOutput);
            sumTotal(recipeOutput, articleOutput);
        }
        return recipeOutput;
    }

    public BigDecimal round(BigDecimal number) {
        Double tmp = Math.round(number.doubleValue() * 20.0) / 20.0 ;
        return BigDecimal.valueOf(tmp);
    }

    public void sumSalesTaxes(RecipeOutput recipeOutput, Article articleOutput) {
        recipeOutput.setSalesTaxes(recipeOutput.getSalesTaxes().add(articleOutput.getSalesTaxes()));
    }

    public void sumTotal(RecipeOutput recipeOutput, Article articleOutput) {
        recipeOutput.setTotal(recipeOutput.getTotal().add(articleOutput.getCost()));
    }

    public Article calculateArticleOutput(Article articleOutput, Integer taxRate) {
        BigDecimal salesTaxes = calculateSalesTaxes(articleOutput, taxRate);
        articleOutput.setCost(articleOutput.getCost().add(salesTaxes));
        articleOutput.setSalesTaxes(salesTaxes);
        return articleOutput;
    }

    public BigDecimal calculateSalesTaxes(Article articleOutput, Integer taxRate) {
        return round(articleOutput.getCost().multiply(BigDecimal.valueOf(taxRate)).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.UP));
    }

    public Article taxImported(Article article) { //15%
        Article articleOutput = article;
        return (calculateArticleOutput(articleOutput, 15));
    }

    public Article taxNotImported(Article article) { //10%
        Article articleOutput = article;
        return (calculateArticleOutput(articleOutput, 10));
    }

    public Article noTaxImported(Article article) { //5%
        Article articleOutput = article;
        return (calculateArticleOutput(articleOutput, 5));
    }

    public Article noTaxNotImported(Article article) { //0%
        Article articleOutput = article;
        return articleOutput;
    }
}
