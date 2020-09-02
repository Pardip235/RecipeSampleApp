package bpn.com.foodrecipedagger.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bpn.com.foodrecipedagger.models.Recipe
import bpn.com.foodrecipedagger.repo.RemoteRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(val repository: RemoteRepo): ViewModel(){

    private val disposable = CompositeDisposable()
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val recipe_: MutableLiveData<Recipe> = MutableLiveData()
    val recipe: LiveData<Recipe>
        get() = recipe_

    fun searchRecipe(recipeId: String){
       disposable.add(repository.retriveRecipeDetail(recipeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                recipe_.postValue(data)
                println("recipe data " +data)
            }, { isEmpty.postValue(true) })
        )
    }

}

