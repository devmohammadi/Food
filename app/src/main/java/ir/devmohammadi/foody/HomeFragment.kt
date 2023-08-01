package ir.devmohammadi.foody

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.shimmer.ShimmerFrameLayout
import com.todkars.shimmer.ShimmerRecyclerView

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<ShimmerRecyclerView>(R.id.recyclerView)
        recyclerView.showShimmer()

        return view
    }
}