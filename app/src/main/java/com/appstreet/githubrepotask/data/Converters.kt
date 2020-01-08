package com.appstreet.githubrepotask.data

import androidx.room.TypeConverter
import com.appstreet.githubrepotask.models.TrendingRepos.BuiltBy
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList


class Converters {


    var gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): ArrayList<BuiltBy?>? {
        if (data == null) {
            return arrayListOf()
        }
        val listType: Type =
            object : TypeToken<List<BuiltBy?>?>() {}.getType()
        return gson.fromJson<ArrayList<BuiltBy?>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: ArrayList<BuiltBy?>?): String? {
        return gson.toJson(someObjects)
    }

}