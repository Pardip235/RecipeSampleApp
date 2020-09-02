package bpn.com.foodrecipedagger.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import bpn.com.foodrecipedagger.R
import bpn.com.foodrecipedagger.models.Recipe
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_recipe_list_item.view.*
import kotlin.math.roundToInt


class RecipeListViewHolder(itemView: View, private val callback: CallBackAdapter): RecyclerView.ViewHolder(itemView), View.OnClickListener{
    private val title  = itemView.findViewById<TextView>(R.id.recipe_title)
    private val publisher: TextView = itemView.recipe_publisher
    private val socialScore: TextView = itemView.recipe_social_score
    private val image: AppCompatImageView = itemView.findViewById(R.id.recipe_image)

    fun onBind(recipe: Recipe){
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(recipe.image_url)
            .into(image)
        title.text = recipe.title
        publisher.text = recipe.publisher
        socialScore.text = recipe.social_rank.roundToInt().toString()
    }
    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        callback.onItemClicked(adapterPosition)
    }
}