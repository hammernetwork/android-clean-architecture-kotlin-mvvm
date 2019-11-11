package online.personaapp.core.interactor

import kotlinx.coroutines.*
import online.personaapp.core.exception.Failure
import online.personaapp.core.functional.Either
import kotlin.coroutines.CoroutineContext

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 *
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> : CoroutineScope where Type : Any {

  private val job = Job()
  private val uiScope = Dispatchers.Main

  override val coroutineContext: CoroutineContext
    get() = job + Dispatchers.IO

  abstract suspend fun run(params: Params): Either<Failure, Type>

  operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) =
//      uiScope.launch { onResult(withContext(Dispatchers.IO) { run(params) }) }
      launch {
        val result = run(params)

        withContext(uiScope) {
          onResult(result)
        }
      }


  fun cancel() {
    coroutineContext.cancel()
  }

  class None
}
