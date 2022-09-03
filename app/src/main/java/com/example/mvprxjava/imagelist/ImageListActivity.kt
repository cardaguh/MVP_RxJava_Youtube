package com.example.mvprxjava.imagelist

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvprxjava.R
import com.example.mvprxjava.data.DataRepository
import com.example.mvprxjava.data.ImageModel
import com.example.mvprxjava.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import eu.davidea.flexibleadapter.FlexibleAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ImageListActivity : AppCompatActivity(), IView {

    @Inject
    lateinit var repository: DataRepository

    private lateinit var presenter: ImageListPresenter

    lateinit var adapter: FlexibleAdapter<ImageViewItem>

    private lateinit var progressDialog: ProgressDialog

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")

        adapter = FlexibleAdapter<ImageViewItem>(emptyList())
        val recyclerView = binding.rvImageList
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenter = ImageListPresenter(repository)
        presenter.init(this)

        presenter.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
        presenter.onDestroy()
    }

    override fun loadList(list: List<ImageViewItem>) {
        adapter.updateDataSet(list)
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.hide()
    }

    override fun onItemClick(imageModel: ImageModel) {
        Toast.makeText(this, "clicked item ${imageModel.id}", Toast.LENGTH_SHORT).show()
    }
}