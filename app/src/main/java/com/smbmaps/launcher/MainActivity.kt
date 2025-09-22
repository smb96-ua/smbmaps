package com.smbmaps.launcher

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smbmaps.launcher.adapters.AppGridAdapter
import com.smbmaps.launcher.databinding.ActivityMainBinding
import com.smbmaps.launcher.models.AppInfo

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var appGridAdapter: AppGridAdapter
    private val installedApps = mutableListOf<AppInfo>()
    private var selectedApp: AppInfo? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViews()
        loadInstalledApps()
    }
    
    private fun setupViews() {
        // Hide system UI for full-screen experience
        hideSystemUI()
        
        // Setup RecyclerView for apps grid
        appGridAdapter = AppGridAdapter(installedApps) { appInfo ->
            onAppSelected(appInfo)
        }
        
        binding.recyclerViewApps.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 4)
            adapter = appGridAdapter
        }
        
        // Initially hide PIP container
        binding.pipContainer.visibility = View.GONE
        binding.textNoPip.visibility = View.VISIBLE
    }
    
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )
    }
    
    private fun loadInstalledApps() {
        val packageManager = packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        
        val resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        
        installedApps.clear()
        for (resolveInfo in resolveInfoList) {
            val appInfo = AppInfo(
                packageName = resolveInfo.activityInfo.packageName,
                appName = resolveInfo.loadLabel(packageManager).toString(),
                icon = resolveInfo.loadIcon(packageManager)
            )
            
            // Filter out this launcher app
            if (appInfo.packageName != packageName) {
                installedApps.add(appInfo)
            }
        }
        
        // Sort apps alphabetically
        installedApps.sortBy { it.appName }
        appGridAdapter.notifyDataSetChanged()
    }
    
    private fun onAppSelected(appInfo: AppInfo) {
        selectedApp = appInfo
        
        // Update PIP section
        binding.textNoPip.visibility = View.GONE
        binding.pipContainer.visibility = View.VISIBLE
        binding.imageSelectedApp.setImageDrawable(appInfo.icon)
        binding.textSelectedApp.text = appInfo.appName
        
        // Launch app button
        binding.buttonLaunchApp.setOnClickListener {
            launchApp(appInfo)
        }
        
        // PIP mode button
        binding.buttonPipMode.setOnClickListener {
            // For now, just launch the app normally
            // In a real implementation, you'd need system-level permissions for true PIP
            launchApp(appInfo)
        }
    }
    
    private fun launchApp(appInfo: AppInfo) {
        val launchIntent = packageManager.getLaunchIntentForPackage(appInfo.packageName)
        launchIntent?.let {
            startActivity(it)
        }
    }
    
    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }
}