package com.mn.core.architecture.domain.usecase

interface UseCase<out T> {
    operator fun invoke(): T
}