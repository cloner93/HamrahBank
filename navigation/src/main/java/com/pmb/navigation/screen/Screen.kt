package com.pmb.navigation.screen

abstract class Screen(val baseRoute: String, val arguments: Map<String, String> = emptyMap()){
    val route: String
        get() = if (arguments.isEmpty()) baseRoute
        else buildRouteWithArgs()

    val base: String
        get() = baseRoute

    private fun buildRouteWithArgs(): String {
        var routeWithArgs = baseRoute
        arguments.forEach { (key, value) ->
            routeWithArgs = routeWithArgs.replace("{$key}", value)
        }
        return routeWithArgs
    }

    fun withArgs(vararg pairs: Pair<String, String>): Screen {
        val newArgs = arguments.toMutableMap().apply {
            putAll(pairs)
        }
        return object : Screen(baseRoute, newArgs) {}
    }

    fun withQueryParams(vararg params: Pair<String, String>): String {
        return route + params.joinToString("&", prefix = "?") { "${it.first}=${it.second}" }
    }

    fun rawRoute(): String = baseRoute

    override fun toString(): String = route
}