package com.coderio.pocmvvmandroid.common.dialogs.base

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.coderio.pocmvvmandroid.R
import com.coderio.pocmvvmandroid.common.CommunicationActivity
import com.coderio.pocmvvmandroid.common.protocol.CommunicationCallback
import com.coderio.pocmvvmandroid.registration.RegistrationActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialog : BottomSheetDialogFragment() {

    lateinit var communication: CommunicationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communication = (requireActivity() as CommunicationActivity)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            (it as BottomSheetDialog).findViewById<FrameLayout>(R.id.design_bottom_sheet)?.let { layout ->
                val behavior = BottomSheetBehavior.from(layout)
                behavior.skipCollapsed = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.isHideable = false
                behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(p0: View, p1: Float) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dismiss()
                        } else {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    }
                })
            }
            it.setOnShowListener(null)
        }

        return dialog
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    fun getDialogTag(): String = this.javaClass.name

    fun getScreenHeight(): Int {
        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        val x = metrics.widthPixels
        val y = metrics.heightPixels
        return Math.max(x, y)
    }
}