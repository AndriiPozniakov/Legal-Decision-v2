package com.hazymoonstudio.legaldecision.presentation.splash_screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hazymoonstudio.legaldecision.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_single_fragment.*
import javax.inject.Inject


@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    @Inject
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        hideNavigation()

        Handler(Looper.getMainLooper()).postDelayed({
            if (mAuth.currentUser != null) {
                findNavController().navigate(R.id.action_splashScreenFragment_to_articlesListFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_authenticationFragment)
            }
        }, 1000)

        return view
    }

    private fun hideNavigation() {
        requireActivity().bottomNavigationMenu.visibility = View.GONE
        requireActivity().app_bar_layout.visibility = View.GONE
    }
}