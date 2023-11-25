package com.accenture.composegenerics

fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
        where T : CharSequence,
              T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
fun <T> List<T>?.filterOrOriginal(predicate: (T) -> Boolean) : List<T>? =
    this?.filter(predicate).takeUnless { it.isNullOrEmpty() } ?: this
fun <T,T1,T2> List<T>?.filterBy(list: List<T>, query: T1, property: T2) : List<T>
        where T1 : String,
              T2 : String {
    val (p1, p2) = list.partition { property.startsWith(query, true) }
    val p3 = p2.let { it.filter { t1 -> property.contains(query, true) } }
    return p1.plus(p3).map { it }
}