package online.personaapp.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import online.personaapp.core.exception.Failure

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Base ViewModel class with default Failure handling.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel() {

  var failure: MutableLiveData<Failure> = MutableLiveData()

  protected fun handleFailure(failure: Failure) {
    this.failure.value = failure
  }

  override fun onCleared() {
    super.onCleared()
    cancelRequest()
  }

  abstract fun cancelRequest()
}