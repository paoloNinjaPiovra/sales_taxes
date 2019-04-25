package com.sales.taxes;

import com.sales.taxes.component.Calculate;
import com.sales.taxes.component.FileReader;
import com.sales.taxes.enums.TaxStatus;
import com.sales.taxes.model.Article;
import com.sales.taxes.model.RecipeOutput;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testInput1() {
        Calculate calculate = new Calculate();
        RecipeOutput recipeOutput = calculate.calculate("input1");
        System.out.println(recipeOutput.toString());
        RecipeOutput output1 = getOutput1();

        /*assertEquals(output1.getSalesTaxes(), recipeOutput.getSalesTaxes());
        assertEquals(output1.getTotal(), recipeOutput.getTotal());
        int output1Size = output1.getArticles().size();
        int recipeOutputSize = recipeOutput.getArticles().size();
        assertEquals(output1Size, recipeOutputSize);
        Article output1List[] = new Article[output1Size];
        output1List = output1.getArticles().toArray(output1List);
        Article recipeOutputList[] = new Article[recipeOutputSize];
        recipeOutputList = output1.getArticles().toArray(recipeOutputList);
        for (int i = 0; i < recipeOutputSize; i++) {
            assertEquals(output1List[i], recipeOutputList[i]);
        }*/
    }

    @Test
    public void testInput2() {
        Calculate calculate = new Calculate();
        RecipeOutput recipeOutput = calculate.calculate("input2");
        System.out.println(recipeOutput.toString());
        RecipeOutput output2 = getOutput2();

        /*assertEquals(output2.getSalesTaxes(), recipeOutput.getSalesTaxes());
        assertEquals(output2.getTotal(), recipeOutput.getTotal());
        int output1Size = output2.getArticles().size();
        int recipeOutputSize = recipeOutput.getArticles().size();
        assertEquals(output1Size, recipeOutputSize);
        Article output1List[] = new Article[output1Size];
        output1List = output2.getArticles().toArray(output1List);
        Article recipeOutputList[] = new Article[recipeOutputSize];
        recipeOutputList = output2.getArticles().toArray(recipeOutputList);
        for (int i = 0; i < recipeOutputSize; i++) {
            assertEquals(output1List[i], recipeOutputList[i]);
        }*/
    }

    @Test
    public void testInput3() {
        Calculate calculate = new Calculate();
        RecipeOutput recipeOutput = calculate.calculate("input3");
        System.out.println(recipeOutput.toString());
        RecipeOutput output3 = getOutput3();

        /*assertEquals(output3.getSalesTaxes(), recipeOutput.getSalesTaxes());
        assertEquals(output3.getTotal(), recipeOutput.getTotal());
        int output1Size = output3.getArticles().size();
        int recipeOutputSize = recipeOutput.getArticles().size();
        assertEquals(output1Size, recipeOutputSize);
        Article output1List[] = new Article[output1Size];
        output1List = output3.getArticles().toArray(output1List);
        Article recipeOutputList[] = new Article[recipeOutputSize];
        recipeOutputList = output3.getArticles().toArray(recipeOutputList);
        for (int i = 0; i < recipeOutputSize; i++) {
            assertEquals(output1List[i], recipeOutputList[i]);
        }*/
    }

    @Test
    public void testFileReader() {
        FileReader fileReader = new FileReader();
        List<Article> articles = fileReader.read("input2");

        assertTrue(null != articles && !articles.isEmpty());
    }

    //@Test
    public void testRound() {
        Calculate calculate = new Calculate();
        BigDecimal start = BigDecimal.valueOf(11.8125);
        BigDecimal end = BigDecimal.valueOf(11.85);

        //assertEquals(calculate.round(start), end);
    }

    public RecipeOutput getOutput1() {
        RecipeOutput recipe = new RecipeOutput();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "book", BigDecimal.valueOf(12.49), BigDecimal.valueOf(0), TaxStatus.NO_TAX_NOT_IMPORTED));
        articles.add(new Article(1, "music CD", BigDecimal.valueOf(16.49), BigDecimal.valueOf(1.50), TaxStatus.TAX_NOT_IMPORTED));
        articles.add(new Article(1, "chocolate bar", BigDecimal.valueOf(0.85), BigDecimal.valueOf(0), TaxStatus.TAX_NOT_IMPORTED));
        recipe.setArticles(articles);
        RecipeOutput output1 = new RecipeOutput();
        output1.setArticles(articles);
        output1.setSalesTaxes(BigDecimal.valueOf(1.50));
        output1.setTotal(BigDecimal.valueOf(29.83));
        return output1;
    }

    public RecipeOutput getOutput2() {
        RecipeOutput recipe = new RecipeOutput();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "imported box of chocolates", BigDecimal.valueOf(10.50), BigDecimal.valueOf(0), TaxStatus.NO_TAX_IMPORTED));
        articles.add(new Article(1, "imported bottle of perfume", BigDecimal.valueOf(54.65), BigDecimal.valueOf(0), TaxStatus.TAX_IMPORTED));
        recipe.setArticles(articles);
        RecipeOutput output2 = new RecipeOutput();
        output2.setArticles(articles);
        output2.setSalesTaxes(BigDecimal.valueOf(7.65));
        output2.setTotal(BigDecimal.valueOf(65.15));
        return output2;
    }

    public RecipeOutput getOutput3() {
        RecipeOutput recipe = new RecipeOutput();
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1, "imported bottle of perfume", BigDecimal.valueOf(32.19), BigDecimal.valueOf(0), TaxStatus.TAX_IMPORTED));
        articles.add(new Article(1, "bottle of perfume", BigDecimal.valueOf(20.89), BigDecimal.valueOf(0), TaxStatus.TAX_NOT_IMPORTED));
        articles.add(new Article(1, "packet of headache pills", BigDecimal.valueOf(9.75), BigDecimal.valueOf(0), TaxStatus.NO_TAX_NOT_IMPORTED));
        articles.add(new Article(1, "imported box of chocolates", BigDecimal.valueOf(11.85), BigDecimal.valueOf(0), TaxStatus.NO_TAX_IMPORTED));
        recipe.setArticles(articles);
        RecipeOutput output3 = new RecipeOutput();
        output3.setArticles(articles);
        output3.setSalesTaxes(BigDecimal.valueOf(6.70));
        output3.setTotal(BigDecimal.valueOf(74.68));
        return output3;
    }
}
