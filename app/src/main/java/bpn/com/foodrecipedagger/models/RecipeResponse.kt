package bpn.com.foodrecipedagger.models

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipes")
    val recipe: List<Recipe>
)