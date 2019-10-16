package com.hyperiumjailbreak.backend.utils

import com.hyperiumjailbreak.backend.callback.Callback
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.util.regex.Pattern

class DownloadTask(private val downloadURL: String, private val saveDirectory: String, private val callback: Callback) {
    private var fileName: String? = null

    init {
        download()
    }

    @Throws(Exception::class)
    fun download(): String {
        val url = URL(this.downloadURL)
        val httpConn = url.openConnection() as HttpURLConnection
        httpConn.requestMethod = "GET"
        httpConn.useCaches = true
        httpConn.readTimeout = 15000
        httpConn.connectTimeout = 15000
        httpConn.doOutput = true
        if (httpConn.responseCode == HttpURLConnection.HTTP_OK) {
            val disposition = httpConn.getHeaderField("Content-Disposition")

            if (disposition != null) {
                val m = Pattern.compile("filename=(?<name>\\S+)").matcher(disposition)
                if (m.find()) fileName = m.group("name")
            }

            if (fileName == null) fileName = this.downloadURL.substring(this.downloadURL.lastIndexOf("/") + 1)
            fileName = URLDecoder.decode(fileName!!, "UTF-8")
        } else {
            println("! - Got code ${httpConn.responseCode} from server!")
            this.callback.sendCode(2)
            this.callback.sendText("Failed to download a file!")
        }
        val inputStream = httpConn.inputStream
        val saveFilePath = File(saveDirectory, this.fileName!!)
        val outputStream = FileOutputStream(saveFilePath)
        val buffer = ByteArray(4096)
        val bytes: Int = inputStream.read(buffer)
        while (bytes != -1) outputStream.write(buffer, 0, bytes)
        outputStream.close()
        inputStream.close()
        httpConn.disconnect()
        return fileName as String
    }
}
