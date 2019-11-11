package online.personaapp.movies.presentation.di.dependencies

import androidx.lifecycle.ViewModelProvider
import online.personaapp.core.presentation.navigation.Navigator

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
interface MovieDetailsPresentationDependencies {
  fun navigator(): Navigator
  fun viewModelFactory(): ViewModelProvider.Factory

}