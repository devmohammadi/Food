package ir.devmohammadi.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ir.devmohammadi.foody.R
import ir.devmohammadi.foody.adapter.PagerAdapter
import ir.devmohammadi.foody.ui.fragments.ingredients.IngredientsFragment
import ir.devmohammadi.foody.ui.fragments.instructions.InstructionsFragment
import ir.devmohammadi.foody.ui.fragments.overview.OverviewFragment
import ir.devmohammadi.foody.util.Constants.Companion.RECIPE_RESULT_KEY

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}