package com.coderio.pocmvvmandroid.common.dialogs

import androidx.fragment.app.FragmentManager
import com.coderio.pocmvvmandroid.common.CommunicationActivity
import com.coderio.pocmvvmandroid.common.dialogs.base.BaseBottomSheetDialog
import com.coderio.pocmvvmandroid.main.products.ProductsFragment
import com.coderio.pocmvvmandroid.registration.LoginFragment

class FragmentsController(
        private val activity: CommunicationActivity
) {
    private val supportFragmentManager: FragmentManager
        get() = activity.supportFragmentManager

    fun showLoginFragment() {
        showDialog(LoginFragment.newInstance())
    }

    fun showProductsFragment(token: String) {
        showDialog(ProductsFragment.newInstance(token))
    }

    private fun showDialog(dialog: BaseBottomSheetDialog) {
        dialog.show(supportFragmentManager, dialog.getDialogTag())
    }
}