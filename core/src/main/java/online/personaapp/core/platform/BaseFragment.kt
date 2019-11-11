package online.personaapp.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.toolbar.*
import online.personaapp.core.R
import online.personaapp.core.extension.appContext
import online.personaapp.core.extension.viewContainer

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

  abstract fun layoutId(): Int

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View =
      inflater.inflate(layoutId(), container, false)

  open fun onBackPressed() {}

  protected fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

  protected fun showProgress() = progressStatus(View.VISIBLE)

  protected fun hideProgress() = progressStatus(View.GONE)

  private fun progressStatus(viewStatus: Int) =
      with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }

  protected fun notify(@StringRes message: Int) =
      Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

  protected fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int,
      action: () -> Any) {
    with(Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)) {
      setAction(actionText) { action.invoke() }
      setActionTextColor(ContextCompat.getColor(appContext, R.color.colorTextPrimary))
      show()
    }
  }
}
