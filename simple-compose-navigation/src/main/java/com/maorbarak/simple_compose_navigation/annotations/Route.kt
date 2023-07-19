package com.maorbarak.simple_compose_navigation.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class Route(
    val name: String
) {
    companion object {
        const val name = "name"
    }
}