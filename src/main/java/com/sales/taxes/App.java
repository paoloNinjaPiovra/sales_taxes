package com.sales.taxes;

import com.sales.taxes.component.Calculate;
import com.sales.taxes.model.RecipeOutput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        try {
            Calculate calculate = new Calculate();
            RecipeOutput recipeOutput1 = calculate.calculate("input1");
            System.out.println("Output 1:");
            System.out.println(recipeOutput1.toString());
            System.out.println();

            RecipeOutput recipeOutput2 = calculate.calculate("input2");
            System.out.println("Output 2:");
            System.out.println(recipeOutput2.toString());
            System.out.println();

            RecipeOutput recipeOutput3 = calculate.calculate("input3");
            System.out.println("Output 3:");
            System.out.println(recipeOutput3.toString());
            System.out.println();

            RecipeOutput recipeOutput4 = calculate.calculate("input4");
            System.out.println("Output 4:");
            System.out.println(recipeOutput4.toString());
            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
