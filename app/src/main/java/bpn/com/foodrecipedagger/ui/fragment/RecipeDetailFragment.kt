package bpn.com.foodrecipedagger.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bpn.com.foodrecipedagger.R
import bpn.com.foodrecipedagger.di.FoodRecipeApplication
import bpn.com.foodrecipedagger.models.Recipe
import bpn.com.foodrecipedagger.ui.viewmodel.RecipeDetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_recipe.*
import javax.inject.Inject
import kotlin.math.roundToInt

class RecipeDetailFragment: Fragment(R.layout.activity_recipe) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val TAG = "RecipeActivity"
    private lateinit var recipe: Recipe
    private var disposables : CompositeDisposable = CompositeDisposable()
    private lateinit var viewModel: RecipeDetailViewModel
    private lateinit var mRecipeIngredientsContainer : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FoodRecipeApplication.instance.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeDetailViewModel::class.java)
        getRecipeItem()
        ObserveRecipe()
    }

    private fun getRecipeItem(){
        val recipe = requireActivity().intent.getParcelableExtra<Recipe>("recipe")
        if(recipe != null){
            viewModel.searchRecipe(49421.toString())
            //49421
        }
    }
    private fun ObserveRecipe(){
        viewModel.recipe.observe(viewLifecycleOwner, Observer { recipe ->
            setRecipeProperties(recipe)
        })
    }

    private fun setRecipeProperties(recipe: Recipe?){
        if(recipe != null){
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background)
            Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(recipe.image_url)
                .into(recipe_image)
            recipe_title.text = recipe.title
            recipe_publisher.text = "Publisher: ${recipe.publisher}"
            recipe_social_score.text= recipe.social_rank.roundToInt().toString()
            if (recipe.ingredients != null) {
                setIngredient(recipe.ingredients)
            }
        }
    }
    private fun setIngredient(ingredients: List<String>?){
        Observable.fromArray(*ingredients as Array<out String>)
            .filter{
                it.isNotBlank()
            }
            .distinct()
            .map { it -> "*$it" }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<String> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onNext(t: String) {
                    setIngredients(t, false)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "Error with setting ingredients using Rx: ${e.message}")
                    setIngredients(null, true)
                }
            })
    }
    private fun setIngredients(recipeItem : String?, error : Boolean) {
        val textView  = TextView(requireContext())
        if(error) {
            textView.text = "Error retrieving ingredients.\n Check network connection"
        } else {
            textView.text = recipeItem
        }
        textView.textSize = 15F
        textView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mRecipeIngredientsContainer.addView(textView)
    }
}