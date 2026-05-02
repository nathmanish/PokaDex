package com.mn.core.networkclient

interface NetworkClient {
    fun <T> create(clientService: Class<T>): T
}