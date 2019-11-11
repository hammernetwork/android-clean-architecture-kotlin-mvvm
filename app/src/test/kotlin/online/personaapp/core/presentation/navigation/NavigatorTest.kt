package online.personaapp.core.presentation.navigation

import com.nhaarman.mockito_kotlin.whenever
import online.personaapp.AndroidTest
import online.personaapp.auth.presentation.Authenticator
import online.personaapp.auth.presentation.LoginActivity
import online.personaapp.core.presentation.RouteActivity
import online.personaapp.movies.presentation.view.MoviesActivity
import online.personaapp.shouldNavigateTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify


class NavigatorTest : AndroidTest() {

  private lateinit var navigator: Navigator

  @Mock
  private lateinit var authenticator: Authenticator

  @Before
  fun setup() {
    navigator = NavigatorImpl(authenticator)
  }

  @Test
  fun `should forward user to login screen`() {
    whenever(authenticator.userLoggedIn()).thenReturn(false)

    navigator.showMain(activityContext())

    verify(authenticator).userLoggedIn()
    RouteActivity::class shouldNavigateTo LoginActivity::class
  }

  @Test
  fun `should forward user to movies screen`() {
    whenever(authenticator.userLoggedIn()).thenReturn(true)

    navigator.showMain(activityContext())

    verify(authenticator).userLoggedIn()
    RouteActivity::class shouldNavigateTo MoviesActivity::class
  }
}
