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
