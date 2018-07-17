package com.production.teman.minesweeper_legacy.Support

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.FloatingActionButton
import android.view.View
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.secondLayer.SettingsActivity
import android.support.design.widget.BottomSheetDialog
import android.view.Gravity
import android.widget.Toast
import com.production.teman.minesweeper_legacy.firstLayer.MainActivity


private lateinit var contextSaved: Context
private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
private var backToMenuFlag: Boolean = false

private lateinit var returnToast: Toast

class BottomSheetPauseMenu: BottomSheetDialogFragment(), View.OnClickListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        val view = View.inflate(context, R.layout.bottom_sheet_pause_menu, null)

        var buttonMenu: FloatingActionButton = view.findViewById(R.id.floatingActionButtonMenu)
        var buttonSettings: FloatingActionButton = view.findViewById(R.id.floatingActionButtonSettings)
        var buttonUnpause: FloatingActionButton = view.findViewById(R.id.floatingActionButtonUnpause)

        buttonMenu.setOnClickListener(this)
        buttonSettings.setOnClickListener(this)
        buttonUnpause.setOnClickListener(this)

        dialog.setContentView(view)

        bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        backToMenuFlag = false
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        contextSaved = context!!
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingActionButtonMenu -> {

                if (!backToMenuFlag) {
                    backToMenuFlag = true
                    returnToast = Toast.makeText(contextSaved, R.string.toastReturn, Toast.LENGTH_SHORT)
                    returnToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 550)
                    returnToast.show()
                }
                else {
                    backToMenuFlag = false
                    dismiss()
                    startActivity(Intent(contextSaved, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
            R.id.floatingActionButtonSettings -> {
                startActivity(Intent(contextSaved, SettingsActivity::class.java))
            }
            R.id.floatingActionButtonUnpause -> {
                backToMenuFlag = false
                dismiss()
            }
        }
    }
}