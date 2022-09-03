package com.example.mvprxjava.imagelist

import com.example.mvprxjava.data.DataRepository
import com.example.mvprxjava.data.ImageModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject

class ImageListPresenter(private val repository: DataRepository) : IPresenter {
    private val disposable = CompositeDisposable()
    private val onItemClickStream = PublishSubject.create<ImageModel>()
    var view: IView? = null

    override fun init(view: IView) {
        this.view = view
        listenOnClickEvent()
    }

    override fun onDestroy() {
        view = null
        disposable.dispose()
        disposable.clear()
    }

    override fun loadData() {
        view?.showLoading()
        repository
            .getImages()
            .doOnSuccess {
                view?.loadList(mapImageModelToImageItem(it))
            }
            .doOnError {
                view?.onError(it.message.orEmpty())
            }
            .doOnTerminate {
                view?.hideLoading()
            }
            .subscribe()
            .also { disposable.add(it) }
    }

    private fun mapImageModelToImageItem(list: List<ImageModel>):
            List<ImageViewItem> =
        list.map {
            ImageViewItem(it, onItemClickStream)
        }

    private fun listenOnClickEvent() {
        onItemClickStream
            .subscribe {
                view?.onItemClick(it)
            }
            .also { disposable.add(it) }
    }


}