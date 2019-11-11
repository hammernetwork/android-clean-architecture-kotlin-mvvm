package online.personaapp.core.di

import online.personaapp.core.presentation.navigation.Navigator

interface RouteActivityDependencies {
  fun navigator(): Navigator

}