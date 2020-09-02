package bpn.com.foodrecipedagger.repo


import bpn.com.foodrecipedagger.models.Recipe
import bpn.com.foodrecipedagger.models.RecipeResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepo @Inject constructor(private val remoteService: RemoteService){
   fun searchRecipeList(query: String, pageNumber: Int): Single<RecipeResponse> {
        return remoteService.getRecipeItem(query, pageNumber)
    }

    fun retriveRecipeDetail(rId: String): Single<Recipe>{
        return remoteService.getRecipeDetail(rId)
    }

}