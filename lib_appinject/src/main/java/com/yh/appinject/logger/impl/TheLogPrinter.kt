package com.yh.appinject.logger.impl

import android.database.Cursor
import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.database.getStringOrNull
import com.yh.appinject.logger.LogAdapter
import com.yh.appinject.logger.Printer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.PrintWriter
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 * Created by CYH on 2020/5/14 14:35
 */
internal class TheLogPrinter : Printer {
    
    /**
     * Provides one-time used tag for the log message
     */
    private val localTag = ThreadLocal<String>()
    private val localAdapter = ThreadLocal<LogAdapter>()
    
    override fun clearLogAdapters() {
        localAdapter.remove()
    }
    
    override fun addAdapter(@NonNull adapter: LogAdapter?) {
        throw RuntimeException("Can not add!")
    }
    
    fun adapter(adapter: LogAdapter?): Printer {
        if(null != adapter) {
            localAdapter.set(adapter)
        }
        return this
    }
    
    override fun t(tag: String?): Printer {
        if(tag != null) {
            localTag.set(tag)
        }
        return this
    }
    
    override fun d(@Nullable message: Any?) {
        log(Log.DEBUG, null, message?.toString())
    }
    
    override fun d(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.DEBUG, null, message, *args)
    }
    
    override fun i(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.INFO, null, message, *args)
    }
    
    override fun v(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.VERBOSE, null, message, *args)
    }
    
    override fun w(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.WARN, null, message, *args)
    }
    
    override fun e(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.ERROR, null, message, *args)
    }
    
    override fun e(@Nullable throwable: Throwable?, @NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.ERROR, throwable, message, *args)
    }
    
    override fun wtf(@NonNull message: String, @Nullable vararg args: Any?) {
        log(Log.ASSERT, null, message, *args)
    }
    
    override fun json(jsonAny: Any?) {
        when(jsonAny) {
            is CharSequence -> {
                json(jsonAny.toString())
            }
            is JSONObject   -> {
                json(jsonAny)
            }
            is JSONArray    -> {
                json(jsonAny)
            }
            is Any          -> {
                d(jsonAny.toString())
            }
            null            -> {
                d("Empty/Null json content")
            }
        }
    }
    
    private fun json(json: JSONArray?) {
        if(null == json) {
            d("Empty json content")
            return
        }
        d(json.toString(2))
    }
    
    private fun json(json: JSONObject?) {
        if(null == json) {
            d("Null json content")
            return
        }
        d(json.toString(2))
    }
    
    private fun json(json: String?) {
        if(null == json || TextUtils.isEmpty(json)) {
            d("Empty/Null json content")
            return
        }
        try {
            val jsonTmp = json.trim()
            if(jsonTmp.startsWith("{")) {
                json(JSONObject(jsonTmp))
                return
            }
            if(jsonTmp.startsWith("[")) {
                json(JSONArray(jsonTmp))
                return
            }
            e("Invalid Json: $json")
        } catch (e: JSONException) {
            e("Invalid Json: $json")
        }
    }
    
    override fun xml(xml: String?) {
        if(null == xml || TextUtils.isEmpty(xml)) {
            d("Empty/Null xml content")
            return
        }
        try {
            val xmlInput: Source = StreamSource(StringReader(xml))
            val xmlOutput = StreamResult(StringWriter())
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
            transformer.transform(xmlInput, xmlOutput)
            d(xmlOutput.writer.toString().replaceFirst(">", ">\n"))
        } catch (e: TransformerException) {
            e("Invalid xml: $xml")
        }
    }
    
    override fun cursor(cursor: Cursor?, justCurrentRow: Boolean) {
        if(null == cursor || cursor.count <= 0) {
            d("Empty/Null cursor content")
            return
        }
        val builder = StringBuilder()
        if(justCurrentRow) {
            cursorCurRow(cursor, builder)
            d(builder)
            return
        }
        val startPos = cursor.position
        if(cursor.moveToFirst()) {
            do {
                cursorCurRow(cursor, builder)
                builder.append("\n")
            } while(cursor.moveToNext())
            builder.deleteCharAt(builder.length - 1)
        }
        cursor.moveToPosition(startPos)
        d(builder)
    }
    
    private fun cursorCurRow(cursor: Cursor, builder: StringBuilder) {
        val cols = cursor.columnNames
        builder.append(cursor.position).append(" {")
        cols.forEachIndexed { index, colName ->
            val colIndex = cursor.getColumnIndex(colName)
            builder.append(colName).append("=").append(getCursorColValue(cursor, colIndex))
            if(index != cols.size - 1) {
                builder.append(", ")
            }
        }
        builder.append("}")
    }
    
    private fun getCursorColValue(cursor: Cursor, colIndex: Int): String {
        if(-1 == colIndex) {
            return "<UNKNOWN>"
        }
        return cursor.getStringOrNull(colIndex)
            ?: "<NULL>"
    }
    
    override fun log(priority: Int, tag: String?, message: String?, throwable: Throwable?) {
        var newMsg = message
        if(throwable != null && message != null) {
            newMsg += " : " + getStackTraceString(throwable)
        }
        if(throwable != null && message == null) {
            newMsg = getStackTraceString(throwable)
        }
        if(null == newMsg || TextUtils.isEmpty(message)) {
            newMsg = "Empty/NULL log message"
        }
        val adapter = getAdapter()
            ?: return
        if(adapter.isLoggable(priority, tag)) {
            adapter.log(priority, tag, newMsg)
        }
    }
    
    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    @Synchronized
    private fun log(priority: Int, @Nullable throwable: Throwable?, @Nullable msg: String?, @Nullable vararg args: Any?) {
        val tag = getTag()
        val message = createMessage(msg, *args)
        log(priority, tag, message, throwable)
    }
    
    /**
     * @return the appropriate tag based on local or global
     */
    @Nullable
    private fun getTag(): String? {
        val tag = localTag.get()
        if(tag != null) {
            localTag.remove()
            return tag
        }
        return null
    }
    
    @Nullable
    private fun getAdapter(): LogAdapter? {
        val adapter = localAdapter.get()
        localAdapter.remove()
        return adapter
    }
    
    @NonNull
    private fun createMessage(@Nullable message: String?, @Nullable vararg args: Any?): String {
        if(null == message) return ""
        return if(args.filterNotNull().isEmpty()) {
            message
        } else {
            String.format(message, *args)
        }
    }
    
    private fun getStackTraceString(t: Throwable): String? {
        // Don't replace this with Log.getStackTraceString() - it hides
        // UnknownHostException, which is not what we want.
        val sw = StringWriter(256)
        val pw = PrintWriter(sw, false)
        t.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }
}