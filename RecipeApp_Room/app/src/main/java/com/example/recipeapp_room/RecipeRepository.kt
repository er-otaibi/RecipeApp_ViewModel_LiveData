package com.example.recipeapp_room

import androidx.lifecycle.LiveData

class RecipeRepository(private val recipeDao: RecipeDao) {

    val getRecipe: LiveData<List<Recipe>> = recipeDao.getAllRecipe()

    suspend fun addRecipe(recipe: Recipe){
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe){
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(id: Int){
        recipeDao.deleteRecipe(id)
    }
}

