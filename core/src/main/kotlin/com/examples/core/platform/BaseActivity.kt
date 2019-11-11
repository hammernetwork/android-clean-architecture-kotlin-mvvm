package com.examples.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.examples.core.R
import com.examples.core.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_layout)
    setSupportActionBar(toolbar)
    addFragment(savedInstanceState)
  }

  override fun onBackPressed() {
    (supportFragmentManager.findFragmentById(
        R.id.fragmentContainer) as BaseFragment).onBackPressed()
    super.onBackPressed()
  }

  private fun addFragment(savedInstanceState: Bundle?) =
      savedInstanceState ?: supportFragmentManager.inTransaction {
        add(
            R.id.fragmentContainer, fragment())
      }

  abstract fun fragment(): BaseFragment

  fun getToolbar(): Toolbar = toolbar
}
