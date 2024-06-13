package com.ba.pokedex.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ba.pokedex.utils.livedata.Event
import com.ba.pokedex.utils.views.LoadingView
import org.koin.core.component.KoinComponent

abstract class BaseFragment<DB : ViewDataBinding> : Fragment(), IBaseView, KoinComponent {

    // Data binding
    lateinit var dataBinding: DB

    // Generic Loading View
    protected val isLoadingObserver by lazy {
        Observer<Event<Boolean>> {
            loadingView.onLoadingResponse(
                it,
                activity?.window
            )
        }
    }
    private val loadingView by lazy { LoadingView(getViewContext()) }

    // View Context
    private lateinit var fragmentContext: Context

    abstract fun getLayoutId(): Int
    abstract fun getVariablesToBind(): Map<Int, Any>
    abstract fun initObservers()

    @CallSuper
    open fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        dataBinding.lifecycleOwner = this
        for ((variableId, value) in getVariablesToBind()) {
            dataBinding.setVariable(variableId, value)
        }
        dataBinding.executePendingBindings()
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        super.onViewCreated(view, savedInstanceState)

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initObservers()
        initView(inflater, container)
        return dataBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }

    override fun isActive(): Boolean = isAdded


    override fun showAlert(textResource: Int) {
        activity?.let {
            if (isActive() && !it.isFinishing) {
                AlertDialog.Builder(getViewContext())
                    .setMessage(textResource)
                    .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .create().show()
            }
        }
    }

    override fun showAlert(text: String) {
        activity?.let {
            if (isActive() && !it.isFinishing) {
                AlertDialog.Builder(getViewContext())
                    .setMessage(text)
                    .setNeutralButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .create().show()
            }
        }
    }

    override fun getViewContext(): Context = fragmentContext

    override fun showLoading() = loadingView.showLoading(activity?.window)

    override fun hideLoading() = loadingView.hideLoading()


}