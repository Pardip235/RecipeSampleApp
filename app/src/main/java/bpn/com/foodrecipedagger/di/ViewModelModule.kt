package bpn.com.foodrecipedagger.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bpn.com.foodrecipedagger.ui.viewmodel.RecipeListViewModel
import bpn.com.foodrecipedagger.ui.viewmodel.RecipeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RecipeListViewModel::class)
    abstract fun bindRecipeViewModel(recipeListViewModel: RecipeListViewModel):ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RecipeDetailViewModel::class)
    abstract fun bindRecipeDetailViewModel(recipeDetailViewModel: RecipeDetailViewModel): ViewModel
}