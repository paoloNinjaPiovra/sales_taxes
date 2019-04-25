package com.sales.taxes.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecipeOutput {

    private List<Article> articles;
    private BigDecimal salesTaxes;
    private BigDecimal total;

    public RecipeOutput() {
        articles = new ArrayList<>();
        salesTaxes = new BigDecimal(0.0);
        total = new BigDecimal(0.0);
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Article article : this.articles) {
            stringBuffer.append(article.getAmount())
                        .append(" ")
                        .append(article.getDescription())
                        .append(": ")
                        .append(article.getCost())
                        .append("\n");
        }
        stringBuffer.append("Sales Taxes: ")
                    .append(this.salesTaxes)
                    .append("\n")
                    .append("Total: ")
                    .append(this.total);
        return stringBuffer.toString();
    }
}