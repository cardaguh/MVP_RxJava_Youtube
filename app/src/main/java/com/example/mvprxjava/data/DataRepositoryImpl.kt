package com.example.mvprxjava.data

import io.reactivex.rxjava3.core.Single

class DataRepositoryImpl: DataRepository {

    override fun getImages(): Single<List<ImageModel>> = Single.create {
        val dataList = ArrayList<ImageModel>()
        for (id in 1 until 200) {
            dataList.add(
                ImageModel(
                    id, "https://picsum" +
                            ".photos/200/300?random=$id"
                )
            )
        }
        it.onSuccess(dataList)
    }
}