package com.example.mvprxjava.data

import io.reactivex.rxjava3.core.Single

interface DataRepository {
    fun getImages(): Single<List<ImageModel>>
}