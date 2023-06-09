package com.asad.dogs.core.data.mapper

interface CustomMapper<E, M> {
    fun mapTo(entity: E): M
}
