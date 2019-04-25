package com.sales.taxes.model;

import com.sales.taxes.enums.TaxStatus;
import com.sales.taxes.enums.TaxFree;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Article {

    private Integer amount;
    private BigDecimal cost;
    private String description;
    private BigDecimal salesTaxes;
    private TaxStatus taxStatus;

    public Article() {
        amount = 0;
        cost = new BigDecimal(0.0);
        salesTaxes = new BigDecimal(0.0);
        taxStatus = TaxStatus.NO_TAX_NOT_IMPORTED;
    }

    public Article(Integer amount, String description, BigDecimal cost,  BigDecimal salesTaxes, TaxStatus taxStatus) {
        this.amount = amount;
        this.cost = cost;
        this.description = description;
        this.salesTaxes = salesTaxes;
        this.taxStatus = taxStatus;
    }

    public Article createArticle(List<String> list) {
        Boolean imported = imported(list);
        Article article = new Article();
        article.setAmount(getAmount(list));
        article.setCost(getCost(list));
        article.setDescription(getDescription(list, imported));
        article.setTaxStatus(getTaxStatusCalculation(tax(list) ? Boolean.TRUE : Boolean.FALSE, imported ? Boolean.TRUE : Boolean.FALSE));
        return article;
    }

    public TaxStatus getTaxStatusCalculation(Boolean tax, Boolean imported) {
        if (tax == true && imported == true) {
            return TaxStatus.TAX_IMPORTED;              //15%
        } else if (tax == true && imported == false) {
            return TaxStatus.TAX_NOT_IMPORTED;          //10%
        } else if (tax == false && imported == true) {
            return TaxStatus.NO_TAX_IMPORTED;           // 5%
        }
        return TaxStatus.NO_TAX_NOT_IMPORTED;           // 0%
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public void setSalesTaxes(BigDecimal salesTaxes) {
        this.salesTaxes = salesTaxes;
    }

    public TaxStatus getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(TaxStatus taxStatus) {
        this.taxStatus = taxStatus;
    }

    public Integer getAmount(List<String> list) {
        return Integer.parseInt(list.get(0));
    }

    public BigDecimal getCost(List<String> list) {
        return BigDecimal.valueOf(Double.parseDouble(list.get(list.size() - 1)));
    }

    public Boolean imported(List<String> list){
        if (list.contains("imported"))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public Boolean tax(List<String> list) {
        if (Arrays.stream(TaxFree.values())
                .map(TaxFree::getValue)
                .anyMatch(list::contains))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public String getDescription(List<String> list, Boolean imported) {
        final String IMPORTED = "imported";
        StringBuffer stringBuffer;

        String output = String.join(" ", (list.subList(1, list.size() - 1)));
        if (imported && output.lastIndexOf(IMPORTED) != 0) {
            stringBuffer = new StringBuffer();
            stringBuffer.append(IMPORTED);
            stringBuffer.append(" ");
            stringBuffer.append(output.substring(0, output.lastIndexOf(IMPORTED)).trim());
            stringBuffer.append(" ");
            stringBuffer.append(output.substring(output.lastIndexOf(IMPORTED) + IMPORTED.length(), output.length()));
            return stringBuffer.toString();
        }
        return output;
    }
}