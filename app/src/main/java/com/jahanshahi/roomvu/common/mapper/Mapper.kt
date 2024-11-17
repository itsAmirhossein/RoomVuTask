package com.jahanshahi.roomvu.common.mapper

interface Mapper<First, Second> {
    fun map(first: First): Second
}
interface SuspendMapper<First, Second> {
    suspend fun map(first: First): Second
}