package com.production.teman.minesweeper_legacy.fragments

import android.content.Context
import android.content.Intent
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
import com.production.teman.minesweeper_legacy.fourthLayer.GameFieldActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var checkBoxSquare: CheckBox
private lateinit var checkBoxHex: CheckBox
private lateinit var checkBoxTriangle: CheckBox

private lateinit var textWidth: TextView
private lateinit var buttonWidthAdd: FloatingActionButton
private lateinit var buttonWidthSub: FloatingActionButton
private lateinit var textHeight: TextView
private lateinit var buttonHeightAdd: FloatingActionButton
private lateinit var buttonHeightSub: FloatingActionButton
private var fieldWidth: Int = 9
private var fieldHeight: Int = 9

private var wAutoIncrement: Boolean = false
private var wAutoDecrement: Boolean = false
private var hAutoIncrement: Boolean = false
private var hAutoDecrement: Boolean = false
private var REP_DELAY: Long = 50
private val repeatUpdateHandler = Handler()

private var gameFieldActivity: GameFieldActivity = GameFieldActivity()

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

        var rootView: View = inflater.inflate(R.layout.fragment_sandbox, container, false)

        fieldWidth = 9
        fieldHeight = 9

        checkBoxSquare = rootView.findViewById(R.id.checkBoxSquare)
        checkBoxHex = rootView.findViewById(R.id.checkBoxHex)
        checkBoxTriangle = rootView.findViewById(R.id.checkBoxTriangle)

        checkBoxSquare.isChecked = true

        checkBoxSquare.setOnClickListener { view ->
            checkBoxSquare.isChecked = true
            checkBoxHex.isChecked = false
            checkBoxTriangle.isChecked = false
        }
        checkBoxHex.setOnClickListener { view ->
            checkBoxSquare.isChecked = false
            checkBoxHex.isChecked = true
            checkBoxTriangle.isChecked = false
        }
        checkBoxTriangle.setOnClickListener { view ->
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
        buttonWidthAdd.setOnClickListener { view ->
            widthIncrement()
            fieldParamsCheck()
        }
        buttonWidthSub.setOnClickListener { view ->
            widthDecrement()
            fieldParamsCheck()
        }
        buttonHeightAdd.setOnClickListener { view ->
            heightIncrement()
            fieldParamsCheck()
        }
        buttonHeightSub.setOnClickListener { view ->
            heightDecrement()
            fieldParamsCheck()
        }

        //long
        buttonWidthAdd.setOnLongClickListener { view ->
            wAutoIncrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonWidthSub.setOnLongClickListener { view ->
            wAutoDecrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonHeightAdd.setOnLongClickListener { view ->
            hAutoIncrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        buttonHeightSub.setOnLongClickListener { view ->
            hAutoDecrement = true
            repeatUpdateHandler.post(RptUpdater())
        }
        //onTouch
        buttonWidthAdd.setOnTouchListener( object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        wAutoIncrement = false
                        fieldParamsCheck()
                    }
                    MotionEvent.ACTION_POINTER_DOWN -> fieldParamsCheck()
                    MotionEvent.ACTION_CANCEL -> wAutoIncrement = false
                }
                return false
            }
        })
        buttonWidthSub.setOnTouchListener( object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        wAutoDecrement = false
                        fieldParamsCheck()
                    }
                    MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                    MotionEvent.ACTION_CANCEL -> wAutoDecrement = false
                }
                return false
            }
        })
        buttonHeightAdd.setOnTouchListener( object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        hAutoIncrement = false
                        fieldParamsCheck()
                    }
                    MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                    MotionEvent.ACTION_CANCEL -> hAutoIncrement = false
                }
                return false
            }
        })
        buttonHeightSub.setOnTouchListener( object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_UP -> {
                        hAutoDecrement = false
                        fieldParamsCheck()
                    }
                    MotionEvent.ACTION_DOWN -> fieldParamsCheck()
                    MotionEvent.ACTION_CANCEL -> hAutoDecrement = false
                }
                return false
            }
        })

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
