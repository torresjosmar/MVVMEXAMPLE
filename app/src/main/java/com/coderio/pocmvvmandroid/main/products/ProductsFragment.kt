package com.coderio.pocmvvmandroid.main.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.common.Outcome
import com.coderio.pocmvvmandroid.common.TOKEN
import com.coderio.pocmvvmandroid.common.dialogs.base.BaseBottomSheetDialog
import com.coderio.pocmvvmandroid.common.extentions.hide
import com.coderio.pocmvvmandroid.common.extentions.show
import com.coderio.pocmvvmandroid.main.products.adapter.ProductsAdapter
import com.coderio.pocmvvmandroid.main.products.model.ProductData
import com.coderio.pocmvvmandroid.main.products.model.ProductDetailResponse
import com.coderio.pocmvvmandroid.main.products.viewmodel.ProductsViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_products.*
import javax.inject.Inject

class ProductsFragment: BaseBottomSheetDialog() {
    companion object {
        fun newInstance(token: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString(TOKEN, token)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ProductsViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductsViewModel::class.java)
        lifecycle.addObserver(viewModel)
        listenToObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainContainer?.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getScreenHeight())
    }

    private fun listenToObserver() {
        viewModel.outcome.observe(this, Observer {outcome ->
            when(outcome){
                is Outcome.Success -> onSuccess(outcome.data)
                is Outcome.Failure -> onError(outcome.e)
                else -> { }
            }
        })
    }

    private fun onSuccess(data: ProductsActions) {
        when (data) {
            is ProductsActions.OnProductsObtained -> {
                if (data.productsList.isNotEmpty()) {
                    populateRecyclerView(data.productsList.filterNotNull())
                }
            }
            is ProductsActions.OnProductDetailFound -> showProductDetail(data.detail)
        }
    }

    private fun populateRecyclerView(products: List<ProductData>) {
        recyclerView.run {
            adapter = ProductsAdapter(products, onProductClick)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onError(error: Throwable) {
        error.printStackTrace()
        progressbarContainer.hide()
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    private val onProductClick = object: ProductsAdapter.OnProductClickListener {
        override fun onProductClick(product: ProductData) {
            progressbarContainer.show()
            viewModel.getProductDetail(product.id)
        }
    }

    private fun showProductDetail(data: ProductDetailResponse) {

    }
}