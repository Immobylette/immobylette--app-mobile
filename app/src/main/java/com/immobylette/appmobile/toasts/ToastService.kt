package com.immobylette.appmobile.toasts

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import com.immobylette.appmobile.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object ToastService {

    init {
        MotionToast.setYOffset(20)
    }

    val successStyle = MotionToastStyle.SUCCESS
    val errorStyle = MotionToastStyle.ERROR
    val warningStyle = MotionToastStyle.WARNING

    fun showToast(
        activity: Activity,
        title: String,
        message: String,
        type: MotionToastStyle,
    ) {
        MotionToast.createColorToast(
            activity,
            title,
            message,
            type,
            MotionToast.GRAVITY_TOP,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(activity, R.font.helvetica_regular)
        )
    }
}