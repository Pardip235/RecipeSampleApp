package bpn.com.foodrecipedagger.di

import bpn.com.foodrecipedagger.ui.fragment.RecipeDetailFragment
import bpn.com.foodrecipedagger.ui.fragment.RecipeListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class]
)
interface ApplicationComponent {
    fun inject(recipeListFragment: RecipeListFragment)

    fun inject(recipeDetailFragment: RecipeDetailFragment)
}