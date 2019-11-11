package online.personaap.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkComponentApi {

  companion object {
    @Volatile
    private lateinit var component: NetworkComponent

    fun get(): NetworkComponent {
      if (!this::component.isInitialized) {
        component = DaggerNetworkComponent
            .builder()
            .build()
      }

      return component
    }
  }

}
