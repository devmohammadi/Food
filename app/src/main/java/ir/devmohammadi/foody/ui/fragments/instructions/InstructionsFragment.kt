package ir.devmohammadi.foody.ui.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ir.devmohammadi.foody.R
import ir.devmohammadi.foody.databinding.FragmentInstructionsBinding
import ir.devmohammadi.foody.models.Result
import ir.devmohammadi.foody.util.Constants.Companion.RECIPE_RESULT_KEY
import ir.devmohammadi.foody.util.retrieveParcelable

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.retrieveParcelable(RECIPE_RESULT_KEY)

        if (myBundle != null) {
            binding.instructionsWebView.webViewClient = object : WebViewClient() {}
            val websiteUrl: String = myBundle.sourceUrl
            binding.instructionsWebView.loadUrl(websiteUrl)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}