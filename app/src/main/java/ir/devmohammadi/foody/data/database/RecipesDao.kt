package ir.devmohammadi.foody.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.devmohammadi.foody.data.database.entities.FavoritesEntity
import ir.devmohammadi.foody.data.database.entities.FoodJokeEntity
import ir.devmohammadi.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipesEntity: RecipesEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)
    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes():Flow<List<RecipesEntity>>
    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes():Flow<List<FavoritesEntity>>
    @Delete
    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)
    @Query("DELETE FROM favorite_recipes_table")
    fun deleteAllFavoriteRecipe()
    @Query("SELECT * FROM food_joke_table ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)
}