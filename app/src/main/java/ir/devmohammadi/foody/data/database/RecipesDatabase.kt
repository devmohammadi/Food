package ir.devmohammadi.foody.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.devmohammadi.foody.data.database.entities.FavoritesEntity
import ir.devmohammadi.foody.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class , FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase:RoomDatabase() {

    abstract fun recipesDao(): RecipesDao
}