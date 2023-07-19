package com.maorbarak.simple_compose_navigation.nested_navigation.child_b

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maorbarak.simple_compose_navigation.annotations.Navigation
import com.maorbarak.simple_compose_navigation.annotations.Route

const val CHILD_BA_ROUTE = "ChildBA"
const val CHILD_BB_ROUTE = "ChildBB"

@Navigation(CHILD_BA_ROUTE)
interface ChildBNavigation {

    @Route(CHILD_BA_ROUTE)
    fun NavGraphBuilder.childBA() = composable(CHILD_BA_ROUTE) { Box(Modifier.fillMaxSize()) { Text(text = "ChildBA", modifier = Modifier.align(Alignment.Center)) } }

    @Route(CHILD_BB_ROUTE)
    fun NavGraphBuilder.childBB() = composable(CHILD_BB_ROUTE) { Box(Modifier.fillMaxSize()) { Text(text = "ChildBB", modifier = Modifier.align(Alignment.Center)) } }
}

/*
package com.maorbarak.simple_compose_navigation.nested_navigation.child_b

import com.maorbarak.simple_compose_navigation.navigation.NavigationDirections
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavController

typealias ChildBNavigationNavController = NavController
fun ChildBNavigationNavController.navigate(direction: ChildBNavigationDestinations) {
	this.navigate(direction.route)
}

sealed class ChildBNavigationDestinations: NavigationDirections {

	object ChildBA: ChildBNavigationDestinations() {
		override val route: String = "ChildBA"
	}

	object ChildBB: ChildBNavigationDestinations() {
		override val route: String = "ChildBB"
	}

}

object ChildBNavigationImpl: ChildBNavigation {
	private val localNavController: ProvidableCompositionLocal<ChildBNavigationNavController?> = staticCompositionLocalOf<ChildBNavigationNavController?> { null }

	@Composable
	fun navHost(navController: NavHostController) {
		CompositionLocalProvider(
			localNavController provides navController
		) {
			NavHost(navController = navController, startDestination = "ChildBA") {
				childBA()
				childBB()
			}
		}
	}

	val current: ChildBNavigationNavController?
		@Composable get () = localNavController.current

}

*/