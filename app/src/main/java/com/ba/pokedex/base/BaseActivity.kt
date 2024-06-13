package com.ba.pokedex.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ba.pokedex.utils.livedata.Event
import com.ba.pokedex.utils.views.LoadingView
import org.koin.core.component.KoinComponent

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity(), IBaseView, KoinComponent {

    // Data binding
    lateinit var dataBinding: DB

    // Generic Loading View
    protected val isLoadingObserver by lazy {
        Observer<Event<Boolean>> {
            loadingView.onLoadingResponse(
                it,
                window
            )
        }
    }
    private val loadingView by lazy { LoadingView(this@BaseActivity) }

    abstract fun getLayoutId(): Int
    abstract fun getVariablesToBind(): Map<Int, Any>
    abstract fun initObservers()

    @CallSuper
    open fun initView() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.lifecycleOwner = this
        for ((variableId, value) in getVariablesToBind()) {
            dataBinding.setVariable(variableId, value)
        }
        dataBinding.executePendingBindings()
    }

    final override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) =
        super.onCreate(savedInstanceState, persistentState)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        initView()
    }

    override fun isActive(): Boolean = !isFinishing

    override fun showAlert(textResource: Int) {
        if (isActive()) {
            AlertDialog.Builder(this)
                .setMessage(textResource)
                .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .create().show()
        }
    }

    override fun showAlert(text: String) {
        if (isActive()) {
            AlertDialog.Builder(this)
                .setMessage(text)
                .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .create().show()
        }
    }

    override fun getViewContext(): Context = this

    override fun showLoading() = loadingView.showLoading(window)
    override fun hideLoading() = loadingView.hideLoading()


}