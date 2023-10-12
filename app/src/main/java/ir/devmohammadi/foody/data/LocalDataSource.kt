package ir.devmohammadi.foody.data

import ir.devmohammadi.foody.data.database.RecipesDao
import ir.devmohammadi.foody.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    fun readDatabase() : Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}