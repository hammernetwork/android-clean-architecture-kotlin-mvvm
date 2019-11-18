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
package com.examples.movies.presentation.viewstate

import android.os.Parcel
import com.examples.core.platform.KParcelable
import com.examples.core.platform.parcelableCreator

data class MovieView(val id: Int, val poster: String) : KParcelable {
  companion object {
    const val PARCELABLE_KEY = "MovieViewKey"
    @JvmField
    val CREATOR = parcelableCreator(::MovieView)
  }

  constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString() ?: "")

  override fun writeToParcel(dest: Parcel, flags: Int) {
    with(dest) {
      writeInt(id)
      writeString(poster)
    }
  }
}
