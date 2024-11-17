package com.jahanshahi.roomvu.domain.core.usecase

import com.sample.domain.core.entity.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<Resource<R>> {
        return try {
            execute(parameters)
                .catch { e -> emit(Resource.Error(Exception(e))) }
                .flowOn(coroutineDispatcher)
        } catch (e: Exception) {
            flow {
                emit(Resource.Error(e))
            }
        }
    }

    protected abstract fun execute(parameters: P): Flow<Resource<R>>
}
