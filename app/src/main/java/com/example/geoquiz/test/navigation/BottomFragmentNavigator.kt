package com.example.geoquiz.test.navigation

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator

/**
 *
 *
 * @author TXZ
 * @version 1.0
 * created by 2024/5/29 18:28
 */
class BottomFragmentNavigator(context: Context, fragmentManager: FragmentManager, containerId: Int) : FragmentNavigator(
    context,
    fragmentManager,
    containerId
) {
    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        super.navigate(entries, navOptions, navigatorExtras)
    }


}