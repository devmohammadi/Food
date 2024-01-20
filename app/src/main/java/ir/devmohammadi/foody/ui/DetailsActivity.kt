package ir.devmohammadi.foody.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import ir.devmohammadi.foody.R
import ir.devmohammadi.foody.adapter.PagerAdapter
import ir.devmohammadi.foody.data.database.entities.FavoritesEntity
import ir.devmohammadi.foody.ui.fragments.ingredients.IngredientsFragment
import ir.devmohammadi.foody.ui.fragments.instructions.InstructionsFragment
import ir.devmohammadi.foody.ui.fragments.overview.OverviewFragment
import ir.devmohammadi.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import ir.devmohammadi.foody.viewmodels.MainViewModel

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var detailsLayout: ConstraintLayout

    private var recipeSaved = false
    private var saveRecipeId = 0
    private lateinit var menuItem : MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)

        detailsLayout = findViewById<ConstraintLayout>(R.id.detailsLayout)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragment = ArrayList<Fragment>()
        fragment.add(OverviewFragment())
        fragment.add(IngredientsFragment())
        fragment.add(InstructionsFragment())

        val title = ArrayList<String>()
        title.add("Overview")
        title.add("Ingredients")
        title.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val adapter = PagerAdapter(resultBundle, fragment, title, supportFragmentManager)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        menuItem = menu!!.findItem(R.id.save_to_favorites_menu)
        checkSaveRecipes(menuItem!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu && !recipeSaved) {
            saveToFavorite(item)
        } else if (item.itemId == R.id.save_to_favorites_menu && recipeSaved) {
            removeFromFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSaveRecipes(menuItem: MenuItem) {
        mainViewModel.readFavoriteRecipes.observe(this) { favoritesEntity ->
            try {
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.id == args.result.id) {
                        changeMenuItem(
                            menuItem,
                            resources.getDrawable(R.drawable.baseline_favorite),
                            R.color.red
                        )
                        saveRecipeId = savedRecipe.id
                        recipeSaved = true
                    }
                }
            } catch (e: Exception) {
                Log.d("DetailsActivity", e.message.toString())
            }
        }
    }

    private fun saveToFavorite(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(0, args.result)
        mainViewModel.insertFavoriteRecipes(favoritesEntity)
        changeMenuItem(item, resources.getDrawable(R.drawable.baseline_favorite), R.color.red)
        showSnackBar("Recipe Saved.")
        recipeSaved = true
    }

    private fun removeFromFavorites(item: MenuItem) {
        val favoritesEntity = FavoritesEntity(saveRecipeId, args.result)
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItem(
            item,
            resources.getDrawable(R.drawable.baseline_favorite_border),
            R.color.white
        )
        showSnackBar("Remove From Favorites..")
        recipeSaved = false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(detailsLayout, message, Snackbar.LENGTH_SHORT).setAction("Okay") {}.show()
    }

    private fun changeMenuItem(item: MenuItem, baselineFavorite: Drawable, color: Int) {
        item.icon = baselineFavorite
        item.icon!!.setTint(ContextCompat.getColor(this, color))
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItem(
            menuItem,
            resources.getDrawable(R.drawable.baseline_favorite_border),
            R.color.white
        )
    }
}