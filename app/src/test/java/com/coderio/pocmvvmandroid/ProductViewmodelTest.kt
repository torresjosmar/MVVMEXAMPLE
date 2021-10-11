package com.coderio.pocmvvmandroid

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coderio.pocmvvmandroid.main.products.controller.ProductsController
import com.coderio.pocmvvmandroid.main.products.model.GetProductsResponse
import com.coderio.pocmvvmandroid.main.products.repository.ProductsRepository
import com.coderio.pocmvvmandroid.main.products.viewmodel.ProductsViewModel
import com.coderio.pocmvvmandroid.mockdata.FakeEmptyProductService
import com.coderio.pocmvvmandroid.mockdata.FakeErrorProductService
import com.coderio.pocmvvmandroid.mockdata.FakeProductService
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


const val USER = "TEST"
const val PASSWORD = "TEST"

class ProductViewmodelTest {

    @Mock
    private lateinit var context: Application

    private lateinit var subject: ProductsViewModel

    private lateinit var onSuccessObserver: Observer<List<GetProductsResponse>>
    private lateinit var onErrorObserver: Observer<Any>
    private lateinit var emptyListObserver: Observer<Boolean>

    private val fakeProductsService = FakeProductService()
    private val fakeEmptyProductsService = FakeEmptyProductService()
    private val fakeErrorProductsService = FakeErrorProductService()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(context.applicationContext).thenReturn(context)

        setupObservers()
    }

    @Test
    fun `retrieve products with ViewModel and Repository returns a product`() {
        subject = ProductsViewModel(
            "test", ProductsController(
                ProductsRepository(FakeProductService())
            )
        )

        with(subject) {
            loadProducts("")
        }

        val response = fakeProductsService.getProducts("test")
        Assert.assertNotNull(subject.outcome)
        Assert.assertTrue(response is Single<GetProductsResponse>)
    }

    @Test
    fun `retrieve products with ViewModel and Repository returns a emptyList`() {
        subject = ProductsViewModel(
            "", ProductsController(
                ProductsRepository(FakeEmptyProductService())
            )
        )

        with(subject) {
            subject.loadProducts("")
            isEmptyList.observeForever(emptyListObserver)
        }

        val response = fakeEmptyProductsService.getProducts("test")
        Assert.assertNotNull(subject.isEmptyList.value)
        Assert.assertTrue(subject.isEmptyList.value == true)
    }


    @Test
    fun `retrieve products with ViewModel and Repository returns an error`() {
        subject = ProductsViewModel(
            "test", ProductsController(
                ProductsRepository(FakeErrorProductService())
            )
        )

        with(subject) {
            getProductDetail("test")
        }

        val response = fakeErrorProductsService.getProductDetail("test", "test")
        //Assert.assertNotNull(subject.onMessageError.value)
    }

    private fun setupObservers() {
        emptyListObserver = mock(Observer::class.java) as Observer<Boolean>
    }


}