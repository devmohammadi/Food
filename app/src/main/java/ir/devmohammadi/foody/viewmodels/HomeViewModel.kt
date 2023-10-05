package ir.devmohammadi.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ir.devmohammadi.foody.util.Constants.Companion.API_KEY
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_API_KEY
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_DIET
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_NUMBER
import ir.devmohammadi.foody.util.Constants.Companion.QUERY_TYPE

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        // https://api.spoonacular.com/recipes/complexSearch?apiKey=39934bc5da994841957a1b29d51156c9&number=50&type=snack&diet=vegan&addRecipeInformation=true&fillIngredients=true
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}