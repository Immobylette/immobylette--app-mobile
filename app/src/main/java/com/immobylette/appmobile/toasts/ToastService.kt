package com.immobylette.appmobile.toasts

import android.app.Activity
import androidx.core.content.res.ResourcesCompat
import com.immobylette.appmobile.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

object ToastService {
    init {
        MotionToast.setYOffset(20)
    }

    val successStyle = MotionToastStyle.SUCCESS
    val errorStyle = MotionToastStyle.ERROR

    private const val toastErrorTitle = "Erreur !";
    private const val toastErrorMessage = "Erreur survenue avec le serveur !"

    private val _toastErrorEvent = MutableSharedFlow<ToastEvent>()
    val toastErrorEvent = _toastErrorEvent.asSharedFlow()

    suspend fun showToastError() {
        _toastErrorEvent.emit(ToastEvent(toastErrorTitle, toastErrorMessage, errorStyle))
    }

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
            ResourcesCompat.getFont(activity, R.font.roboto_regular),
        )
    }
}

data class ToastEvent(
    val title: String,
    val message: String,
    val type: MotionToastStyle
)