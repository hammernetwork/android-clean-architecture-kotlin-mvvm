package com.examples.movies.presentation.di.dependencies

import androidx.lifecycle.ViewModelProvider
import com.examples.core.presentation.navigation.Navigator

interface MoviesPresentationDependencies {
  fun navigator(): Navigator
  fun viewModelFactory(): ViewModelProvider.Factory

}