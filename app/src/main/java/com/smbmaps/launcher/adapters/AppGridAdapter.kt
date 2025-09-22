package com.smbmaps.launcher.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smbmaps.launcher.databinding.ItemAppBinding
import com.smbmaps.launcher.models.AppInfo

class AppGridAdapter(
    private val apps: List<AppInfo>,
    private val onAppClick: (AppInfo) -> Unit
) : RecyclerView.Adapter<AppGridAdapter.AppViewHolder>() {

    class AppViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appInfo: AppInfo, onAppClick: (AppInfo) -> Unit) {
            binding.imageAppIcon.setImageDrawable(appInfo.icon)
            binding.textAppName.text = appInfo.appName
            
            binding.root.setOnClickListener {
                onAppClick(appInfo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(apps[position], onAppClick)
    }

    override fun getItemCount(): Int = apps.size
}