package ru.zkv.covid19app.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(itemId: Int): View =
    LayoutInflater.from(context).inflate(itemId, this, false)

fun <T> MutableCollection<T>.surrogateAll(elements: Collection<T>) {
    clear()
    addAll(elements)
}