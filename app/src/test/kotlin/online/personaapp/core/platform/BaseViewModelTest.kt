package online.personaapp.core.platform

import androidx.lifecycle.MutableLiveData
import online.personaapp.AndroidTest
import online.personaapp.core.exception.Failure
import online.personaapp.core.exception.Failure.NetworkConnection
import online.personaapp.core.functional.Either
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {
  @Test
  fun `should handle failure by updating live data`() {
    val viewModel = MyViewModel()

    viewModel.handleError(NetworkConnection())

    val failure = viewModel.failure
    val error = viewModel.failure.value

    failure shouldBeInstanceOf MutableLiveData::class.java
    error shouldBeInstanceOf NetworkConnection::class.java
  }

  private class DummyUseCase : online.personaapp.core.interactor.UseCase<Any, Any>() {
    override suspend fun run(params: Any): Either<Failure, Any> {
      TODO(
          "not implemented") //To change body of created functions use File | Settings | File Templates.
    }
  }

  private class MyViewModel : BaseViewModel() {
    fun handleError(failure: Failure) = handleFailure(failure)

    override fun cancelRequest() {
      //Nothing to do
    }
  }
}