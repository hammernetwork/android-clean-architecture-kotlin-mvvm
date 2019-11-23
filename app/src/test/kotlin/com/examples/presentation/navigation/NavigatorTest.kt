/**
 * Copyright (C) 2018 Andriy Se Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.examples.presentation.navigation

import com.examples.auth.presentation.Authenticator
import com.examples.auth.presentation.LoginActivity
import com.examples.core.AndroidTest
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
