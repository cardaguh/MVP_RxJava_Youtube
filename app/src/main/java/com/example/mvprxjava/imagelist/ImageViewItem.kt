package com.example.mvprxjava.imagelist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvprxjava.R
import com.example.mvprxjava.data.ImageModel
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import io.reactivex.rxjava3.subjects.Subject

class ImageViewItem(
    private val imageModel: ImageModel,
    private val onClickEventStream: Subject<ImageModel>
) :
    AbstractFlexibleItem<ImageViewItem.ItemViewHolder>() {

    override fun equals(other: Any?): Boolean {
        if (other is ImageModel) {
            return imageModel.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return imageModel.id.hashCode()
    }

    override fun getLayoutRes(): Int = R.layout.item_image

    override fun createViewHolder(
        view: View?,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ) = ItemViewHolder(view, adapter, onClickEventStream)

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: ItemViewHolder?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder?.onBind(imageModel)
    }

    class ItemViewHolder(
        view: View?, adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        private val onClickEventStream: Subject<ImageModel>
    ) : FlexibleViewHolder(view, adapter) {

        fun onBind(imageModel: ImageModel) {
            itemView.setOnClickListener {
                onClickEventStream.onNext(imageModel)
            }
            itemView.findViewById<TextView>(R.id.txtId).text =
                "${imageModel.id}"
            Glide
                .with(itemView.context)
                .load(imageModel.url)
                .into(itemView.findViewById(R.id.img))
        }

    }

}
