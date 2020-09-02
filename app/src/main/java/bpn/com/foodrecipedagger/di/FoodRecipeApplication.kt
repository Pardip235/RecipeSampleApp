package bpn.com.foodrecipedagger.di

import androidx.multidex.MultiDexApplication

class FoodRecipeApplication : MultiDexApplication()  {
    companion object{
        private lateinit var app: FoodRecipeApplication

        @JvmStatic
        val instance
        get() = app
    }
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        app = this
    }
}