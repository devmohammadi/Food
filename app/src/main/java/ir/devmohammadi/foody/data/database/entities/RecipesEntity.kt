package ir.devmohammadi.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.devmohammadi.foody.models.FoodRecipe
import ir.devmohammadi.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}