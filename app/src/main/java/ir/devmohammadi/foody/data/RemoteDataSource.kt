package ir.devmohammadi.foody.data

import ir.devmohammadi.foody.data.network.FoodRecipeApi
import ir.devmohammadi.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipeApi: FoodRecipeApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipeApi.getRecipe(queries)
    }
}