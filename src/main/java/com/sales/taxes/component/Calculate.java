package com.sales.taxes.component;

import com.sales.taxes.exceptions.SalesTaxesCalculationException;
import com.sales.taxes.model.Article;
import com.sales.taxes.model.RecipeOutput;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculate {

    public Calculate() {

    }

    public RecipeOutput calculate(String file) throws SalesTaxesCalculationException {
        try {
            Article articleOutput;
            FileReader fileReader = new FileReader();
            RecipeOutput recipeOutput = new RecipeOutput();

            if (StringUtils.isEmpty(file))
                throw new SalesTaxesCalculationException("File not found");

            List<Article> articles = fileReader.read(file);

            if (null == articles || articles.isEmpty())
                throw new SalesTaxesCalculationException("Articles not present");

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

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public BigDecimal scale(BigDecimal number) throws SalesTaxesCalculationException {
        if (null == number)
            throw new SalesTaxesCalculationException("No element to scale");

        return number.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal adder(BigDecimal number1, BigDecimal number2) throws SalesTaxesCalculationException {
        if (null == number1 || null == number2)
            throw new SalesTaxesCalculationException("Missing element for the sum ");

        BigDecimal sum = number1.add(number2);
        return scale(sum);
    }

    public BigDecimal round(BigDecimal number) throws SalesTaxesCalculationException {
        if (null == number)
            throw new SalesTaxesCalculationException("No element for tax rounding");

        double round = Math.ceil(number.doubleValue() / 0.05d) * 0.05d;
        return scale(BigDecimal.valueOf(round));
    }

    public void sumSalesTaxes(RecipeOutput recipeOutput, Article articleOutput) throws SalesTaxesCalculationException {
        if (null == recipeOutput || null == articleOutput)
            throw new SalesTaxesCalculationException("No element for tax calculation");

        BigDecimal salesTaxes = adder(recipeOutput.getSalesTaxes(), articleOutput.getSalesTaxes());
        recipeOutput.setSalesTaxes(salesTaxes);
    }

    public void sumTotal(RecipeOutput recipeOutput, Article articleOutput) throws SalesTaxesCalculationException {
        if (null == recipeOutput || null == articleOutput)
            throw new SalesTaxesCalculationException("No element for total calculation");

        BigDecimal total = adder(recipeOutput.getTotal(), articleOutput.getCost());
        recipeOutput.setTotal(total);
    }

    public Article calculateArticleOutput(Article articleOutput, Integer taxRate) throws SalesTaxesCalculationException {
        if (null == articleOutput || null == taxRate)
            throw new SalesTaxesCalculationException("Missing element for tax calculation");

        BigDecimal salesTaxes = calculateSalesTaxes(articleOutput, taxRate);
        BigDecimal cost = adder(articleOutput.getCost(), salesTaxes);
        articleOutput.setCost(cost);
        articleOutput.setSalesTaxes(salesTaxes);
        return articleOutput;
    }

    public BigDecimal calculateSalesTaxes(Article articleOutput, Integer taxRate) throws SalesTaxesCalculationException {
        if (null == articleOutput || null == taxRate)
            throw new SalesTaxesCalculationException("Missing element for tax calculation");

        BigDecimal tax = articleOutput.getCost().multiply(BigDecimal.valueOf(taxRate)).divide(BigDecimal.valueOf(100));
        BigDecimal roundedTax = round(tax);
        return scale(roundedTax);
    }

    public Article taxImported(Article article) throws SalesTaxesCalculationException {
        if (null == article)
            throw new SalesTaxesCalculationException("Article not valid during tax calculation");

        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 15);
    }

    public Article taxNotImported(Article article) throws SalesTaxesCalculationException {
        if (null == article)
            throw new SalesTaxesCalculationException("Article not valid during tax calculation");

        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 10);
    }

    public Article noTaxImported(Article article) throws SalesTaxesCalculationException {
        if (null == article)
            throw new SalesTaxesCalculationException("Article not valid during tax calculation");

        Article articleOutput = article;
        return calculateArticleOutput(articleOutput, 5);
    }

    public Article noTaxNotImported(Article article) throws SalesTaxesCalculationException {
        if (null == article)
            throw new SalesTaxesCalculationException("Article not valid during tax calculation");

        Article articleOutput = article;
        return articleOutput;
    }
}
