package gr.makris.shobix

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import gr.makris.shobix.databinding.ActivityMainBinding
import gr.makris.shobix.fragment.main.MainFragment


class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
        setFocusListeners()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun setOnClickListeners() {
        binding.toolbarSettingsIcon.setOnClickListener {
            openSettingsPanel()
        }

        binding.settingsOverlay.setOnClickListener {
            closeSettingsPanel()
        }
    }

    private fun setFocusListeners() {
        binding.toolbarSettingsIcon.setOnFocusChangeListener { view, focused ->
            val imageView = view as? ImageView
            if (focused) {
                imageView?.setColorFilter(Color.WHITE)
            } else {
                imageView?.setColorFilter(Color.BLACK)
            }
        }

        binding.settingsMenu.privacySection.setOnFocusChangeListener { view, focused ->
            val section = view as? ConstraintLayout
            if (focused) {
                section?.setBackgroundColor(resources.getColor(R.color.settings_selected))
            } else {
                section?.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        binding.settingsMenu.updateSection.setOnFocusChangeListener { view, focused ->
            val section = view as? ConstraintLayout
            if (focused) {
                section?.setBackgroundColor(resources.getColor(R.color.settings_selected))
            } else {
                section?.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        binding.settingsMenu.helpSection.setOnFocusChangeListener { view, focused ->
            val section = view as? ConstraintLayout
            if (focused) {
                section?.setBackgroundColor(resources.getColor(R.color.settings_selected))
            } else {
                section?.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        binding.settingsMenu.aboutSection.setOnFocusChangeListener { view, focused ->
            val section = view as? ConstraintLayout
            if (focused) {
                section?.setBackgroundColor(resources.getColor(R.color.settings_selected))
            } else {
                section?.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        binding.bannerView.setOnFocusChangeListener { view, focused ->
            val cardView = view as? CardView
            if (focused) {
                cardView?.setBackgroundResource(R.drawable.card_selected)
            } else {
                cardView?.setBackgroundResource(R.drawable.card_unselected)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!isSettingsMenuVisible()) {
            return super.onKeyDown(keyCode, event)
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            handleLeftKeyPress()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun handleLeftKeyPress() {
        if (isSettingsMenuVisible()) {
            closeSettingsPanel()
        }
    }

    private fun openSettingsPanel() {
        binding.settingsOverlay.visibility = View.VISIBLE
        binding.settingsMenu.root.visibility = View.VISIBLE
        val slideAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        binding.settingsMenu.root.startAnimation(slideAnimation)
        onSettingsPanelVisible()
    }

    private fun closeSettingsPanel() {
        binding.settingsOverlay.visibility = View.GONE
        val slideAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        binding.settingsMenu.root.startAnimation(slideAnimation)
        binding.settingsMenu.root.visibility = View.GONE
        enableFocus()
    }

    private fun onSettingsPanelVisible() {
        binding.settingsMenu.privacySection.requestFocus()
        disableFocus()
    }

    private fun disableFocus() {
        binding.toolbarSettingsIcon.isClickable = false
        binding.toolbarSettingsIcon.isFocusable = false

        binding.bannerView.isClickable = false
        binding.bannerView.isFocusable = false
    }

    private fun enableFocus() {
        binding.toolbarSettingsIcon.isClickable = true
        binding.toolbarSettingsIcon.isFocusable = true

        binding.bannerView.isClickable = true
        binding.bannerView.isFocusable = true
    }

    private fun isSettingsMenuVisible(): Boolean {
        return binding.settingsMenu.root.isVisible ?: false
    }

    override fun onBackPressed() {
        if (isSettingsMenuVisible()) {
            closeSettingsPanel()
        } else {
            super.onBackPressed()
        }
    }
}