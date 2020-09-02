package bpn.com.foodrecipedagger.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bpn.com.foodrecipedagger.R
import bpn.com.foodrecipedagger.models.Recipe

class RecipeRecyclerAdapter( private val itemCallback: CallBackAdapter): RecyclerView.Adapter<RecipeListViewHolder>() {
    private val TAG = "RecipeRecyclerAdapter"
    private var mRecipes: ArrayList<Recipe>? = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
       return RecipeListViewHolder(
           LayoutInflater.from(parent.context)
               .inflate(R.layout.layout_recipe_list_item, parent, false), itemCallback
       )
    }

    override fun onBindViewHolder(holderList: RecipeListViewHolder, position: Int) {
        mRecipes?.get(position)?.let { holderList.onBind(it) }
    }
    override fun getItemCount(): Int {
        if(mRecipes != null){
            return mRecipes!!.size
        }
        return 0
    }

    fun getSelectedRecipe(position: Any?) : Recipe? {
        if (mRecipes != null && mRecipes!!.size > 0) {
            return mRecipes!![position as Int]
        }
        return null
    }

    fun setRecipes(recipes: List<Recipe>){
        mRecipes = recipes as ArrayList<Recipe>
        notifyDataSetChanged()
    }
}