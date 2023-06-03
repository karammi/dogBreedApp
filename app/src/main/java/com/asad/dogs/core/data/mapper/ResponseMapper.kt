package com.asad.dogs.core.data.mapper

interface ResponseMapper<E, M> {
    fun mapToModel(entity: E): M
}
