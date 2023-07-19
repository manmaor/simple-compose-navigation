package com.maorbarak.simple_compose_navigation.navigation

interface NavigationDirections {
    val route: String

    companion object {
        const val ROUTE_PROPERTY_NAME = "route"
    }
}