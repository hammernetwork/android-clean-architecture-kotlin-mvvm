package com.examples.di.module

import com.examples.core.presentation.navigation.Navigator
import com.examples.core.presentation.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

  @Binds
  fun bindNavigator(impl: NavigatorImpl): Navigator

}