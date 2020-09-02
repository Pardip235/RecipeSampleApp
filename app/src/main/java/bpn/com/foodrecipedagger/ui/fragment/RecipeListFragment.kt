package bpn.com.foodrecipedagger.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import bpn.com.foodrecipedagger.R
import bpn.com.foodrecipedagger.di.FoodRecipeApplication
import bpn.com.foodrecipedagger.ui.adapter.CallBackAdapter
import bpn.com.foodrecipedagger.ui.adapter.RecipeRecyclerAdapter
import bpn.com.foodrecipedagger.ui.viewmodel.RecipeListViewModel
import bpn.com.foodrecipedagger.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_launcher.*
import kotlinx.android.synthetic.main.activity_recipe_list.*
import javax.inject.Inject

class RecipeListFragment : Fragment(R.layout.activity_recipe_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var listViewModel: RecipeListViewModel
    private lateinit var recipeRecyclerAdapter: RecipeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FoodRecipeApplication.instance.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RecipeListViewModel::class.java)
        listViewModel.searchRecipe()
        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            recipeRecyclerAdapter =
                RecipeRecyclerAdapter(
                    object : CallBackAdapter {
                        override fun <T> onItemClicked(item: T) {
                            val bundle = Bundle()
                            bundle.putParcelable(
                                "recipe",
                                recipeRecyclerAdapter.getSelectedRecipe(item)
                            )
                            NavHostFragment.findNavController(host_fragment).navigate(
                                R.id.action_recipeListFragment_to_recipeDetailFragment,
                                bundle
                            )
                        }

                    }
                )
            adapter = recipeRecyclerAdapter
        }
    }

    private fun observeData() {
        listViewModel.recipeItem_.observe(viewLifecycleOwner, Observer { listOfResource ->
            recipeRecyclerAdapter.setRecipes(listOfResource)

        })

        listViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        listViewModel.isEmpty.observe(viewLifecycleOwner, Observer {
            if (it == true) {
            }
        })
    }
}