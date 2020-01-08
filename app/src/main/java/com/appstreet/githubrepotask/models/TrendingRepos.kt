package com.appstreet.githubrepotask.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "trendingrepos")
data class TrendingRepos(

    @SerializedName("author")
    val author: String? = "",
    @SerializedName("avatar")
    val avatar: String? = "",

    @SerializedName("builtBy")
    val builtBy: ArrayList<BuiltBy?> = arrayListOf(),

    @SerializedName("currentPeriodStars")
    val currentPeriodStars: Int? = 0,
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("forks")
    val forks: Int? = 0, // 230
    @SerializedName("language")
    val language: String? = "",
    @SerializedName("languageColor")
    val languageColor: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("stars")
    val stars: Int? = 0,
    @SerializedName("url")
    val url: String = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @Parcelize
    data class BuiltBy(
        @SerializedName("avatar")
        val avatar: String? = "",
        @SerializedName("href")
        val href: String? = "",
        @SerializedName("username")
        val username: String? = ""
    ) : Parcelable
}