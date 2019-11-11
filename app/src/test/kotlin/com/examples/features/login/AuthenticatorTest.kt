package com.examples.features.login

import com.examples.UnitTest
import com.examples.auth.presentation.Authenticator
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

  private val authenticator = Authenticator()

  @Test
  fun `returns default value`() {
    authenticator.userLoggedIn() shouldBe true
  }
}
