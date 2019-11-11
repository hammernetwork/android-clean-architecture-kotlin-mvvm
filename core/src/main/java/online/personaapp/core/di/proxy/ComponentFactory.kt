package online.personaapp.core.di.proxy

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
interface ComponentFactory {
  fun createComponent(clazz: Class<*>): Any
}
