package ir.devmohammadi.foody.data.network

import ir.devmohammadi.foody.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipeApi {

    @GET("recipes/complexSearch")
    fun getRecipe(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>

}