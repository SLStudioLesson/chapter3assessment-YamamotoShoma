package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for(int i = 1; i < parts.length; i++) {
                    ingredients.add(new Ingredient(parts[i].trim()));
                }
                recipes.add(new Recipe(name, ingredients));
            }
        }
        return recipes;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.newLine();
            writer.write(recipe.getName());
            for(Ingredient ingredient : recipe.getIngredients()) {
                writer.write("," + ingredient.getName());
            }
        } catch(IOException e) {
            throw new IOException("Error writing to file: " + filePath, e);
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null; //以降の設問で処理を実装する
    }
}
