package online.personaapp.movies.presentation.viewstate

import android.os.Parcel
import online.personaapp.core.platform.KParcelable
import online.personaapp.core.platform.parcelableCreator

/**
 * Created by Andriy on 2019-10-15.
 * Copyright (c) 2019 Personaapp. All rights reserved.
 */
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
