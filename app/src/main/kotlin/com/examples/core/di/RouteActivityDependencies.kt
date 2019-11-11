package com.examples.core.di

import com.examples.core.presentation.navigation.Navigator

interface RouteActivityDependencies {
  fun navigator(): Navigator

}