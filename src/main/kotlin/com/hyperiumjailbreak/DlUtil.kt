package com.hyperiumjailbreak;

import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLDecoder
import java.util.regex.Matcher
import java.util.regex.Pattern

public class HTTPDownloadUtil {
    val FILENAME = Pattern.compile("filename=(?<name>\\S+)")
    val httpConn
    val inputStream
    val fileName
    val contentLength
    @Throws(IOException::class) public fun downloadFile(fileURL: String) {
        URL url = new URL(fileURL)
        httpConn = (HttpURLConnection) url.openConnection()
        httpConn.setRequestMethod("GET")
        httpConn.setUseCaches(true)
        httpConn.addRequestProperty("User-Agent", "HJB")
        httpConn.setReadTimeout(15000)
        httpConn.setConnectTimeout(15000)
        httpConn.setDoOutput(true)

        val responseCode = httpConn.getResponseCode()

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String disposition = httpConn.getHeaderField("Content-Disposition")
            String contentType = httpConn.getContentType()
            contentLength = httpConn.getContentLength()

            if (disposition != null) {
                val m = FILENAME.matcher(disposition)
                if (m.find()) fileName = m.group("name")
            }

            if (fileName == null) fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1)
            fileName = URLDecoder.decode(fileName, "UTF-8")

            // opens input stream from the HTTP connection
            inputStream = httpConn.getInputStream()
        } else {
            throw IOException(responseCode)
        }
    }

    @Throws(IOException::class) public fun disconnect() {
        inputStream.close()
        httpConn.disconnect()
    }

    public val getFileName() : String { return this.fileName }

    public val getContentLength() : Integer { return this.contentLength }

    public val getInputStream() : InputStream { return this.inputStream }
}
