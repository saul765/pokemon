package com.ba.pokedex.base

import android.content.Context

interface IBaseView {

    fun isActive(): Boolean

    fun showAlert(textResource: Int)

    fun showAlert(text: String)

    fun showAlertWithTitle(title: String, text: String, okCallback: () -> Unit = {})

    fun getViewContext(): Context

    fun showLoading()

    fun hideLoading()
}