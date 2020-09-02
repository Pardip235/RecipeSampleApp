package bpn.com.foodrecipedagger.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Recipe(
    val title: String,
    val publisher: String,
    val image_url: String,
    val recipe_id: String,
    val social_rank: Float,
    var ingredients: List<String>,
    val timestamp: String) : Parcelable