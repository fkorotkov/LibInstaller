package cc.hyperium.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

class DoDownload {
    @JvmStatic val BUFFER_SIZE = 4096
    private final String downloadURL
    @JvmStatic val saveDirectory
    val fileName

    DownloadTask(downloadURL: String, saveDirectory: String) {
        this.downloadURL = downloadURL
        this.saveDirectory = saveDirectory
    }

    @Throws(Exception::class) fun doInBackground() {
        val util = new HTTPDownloadUtil()
        util.downloadFile(downloadURL)
        this.fileName = util.getFileName()
        val saveFilePath = File(saveDirectory, util.getFileName())

        val inputStream = util.getInputStream();
        // opens an output stream to save into file
        val outputStream = FileOutputStream(saveFilePath);

        byte[] buffer = byte[BUFFER_SIZE];
        val bytesRead, percentCompleted;
        val totalBytesRead = 0
        val fileSize = util.getContentLength()

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead)
            totalBytesRead += bytesRead
            percentCompleted = (int) (totalBytesRead * 100 / fileSize)

            setProgress(percentCompleted)
        }

        outputStream.close()
        util.disconnect()
    }
}
