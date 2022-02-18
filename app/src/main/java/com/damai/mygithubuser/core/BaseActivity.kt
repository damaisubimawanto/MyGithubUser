package com.damai.mygithubuser.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.damai.mygithubuser.BR

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val layoutResourceId: Int
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutIfDefined()
    }

    private fun setLayoutIfDefined() {
        if (this is ViewDataBindingOwner<*>) {
            setContentViewBinding(
                activity = this,
                layoutResId = layoutResourceId
            )
            binding.setVariable(BR.vm, viewModel)
            binding.lifecycleOwner = this
            if (this is BaseView) {
                binding.setVariable(BR.view, this)
            }
        } else {
            setContentView(layoutResourceId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is ViewDataBindingOwner<*>) {
            clearDataBinding()
        }
    }
}