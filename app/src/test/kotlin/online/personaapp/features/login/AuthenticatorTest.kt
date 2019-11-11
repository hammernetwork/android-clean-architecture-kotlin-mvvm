package online.personaapp.features.login

import online.personaapp.UnitTest
import online.personaapp.auth.presentation.Authenticator
import org.amshove.kluent.shouldBe
import org.junit.Test

class AuthenticatorTest : UnitTest() {

  private val authenticator = Authenticator()

  @Test
  fun `returns default value`() {
    authenticator.userLoggedIn() shouldBe true
  }
}
