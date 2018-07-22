package com.production.teman.minesweeper_legacy.fragments

import android.content.Context
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.production.teman.minesweeper_legacy.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var fieldWidth: Int = 9
private var fieldHeight: Int = 9
private var REP_DELAY: Long = 50
private val repeatUpdateHandler = Handler()
private var wAutoIncrement: Boolean = false
private var wAutoDecrement: Boolean = false
private var hAutoIncrement: Boolean = false
private var hAutoDecrement: Boolean = false
private lateinit var textWidth: TextView
private lateinit var textHeight: TextView
private lateinit var buttonWidthAdd: FloatingActionButton
private lateinit var buttonWidthSub: FloatingActionButton
private lateinit var buttonHeightAdd: FloatingActionButton
private lateinit var buttonHeightSub: FloatingActionButton

internal class RptUpdater : Runnable {

    private var sbf: SandboxFragment = SandboxFragment()


    override fun run() {
        if (wAutoIncrement) {
            sbf.widthIncrement()
            if ( fieldWidth != 30 ) repeatUpdateHandler.postDelayed(RptUpdater(), REP_DELAY)
        } else if (wAutoDecrement) {
            sbf.widthDecrement()
            if ( fieldWidth != 5 )repeatUpdateHandler.postDelayed(RptUpdater(), REP_DELAY)
        }

        if (hAutoIncrement) {
            sbf.heightIncrement()
            if ( fieldHeight != 30 )repeatUpdateHandler.postDelayed(RptUpdater(), REP_DELAY)
        } else if (hAutoDecrement) {
            sbf.heightDecrement()
            if ( fieldHeight != 5 )repeatUpdateHandler.postDelayed(RptUpdater(), REP_DELAY)
        }
    }
}

class SandboxFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_sandbox, container, false)

        fieldWidth = 9
        fieldHeight = 9

        val checkBoxSquare: CheckBox = rootView.findViewById(R.id.checkBoxSquare)
        val checkBoxHex: CheckBox = rootView.findViewById(R.id.checkBoxHex)
        val checkBoxTriangle: CheckBox = rootView.findViewById(R.id.checkBoxTriangle)

        checkBoxSquare.isChecked = true

        checkBoxSquare.setOnClickListener {
            checkBoxSquare.isChecked = true
            checkBoxHex.isChecked = false
            checkBoxTriangle.isChecked = false
        }
        checkBoxHex.setOnClickListener {
            checkBoxSquare.isChecked = false
            checkBoxHex.isChecked = true
            checkBoxTriangle.isChecked = false
        }
        checkBoxTriangle.setOnClickListener {
            checkBoxSquare.isChecked = false
            checkBoxHex.isChecked = false
            checkBoxTriangle.isChecked = true
        }

        textWidth = rootView.findViewById(R.id.textViewWidth)
        textHeight = rootView.findViewById(R.id.textViewHeight)
        textWidth.text = "" + fieldWidth
        textHeight.text = "" + fieldHeight

        buttonWidthAdd = rootView.findViewById(R.id.buttonWidthAdd)
        buttonWidthSub = rootView.findViewById(R.id.buttonWidthSub)
        buttonHeightAdd = rootView.findViewById(R.id.buttonHeightAdd)
        buttonHeightSub = rootView.findViewById(R.id.buttonHeightSub)

        //short
        buttonWidthAdd.setOnClickListener {
            widthIncrement()
            fieldParamsCheck()
        }
        buttonWidthSub.setOnClickListener {
            widthDecrement()
            fieldParamsCheck()
        }
        buttonHeightAdd.setOnClickListener {
            heightIncrement()
            fieldParamsCheck()
        }
        buttonHeightSub.setOnClickListener {
            heightDecrement()
            fieldParamsCheck()
        }

        //long
        buttonWidthAdd.setOnLongClickListener {
            wAutoIncrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonWidthSub.setOnLongClickListener {
            wAutoDecrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonHeightAdd.setOnLongClickListener {
            hAutoIncrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonHeightSub.setOnLongClickListener {
            hAutoDecrement = true
            repeatUpdateHandler.post(RptUpdater())
        }

        //touch
        buttonWidthAdd.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    wAutoIncrement = false
                    fieldParamsCheck()
                }
                MotionEvent.ACTION_POINTER_DOWN -> fieldParamsCheck()
                MotionEvent.ACTION_CANCEL -> wAutoIncrement = false
            }
            false
        }
        buttonWidthSub.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    wAutoDecrement = false
                    fieldParamsCheck()
                }
                MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                MotionEvent.ACTION_CANCEL -> wAutoDecrement = false
            }
            false
        }
        buttonHeightAdd.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    hAutoIncrement = false
                    fieldParamsCheck()
                }
                MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                MotionEvent.ACTION_CANCEL -> hAutoIncrement = false
            }
            false
        }
        buttonHeightSub.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    hAutoDecrement = false
                    fieldParamsCheck()
                }
                MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                MotionEvent.ACTION_CANCEL -> hAutoDecrement = false
            }
            false
        }

        return rootView
    }

    fun widthIncrement() {
        fieldWidth++
        textWidth.text = "" + fieldWidth
    }
    fun widthDecrement() {
        fieldWidth--
        textWidth.text = "" + fieldWidth
    }
    fun heightIncrement() {
        fieldHeight++
        textHeight.text = "" + fieldHeight
    }
    fun heightDecrement() {
        fieldHeight--
        textHeight.text = "" + fieldHeight
    }

    fun fieldParamsCheck() {
        buttonWidthAdd.isEnabled = fieldWidth != 30
        buttonWidthSub.isEnabled = fieldWidth != 5

        buttonHeightAdd.isEnabled = fieldHeight != 30
        buttonHeightSub.isEnabled = fieldHeight != 5

        when {
            fieldWidth != 30 -> {
                buttonWidthAdd.background.clearColorFilter()
                buttonWidthAdd.clearColorFilter()
            }
            else -> {
                buttonWidthAdd.background.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
                buttonWidthAdd.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
            }
        }
        when {
            fieldWidth != 5 -> {
                buttonWidthSub.background.clearColorFilter()
                buttonWidthSub.clearColorFilter()
            }
            else -> {
                buttonWidthSub.background.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
                buttonWidthSub.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
            }
        }
        when {
            fieldHeight != 30 -> {
                buttonHeightAdd.background.clearColorFilter()
                buttonHeightAdd.clearColorFilter()
            }
            else -> {
                buttonHeightAdd.background.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
                buttonHeightAdd.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
            }
        }
        when {
            fieldHeight != 5 -> {
                buttonHeightSub.background.clearColorFilter()
                buttonHeightSub.clearColorFilter()
            }
            else -> {
                buttonHeightSub.background.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
                buttonHeightSub.setColorFilter(ContextCompat.getColor(activity!!, R.color.background), PorterDuff.Mode.MULTIPLY)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    fun getFieldWidth() : Int {
        return fieldWidth
    }

    fun getFieldHeight(): Int {
        return fieldHeight
    }
}
