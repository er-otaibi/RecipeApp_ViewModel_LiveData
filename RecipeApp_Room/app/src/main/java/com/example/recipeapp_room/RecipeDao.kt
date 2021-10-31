package com.example.recipeapp_room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface RecipeDao{

    @Query("SELECT * FROM Recipe")
    fun getAllRecipe(): LiveData<List<Recipe>>

    @Insert
    fun insertRecipe(recipe: Recipe)


    @Query("DELETE FROM Recipe where id=:pk")
    fun deleteRecipe(pk: Int)

    @Update
    fun updateRecipe(recipe: Recipe)

}