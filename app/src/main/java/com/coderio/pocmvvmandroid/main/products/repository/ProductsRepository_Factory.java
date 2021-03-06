// Generated by Dagger (https://dagger.dev).
package com.coderio.pocmvvmandroid.main.products.repository;

import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ProductsRepository_Factory implements Factory<ProductsRepository> {
  private final Provider<ProductsService> productsServiceProvider;

  public ProductsRepository_Factory(Provider<ProductsService> productsServiceProvider) {
    this.productsServiceProvider = productsServiceProvider;
  }

  @Override
  public ProductsRepository get() {
    return newInstance(productsServiceProvider.get());
  }

  public static ProductsRepository_Factory create(
      Provider<ProductsService> productsServiceProvider) {
    return new ProductsRepository_Factory(productsServiceProvider);
  }

  public static ProductsRepository newInstance(ProductsService productsService) {
    return new ProductsRepository(productsService);
  }
}
