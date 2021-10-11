package com.coderio.pocmvvmandroid.main.products.viewmodel

import androidx.lifecycle.*
import com.coderio.pocmvvmandroid.common.Outcome
import com.coderio.pocmvvmandroid.common.extentions.applySchedulers
import com.coderio.pocmvvmandroid.common.extentions.plusAssign
import com.coderio.pocmvvmandroid.main.products.ProductsActions
import com.coderio.pocmvvmandroid.main.products.controller.ProductsController
import com.coderio.pocmvvmandroid.main.products.model.ProductData
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val token: String,
    private val productsController: ProductsController
) : ViewModel(), LifecycleObserver {


    val outcome by lazy { MutableLiveData<Outcome<ProductsActions>>() }
    private val disposable = CompositeDisposable()


    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList


    private val _onMessageError = MutableLiveData<Any>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadProducts(token)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        disposable.dispose()
    }

    fun getProductDetail(productId: String) {
        disposable += productsController.getProductDetail(token, productId)
            .applySchedulers()
            .subscribe({
                outcome.postValue(Outcome.success(ProductsActions.OnProductDetailFound(it)))
            }, {
                _onMessageError.value = true
                outcome.postValue(Outcome.failure(it))
            })
    }

    fun loadProducts(token: String) {
        disposable += productsController.getProducts(token)
            .applySchedulers()
            .subscribe({
                outcome.postValue(
                    Outcome.success(
                        onProductData(it.productsData)
                    )
                )
            }, {
                _onMessageError.value = true
                outcome.postValue(Outcome.failure(it))
            })
    }

    private fun onProductData(productsData: List<ProductData?>): ProductsActions {
        if (productsData.isEmpty()) {
            _isEmptyList.value = true
            return ProductsActions.OnProductsObtained(productsData)
        } else {
            ProductsActions.OnProductsObtained(productsData)
        }
        return ProductsActions.OnProductsObtained(productsData)
    }
}