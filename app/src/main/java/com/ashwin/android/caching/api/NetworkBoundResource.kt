package com.ashwin.android.caching.api

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,  // Get cached data from DB
    crossinline fetch: suspend () -> RequestType,  // Get new remote data from network
    crossinline saveFetchResult: suspend (RequestType) -> Unit, // Saving the fetched data in DB
    crossinline shouldFetch: (ResultType) -> Boolean = { true } // Decides whether to make a network call or not, by-default it will always fetch and update the cache
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map {
                Resource.Success(it)
            }
        } catch (throwable: Throwable) {
            query().map {
                Resource.Error(throwable, it)
            }
        }
    } else {
        query().map {
            Resource.Success(it)
        }
    }

    emitAll(flow)
}
