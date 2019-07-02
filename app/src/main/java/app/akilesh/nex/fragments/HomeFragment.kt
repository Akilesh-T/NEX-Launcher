package app.akilesh.nex.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.akilesh.nex.R
import com.google.android.material.card.MaterialCardView


class HomeFragment : Fragment(),  View.OnClickListener  {
    private lateinit var ai:MaterialCardView
    private lateinit var fw:MaterialCardView
    private lateinit var fu:MaterialCardView
    private lateinit var ga:MaterialCardView
    private lateinit var ges:MaterialCardView
    private lateinit var gl:MaterialCardView
    private lateinit var jc:MaterialCardView
    private lateinit var sr:MaterialCardView
    private lateinit var sb:MaterialCardView
    private lateinit var tm:MaterialCardView
    private lateinit var vs:MaterialCardView

    private val appName: Map<String, String> = mapOf("com.nbc.AIFloatingTouch.STouchActivity" to "AI Floating Touch", "om.evenwell.firewall.TrafficControl" to "App Traffic Control", "com.android.settings.Settings\$SecurityDashboardActivity" to "Face Plus Service", "com.nbc.gameassistant.GameAssistantActivity" to "Game Assistant", "com.android.settings.Settings\$GestureSettingsActivity" to "Gesture overlay", "com.android.systemui.keyguard.glance.GlanceSettingsActivity" to "Glance Screen", "com.evenwell.cleaner.MainActivity" to "Junk Cleaner", "com.nbc.android.screenrecord.ui.ActivitySetting" to "Screen Recorder", "com.evenwell.smartboost.activities.MainActivity" to "Smart Boost", "com.evenwell.memorycleaner.MainActivity" to "Task Manager", "com.evenwell.viruscan.qscanner.VirusScannerActivity" to "Virus Scanner")

    private fun isCallable(intent: Intent): Boolean {
        val activities = context!!.packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY)
        return activities.isNotEmpty()
    }

    private fun launchApp(packageName: String, className: String){

        val intent = Intent(Intent.ACTION_MAIN)
        intent.setClassName(packageName, className)
        if (isCallable(intent))
            startActivity(intent)
        else {
           val toast = Toast.makeText(activity, "${appName[className]} is not installed", Toast.LENGTH_SHORT)
           toast.setGravity(Gravity.BOTTOM, 0, 230)
           toast.show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.fragment_home, container, false)

        ai = view.findViewById(R.id.ai_desc)
        ai.setOnClickListener(this)

        fw = view.findViewById(R.id.fw_desc)
        fw.setOnClickListener(this)

        fu = view.findViewById(R.id.face_unlock)
        fu.setOnClickListener(this)

        ga = view.findViewById(R.id.ga_desc)
        ga.setOnClickListener(this)

        ges = view.findViewById(R.id.ges_desc)
        ges.setOnClickListener(this)

        gl = view.findViewById(R.id.gl_desc)
        gl.setOnClickListener(this)

        jc = view.findViewById(R.id.jc_desc)
        jc.setOnClickListener(this)

        sr = view.findViewById(R.id.sr_desc)
        sr.setOnClickListener(this)

        sb = view.findViewById(R.id.sb_desc)
        sb.setOnClickListener(this)

        tm = view.findViewById(R.id.tm_desc)
        tm.setOnClickListener(this)

        vs = view.findViewById(R.id.vs_desc)
        vs.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ai_desc -> {
                launchApp("com.nbc.AIFloatingTouch", "com.nbc.AIFloatingTouch.STouchActivity")
            }

            R.id.fw_desc -> {
                launchApp("com.evenwell.firewall", "com.evenwell.firewall.TrafficControl")
            }

            R.id.face_unlock -> {
                launchApp("com.android.settings", "com.android.settings.Settings\$SecurityDashboardActivity")
            }

            R.id.ga_desc -> {
                launchApp("com.nbc.gameassistant", "com.nbc.gameassistant.GameAssistantActivity")
            }

            R.id.ges_desc -> {
                launchApp("com.android.settings", "com.android.settings.Settings\$GestureSettingsActivity")
            }

            R.id.gl_desc -> {
                launchApp("com.android.systemui", "com.android.systemui.keyguard.glance.GlanceSettingsActivity")
            }

            R.id.jc_desc -> {
                launchApp("com.evenwell.cleaner", "com.evenwell.cleaner.MainActivity")
            }

            R.id.sr_desc -> {
                launchApp("com.nbc.android.screenrecord", "com.nbc.android.screenrecord.ui.ActivitySetting")
            }

            R.id.sb_desc -> {
                launchApp("com.evenwell.smartboost", "com.evenwell.smartboost.activities.MainActivity")
            }

            R.id.tm_desc -> {
                launchApp("com.evenwell.memorycleaner", "com.evenwell.memorycleaner.MainActivity")
            }

            R.id.vs_desc -> {
                launchApp("com.evenwell.viruscan", "com.evenwell.viruscan.qscanner.VirusScannerActivity")
            }
        }
    }


    companion object {

        internal const val TAG = "HomeFragmentTag"

    }

}
