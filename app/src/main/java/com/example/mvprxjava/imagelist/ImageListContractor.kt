package com.example.mvprxjava.imagelist

import com.example.mvprxjava.data.ImageModel

interface IView {
    fun loadList(List: List<ImageViewItem>)
    fun onError(message: String)
    fun showLoading()
    fun hideLoading()
    fun onItemClick(imageModel: ImageModel)
}

interface IPresenter {
    fun init(view: IView)
    fun onDestroy()
    fun loadData()
}