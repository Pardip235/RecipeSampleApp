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

class RecipeListViewModel @Inject constructor(private val repository: RemoteRepo) : ViewModel() {
    private val disposable = CompositeDisposable()
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val recipeItem: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipeItem_: LiveData<List<Recipe>>
        get() = recipeItem


    fun searchRecipe() {
       disposable.add(
           repository.searchRecipeList("chicken",3)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe({ data ->
                   if (data.recipe.isEmpty()) {
                       isEmpty.postValue(true)
                   } else {
                       recipeItem.postValue(data.recipe)
                   }
                   //data.recipes
               }, { errorMessage.postValue(it.message) })
       )
    }
}





