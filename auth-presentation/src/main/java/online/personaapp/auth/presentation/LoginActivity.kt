package online.personaapp.auth.presentation

import android.content.Context
import android.content.Intent
import online.personaapp.core.platform.BaseActivity

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
class LoginActivity : BaseActivity() {
  companion object {
    fun callingIntent(context: Context) = Intent(context, LoginActivity::class.java)
  }

  override fun fragment() = LoginFragment()
}
