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
package com.examples.core.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examples.core.exception.Failure

/**
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