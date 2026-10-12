package com.example.quick_android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object Api {

    // In Api.kt
    fun JSONObject.str(key: String, default: String = ""): String = optString(key, default)
    fun JSONObject.int(key: String, default: Int = 0): Int = optInt(key, default)
    fun JSONObject.bool(key: String, default: Boolean = false): Boolean = optBoolean(key, default)
    fun JSONObject.obj(key: String): JSONObject = optJSONObject(key) ?: JSONObject()
    fun JSONObject.arr(key: String): org.json.JSONArray = optJSONArray(key) ?: org.json.JSONArray()

    suspend fun get(url: String): JSONObject = withContext(Dispatchers.IO) {
        JSONObject(URL(url).readText())
    }

    suspend fun post(url: String, body: JSONObject): JSONObject = withContext(Dispatchers.IO) {
        val conn = (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        conn.outputStream.use { it.write(body.toString().toByteArray()) }
        JSONObject(conn.inputStream.reader().readText())
    }
}