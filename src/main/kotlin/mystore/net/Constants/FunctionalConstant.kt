package mystore.net.Constants

import io.ktor.http.content.*
import java.io.File
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.*

fun PartData.FileItem.save(path: String): String {
    // read the file bytes
    val fileBytes = streamProvider().readBytes()
    // find the file extension eg: .jpg
    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    // generate a random name for the new file and append the file extension
    val fileName = UUID.randomUUID().toString() + "." + fileExtension
    // create our new file in the server
    val folder = File(path)
    // create parent directory if not exits
    if (!folder.parentFile.exists()) {
        folder.parentFile.mkdirs()
    }
    // continue with creating our new file
    folder.mkdir()
    // write bytes to our newly created file
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}


fun getLocalIPv4Address(): String? {
    try {
        for (intf in NetworkInterface.getNetworkInterfaces()) {
            for (address in intf.inetAddresses) {
                if (address is Inet4Address && !address.isLoopbackAddress) {
                    return address.hostAddress
                }
            }
        }
    } catch (ex: Exception) {
        println("Error getting IP address: $ex")
    }

    return null  // If no suitable IPv4 address is found
}