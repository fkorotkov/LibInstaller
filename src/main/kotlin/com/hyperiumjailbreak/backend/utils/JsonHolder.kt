package com.hyperiumjailbreak.backend.utils

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class JsonHolder {
    var obj: JsonObject? = null

    constructor(obj: JsonObject) {
        this.obj = obj
    }

    constructor(raw: String?) {
        if (raw == null || raw.isEmpty()) {
            obj = JsonObject()
            return
        }
        try {
            this.obj = JsonParser().parse(raw).asJsonObject
        } catch (e: Exception) {
            this.obj = JsonObject()
            e.printStackTrace()
        }
    }

    constructor() : this(JsonObject())

    override fun toString(): String {
        return if (obj != null) obj!!.toString() else "{}"
    }

    fun getKeys(): List<String> {
        val list = mutableListOf<String>()
        for (i in this.obj?.entrySet()!!) {
            list.add(i.key)
        }
        return list
    }

    fun put(key: String, value: String): JsonHolder {
        obj!!.addProperty(key, value)
        return this
    }

    fun optJSONObject(key: String, fallBack: JsonObject): JsonHolder {
        return try {
            JsonHolder(obj!!.get(key).asJsonObject)
        } catch (e: Exception) {
            JsonHolder(fallBack)
        }
    }

    fun optJSONArray(key: String, fallback: JsonArray): JsonArray {
        return try {
            obj!!.get(key).asJsonArray
        } catch (e: Exception) {
            fallback
        }
    }

    fun optJSONArray(key: String): JsonArray {
        return optJSONArray(key, JsonArray())
    }

    fun has(key: String): Boolean {
        return obj!!.has(key)
    }

    fun optJSONObject(key: String): JsonHolder {
        return optJSONObject(key, JsonObject())
    }

    fun optString(key: String): String {
        return try {
            obj!!.get(key).asString
        } catch (e: Exception) {
            ""
        }
    }

    fun put(values: String, values1: JsonHolder): JsonHolder {
        return put(values, values1.obj)
    }

    fun put(values: String, `object`: JsonObject?): JsonHolder {
        this.obj!!.add(values, `object`)
        return this
    }

    fun put(key: String, value: JsonArray): JsonHolder {
        this.obj!!.add(key, value)
        return this
    }
}