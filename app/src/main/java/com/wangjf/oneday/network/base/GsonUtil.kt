package com.wangjf.kotlinframwork.network.base

import com.google.gson.*
import java.lang.reflect.Type
import java.text.DateFormat
import java.util.*

/**
 * Created by junfengwang on 2017/7/14.
 */
object GsonUtil {

    fun create():Gson{
        var gb = GsonBuilder()
        gb.registerTypeAdapter(Date::class.java , DateDeserializer()).setDateFormat(DateFormat.LONG)
        gb.registerTypeAdapter(Date::class.java , DateSerializer()).setDateFormat(DateFormat.LONG)
        return gb.create()
    }

    internal class DateDeserializer : JsonDeserializer<Date>{

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Date {
            return Date(json.asJsonPrimitive.asLong)
        }
    }


    internal class DateSerializer : JsonSerializer<Date>{
        override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            return JsonPrimitive(src?.time)
        }

    }
}