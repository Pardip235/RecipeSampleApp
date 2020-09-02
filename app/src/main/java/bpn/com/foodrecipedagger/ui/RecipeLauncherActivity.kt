package bpn.com.foodrecipedagger.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import bpn.com.foodrecipedagger.R
import kotlinx.android.synthetic.main.activity_launcher.*


class RecipeLauncherActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

    }
    override fun onSupportNavigateUp() =
        NavHostFragment.findNavController(host_fragment).navigateUp()

}