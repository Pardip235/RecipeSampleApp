package bpn.com.foodrecipedagger.repo

import bpn.com.foodrecipedagger.models.Recipe
import bpn.com.foodrecipedagger.models.RecipeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteService {

   // https://recipesapi.herokuapp.com/api/search?q=chicken&page=3

    @GET("/api/search/")
    fun getRecipeItem(@Query("q") query: String, @Query("page") pageNumber: Int): Single<RecipeResponse>

    @GET("/api/get/")
    fun getRecipeDetail(@Query("rId") recipeId: String): Single<Recipe>


    //https://recipesapi.herokuapp.com/api/get?rId=1033dd

    //https://recipesapi.herokuapp.com/api/get?rId=49421
}