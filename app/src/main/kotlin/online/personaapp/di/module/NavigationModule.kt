package online.personaapp.di.module

import dagger.Binds
import dagger.Module
import online.personaapp.core.presentation.navigation.Navigator
import online.personaapp.core.presentation.navigation.NavigatorImpl

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
@Module
interface NavigationModule {

  @Binds
  fun bindNavigator(impl: NavigatorImpl): Navigator

}