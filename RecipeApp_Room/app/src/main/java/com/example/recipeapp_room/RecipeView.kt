package com.example.recipeapp_room

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeView : AppCompatActivity() {
    private val myViewModel by lazy{ ViewModelProvider(this).get(MyViewModel::class.java)}
    lateinit var rvMain : RecyclerView
    private lateinit var rvAdapter: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_view)

        supportActionBar?.hide()
        rvMain = findViewById(R.id.rvMain)

        myViewModel.getRecipes().observe(this, {

                recipes -> rvAdapter = RecipeAdapter(this, recipes)
                           rvMain.adapter = rvAdapter
                           rvMain.layoutManager = LinearLayoutManager(this)
        })
    }


    fun editAlert(idRecipe: Int , title: String, author: String, ingredients: String,instructions: String) {

        val builder = AlertDialog.Builder(this)

        val view = layoutInflater.inflate(R.layout.edit_alert, null)

        builder.setView(view)

        val alertDialog: AlertDialog = builder.create()

        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etAuthor = view.findViewById<EditText>(R.id.etAuthor)
        val etIngredients = view.findViewById<EditText>(R.id.etIngredients)
        val etInstructions = view.findViewById<EditText>(R.id.etInstructions)
        val edit = view.findViewById<Button>(R.id.edit)

        etTitle.setText(title)
        etAuthor.setText(author)
        etIngredients.setText(ingredients)
        etInstructions.setText(instructions)

        alertDialog.show()


        edit.setOnClickListener {
            var utitle = etTitle.text.toString()
            var uauthor = etAuthor.text.toString()
            var uingredients = etIngredients.text.toString()
            var uinstructions = etInstructions.text.toString()

           myViewModel.editRecipe(idRecipe,utitle,uauthor,uingredients,uinstructions)

           alertDialog.dismiss()

        }

    }

    fun deleteAlert( id: Int){
        val dialogBuilder = androidx.appcompat.app.AlertDialog.Builder(this)


        dialogBuilder.setMessage("Confirm delete ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener {
                    _, _ ->

              myViewModel.deleteRecipe(id)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()

        alert.setTitle("Delete Alert")
        alert.show()


    }
}