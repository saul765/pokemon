package com.ba.pokedex.utils.views

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import com.ba.pokedex.R
import com.ba.pokedex.utils.livedata.Event


class LoadingView : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() = inflate(context, R.layout.view_loading, this)

    fun onLoadingResponse(event: Event<Boolean>, window: Window?) {
        when (event) {
            is Event.Success -> onLoadingResponseSuccess(event.data, window)
            is Event.Failure -> Unit
        }
    }

    private fun onLoadingResponseSuccess(event: Boolean, window: Window?) {
        if (event)
            showLoading(window)
        else
            hideLoading()
    }

    fun showLoading(window: Window?) {
        if (parent == null) {
            window?.addContentView(
                this,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }
    }

    fun hideLoading() {
        parent?.let { (it as ViewGroup).removeView(this) }
    }

}