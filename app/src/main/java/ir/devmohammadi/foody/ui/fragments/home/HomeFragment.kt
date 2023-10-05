package ir.devmohammadi.foody.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.devmohammadi.foody.viewmodels.MainViewModel
import ir.devmohammadi.foody.R
import ir.devmohammadi.foody.adapter.RecipesAdapter
import ir.devmohammadi.foody.util.Constants.Companion.API_KEY
import ir.devmohammadi.foody.util.NetworkResult
import ir.devmohammadi.foody.viewmodels.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeViewModel: HomeViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View
    private lateinit var recyclerView: ShimmerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = mView.findViewById(R.id.recyclerView)

        setupRecyclerView()
        requestApiData()

        return mView
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(homeViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        recyclerView.hideShimmer()
    }
}