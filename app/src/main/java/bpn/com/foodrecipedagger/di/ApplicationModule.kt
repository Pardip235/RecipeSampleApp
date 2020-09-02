package bpn.com.foodrecipedagger.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides fun provideApplicationContext(): Context = context

    @Provides
    @Singleton
    internal fun provideConnectivityManager(): ConnectivityManager{
        return context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}