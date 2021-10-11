package com.coderio.pocmvvmandroid.main.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.main.products.model.ProductData

class ProductsAdapter(
        private val products: List<ProductData>,
        private val onProductClickListener: OnProductClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolderProducts>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProducts {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_product, parent, false)
        return ViewHolderProducts(view, onProductClickListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolderProducts, position: Int) {
        holder.addProduct(products[position])
    }

    class ViewHolderProducts(itemView: View, private val onProductClickListener: OnProductClickListener) : RecyclerView.ViewHolder(itemView) {
        private val productId: TextView = itemView.findViewById(R.id.idValueTextView)
        private val productName: TextView = itemView.findViewById(R.id.nameValueTextView)
        private val productDescription: TextView = itemView.findViewById(R.id.descriptionValueTextView)
        private val productPrice: TextView = itemView.findViewById(R.id.priceValueTextView)
        private val productQuantity: TextView = itemView.findViewById(R.id.quantityValueTextView)

        fun addProduct(product: ProductData) {
            productId.text = product.id
            productName.text = product.name
            productDescription.text = product.description
            productPrice.text = product.unitPrice.toString()
            productQuantity.text = product.quantity.toString()

            itemView.setOnClickListener { onProductClickListener.onProductClick(product) }
        }
    }

    interface OnProductClickListener {
        fun onProductClick(product: ProductData)
    }
}