package com.maorbarak.simple_compose_navigation.processor

import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSValueArgument
import com.google.devtools.ksp.symbol.Modifier

// return one annotation object from a list of annotations or throw error
fun Sequence<KSAnnotation>.getAnnotation(target: String): KSAnnotation {
    return getAnnotationIfExist(target) ?:
    throw NoSuchElementException("Sequence contains no element matching the predicate.")
}

// return null or one annotation object from a list of annotations
fun Sequence<KSAnnotation>.getAnnotationIfExist(target: String): KSAnnotation? {
    for (element in this) if (element.shortName.asString() == target) return element
    return null
}

// return true if an object has a specific annotation
fun Sequence<KSAnnotation>.hasAnnotation(target: String): Boolean {
    for (element in this) if (element.shortName.asString() == target) return true
    return false
}

// return the parameter's value from an annotation object or throw error
fun <T> List<KSValueArgument>.getParameterValue(target: String): T {
    return getParameterValueIfExist(target) ?:
    throw NoSuchElementException("Sequence contains no element matching the predicate.")
}

// return null or the parameter's value from an annotation object
fun <T> List<KSValueArgument>.getParameterValueIfExist(target: String): T? {
    for (element in this) if (element.name?.asString() == target) (element.value as? T)?.let { return it }
    return null
}

// return true if a particular modifier is included in a list
fun Collection<Modifier>.containsIgnoreCase(name: String): Boolean {
    return stream().anyMatch { it.name.equals(name, true) }
}