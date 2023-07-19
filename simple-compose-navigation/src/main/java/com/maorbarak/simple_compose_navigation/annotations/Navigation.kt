package com.maorbarak.simple_compose_navigation.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Navigation(
    val firstRoute: String
) {
    companion object {
        const val firstRoute = "firstRoute"
    }
}