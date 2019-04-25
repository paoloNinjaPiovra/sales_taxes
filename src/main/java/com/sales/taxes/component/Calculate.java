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

    public BigDecimal scale(BigDecimal number) {
        return number.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal adder(BigDecimal number1, BigDecimal number2) {
        BigDecimal sum = number1.add(number2);
        return scale(sum);
    }

    public BigDecimal round(BigDecimal number) {
        double round = Math.ceil(number.doubleValue() / 0.05d) * 0.05d;
        return scale(BigDecimal.valueOf(round));
    }

    public void sumSalesTaxes(RecipeOutput recipeOutput, Article articleOutput) {
        BigDecimal salesTaxes = adder(recipeOutput.getSalesTaxes(), articleOutput.getSalesTaxes());
        recipeOutput.setSalesTaxes(salesTaxes);
    }

    public void sumTotal(RecipeOutput recipeOutput, Article articleOutput) {
        BigDecimal total = adder(recipeOutput.getTotal(), articleOutput.getCost());
        recipeOutput.setTotal(total);
    }

    public Article calculateArticleOutput(Article articleOutput, Integer taxRate) {
        BigDecimal salesTaxes = calculateSalesTaxes(articleOutput, taxRate);
        BigDecimal cost = adder(articleOutput.getCost(), salesTaxes);
        articleOutput.setCost(cost);
        articleOutput.setSalesTaxes(salesTaxes);
        return articleOutput;
    }

    public BigDecimal calculateSalesTaxes(Article articleOutput, Integer taxRate) {
        BigDecimal tax = articleOutput.getCost().multiply(BigDecimal.valueOf(taxRate)).divide(BigDecimal.valueOf(100));
        BigDecimal roundedTax = round(tax);
        return scale(roundedTax);
    }

    public Article taxImported(Article article) {
        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 15);
    }

    public Article taxNotImported(Article article) {
        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 10);
    }

    public Article noTaxImported(Article article) {
        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 5);
    }

    public Article noTaxNotImported(Article article) {
        Article articleOutput = article;
        return articleOutput;
    }
}
