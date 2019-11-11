package com.examples.core.presentation.navigation

import com.examples.AndroidTest
import com.examples.auth.presentation.Authenticator
import com.examples.auth.presentation.LoginActivity
import com.examples.core.presentation.RouteActivity
import com.examples.movies.presentation.view.MoviesActivity
import com.examples.shouldNavigateTo
import com.nhaarman.mockito_kotlin.whenever
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
