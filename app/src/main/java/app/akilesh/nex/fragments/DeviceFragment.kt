package app.akilesh.nex.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import java.io.*


class DeviceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_device, container, false)

        val brand = Build.BRAND
        val deviceBrand = view.findViewById<TextView>(R.id.brand)
        deviceBrand.text = String.format("%s", brand)

        val model = Build.MODEL
        val deviceModel = view.findViewById<TextView>(R.id.model)
        deviceModel.text = String.format("%s", model)

        val code = Build.DEVICE
        val deviceCodeName = view.findViewById<TextView>(R.id.codeName)
        deviceCodeName.text = String.format("%s", code)

        var buildVer = "Unknown"
        if(File("/proc/fver").exists()) {
            try {
                val process = Runtime.getRuntime().exec("su")
                val `in` = process.inputStream
                val out = process.outputStream
                val cmd = "[ -r /proc/fver ] && head -n 1 /proc/fver"
                out.write(cmd.toByteArray())
                out.flush()
                out.close()
                process.waitFor()

                if (process.exitValue() != 0) {
                    Log.e(TAG, "Failed to obtain root")

                    try {
                        val p = Runtime.getRuntime().exec("getprop ro.build.version.incremental")

                        val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
                        buildVer = stdInput.readLine().trim()

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                else {
                    var ch: Int
                    val sb = StringBuilder()
                    while (`in`.read().let { ch = it; it != -1 })
                        sb.append(ch.toChar())
                    buildVer = sb.toString().substring(4, 23)
                }
                buildVer = buildVer.trim { it <= ' ' }

            } catch (e: IOException) {
                Log.e(TAG, "IOException, " + e.message)
            } catch (e: InterruptedException) {
                Log.e(TAG, "InterruptedException, " + e.message)
            }
        }

        else {
            Log.e(TAG, "/proc/fver doesn't exist")
            try {
                val p = Runtime.getRuntime().exec("getprop ro.build.version.incremental")

                val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
                buildVer = stdInput.readLine().trim()

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val build = view.findViewById<TextView>(R.id.buildVersion)
        build.text = String.format("%s", buildVer)


        var skuid = "Unknown"
        if(File("/proc/cda/skuid").exists()) {
            try {
                val process = Runtime.getRuntime().exec("su")
                val `in` = process.inputStream
                val out = process.outputStream
                val cmd = "[ -r /proc/cda/skuid ] && head -n 1 /proc/cda/skuid"
                out.write(cmd.toByteArray())
                out.flush()
                out.close()
                process.waitFor()

                if (process.exitValue() != 0) {
                    Log.e(TAG, "Failed to obtain root")
                    try {
                        val p = Runtime.getRuntime().exec("getprop ro.cda.skuid.id")

                        val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
                        skuid = stdInput.readLine().trim()

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                else {
                    var ch: Int
                    val sb = StringBuilder()
                    while (`in`.read().let { ch = it; it != -1 })
                        sb.append(ch.toChar())
                    skuid = sb.toString()
                }
                skuid = skuid.trim { it <= ' ' }

            } catch (e: IOException) {
                Log.e(TAG, "IOException, " + e.message)

            } catch (e: InterruptedException) {
                Log.e(TAG, "InterruptedException, " + e.message)
            }
        }
        else {
            Log.e(TAG, "/proc/cda/skuid doesn't exist")

            try {
                val p = Runtime.getRuntime().exec("getprop ro.cda.skuid.id")

                val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
                skuid = stdInput.readLine().trim()

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        val textView = view.findViewById<TextView>(R.id.skuid)
        textView.text = String.format("%s", skuid)

        var asp = "Unknown"
        try {
            val p = Runtime.getRuntime().exec("getprop ro.build.version.security_patch")

            val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
            asp = stdInput.readLine().trim()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        val aspTextView = view.findViewById<TextView>(R.id.asp)
        aspTextView.text = String.format("%s", asp)

        var vsp = ""
        try {
            val p = Runtime.getRuntime().exec("getprop ro.vendor.build.security_patch")

            val stdInput = BufferedReader(InputStreamReader(p.inputStream) as Reader?)
            vsp = stdInput.readLine().trim()

        } catch (e: IOException) {
            e.printStackTrace()
        }
        if(vsp.isEmpty()) vsp = "Unknown"
        val vspTextView = view.findViewById<TextView>(R.id.vsp)
        vspTextView.text = String.format("%s", vsp)


        return view
    }

    companion object {

        internal const val TAG = "DeviceFragmentTag"
    }

}
