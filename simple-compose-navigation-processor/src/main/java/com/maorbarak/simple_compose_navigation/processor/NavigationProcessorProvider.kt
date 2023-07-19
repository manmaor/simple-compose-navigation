package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated

class NavigationProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return NavigationSymbolProcessor(
            environment.codeGenerator,
            environment.logger,
            environment.options
        )
    }
}

