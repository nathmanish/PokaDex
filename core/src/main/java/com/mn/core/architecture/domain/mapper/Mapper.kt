package com.mn.core.architecture.domain.mapper

interface Mapper<in F, out T> {
    operator fun invoke(from: F): T
}