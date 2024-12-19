package com.example.remoria

import android.os.Parcel
import android.os.Parcelable

data class SlamInfo(
    val fullName: String,
    val nickname: String,
    val zodiacSign: String,
    val relationshipStatus: String,
    val color: String,
    val movieCharacter: String,
    val place: String,
    val favoriteQuote: String,
    val bestDescription: String
) : Parcelable {
    // Constructor to create an object from a Parcel
    constructor(parcel: Parcel) : this(
        fullName = parcel.readString() ?: "",
        nickname = parcel.readString() ?: "",
        zodiacSign = parcel.readString() ?: "",
        relationshipStatus = parcel.readString() ?: "",
        color = parcel.readString() ?: "",
        movieCharacter = parcel.readString() ?: "",
        place = parcel.readString() ?: "",
        favoriteQuote = parcel.readString() ?: "",
        bestDescription = parcel.readString() ?: ""
    )

    // Write all properties to the Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(nickname)
        parcel.writeString(zodiacSign)
        parcel.writeString(relationshipStatus)
        parcel.writeString(color)
        parcel.writeString(movieCharacter)
        parcel.writeString(place)
        parcel.writeString(favoriteQuote)
        parcel.writeString(bestDescription)
    }

    // Required method for Parcelable (usually returns 0)
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SlamInfo> {
        override fun createFromParcel(parcel: Parcel): SlamInfo {
            return SlamInfo(parcel)
        }

        override fun newArray(size: Int): Array<SlamInfo?> {
            return arrayOfNulls(size)
        }
    }
}