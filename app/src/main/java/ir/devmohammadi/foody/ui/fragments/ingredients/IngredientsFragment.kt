package ir.devmohammadi.foody.ui.fragments.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ir.devmohammadi.foody.adapter.IngredientsAdapter
import ir.devmohammadi.foody.databinding.FragmentIngredientsBinding
import ir.devmohammadi.foody.models.Result
import ir.devmohammadi.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import ir.devmohammadi.foody.util.retrieveParcelable

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.retrieveParcelable(RECIPE_RESULT_KEY)

        setupRecyclerView()
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}