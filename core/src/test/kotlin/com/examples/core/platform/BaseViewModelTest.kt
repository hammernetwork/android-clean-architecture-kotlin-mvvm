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
import com.examples.AndroidTest
import com.examples.core.exception.Failure
import com.examples.core.exception.Failure.NetworkConnection
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {
  @Test
  fun `should handle failure by updating live data`() {
    val viewModel = MyViewModel()

    viewModel.handleError(NetworkConnection)

    val failure = viewModel.failure
    val error = viewModel.failure.value

    failure shouldBeInstanceOf MutableLiveData::class.java
    error shouldBeInstanceOf NetworkConnection::class.java
  }

  private class MyViewModel : BaseViewModel() {
    fun handleError(failure: Failure) = handleFailure(failure)

    override fun cancelRequest() {
      //Nothing to do
    }
  }
}