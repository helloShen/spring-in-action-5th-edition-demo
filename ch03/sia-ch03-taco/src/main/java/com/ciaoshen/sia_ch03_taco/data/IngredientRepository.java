package com.ciaoshen.sia_ch03_taco.data;

import com.ciaoshen.sia_ch03_taco.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}