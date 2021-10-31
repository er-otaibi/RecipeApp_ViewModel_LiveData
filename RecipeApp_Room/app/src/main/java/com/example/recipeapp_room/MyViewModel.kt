package com.example.recipeapp_room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MyViewModel(application: Application): AndroidViewModel(application) {
    private val repository: RecipeRepository
    private val recipes: LiveData<List<Recipe>>

    init {
        val recipeDao = RecipeDatabase.getInstance(application).RecipeDao()
        repository = RecipeRepository(recipeDao)
        recipes = repository.getRecipe
    }

    fun getRecipes(): LiveData<List<Recipe>> {
        return recipes
    }

    fun addRecipe( title: String, author: String, ingredients: String, instructions: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addRecipe(Recipe(0, title, author, ingredients, instructions))
        }
    }

    fun editRecipe(recipeID: Int, title: String, author: String, ingredients: String, instructions: String){
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateRecipe(Recipe(recipeID,title, author, ingredients, instructions))
        }
    }

    fun deleteRecipe(recipeID: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteRecipe(recipeID)
        }
    }
}