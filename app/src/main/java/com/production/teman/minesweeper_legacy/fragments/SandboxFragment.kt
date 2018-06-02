package com.production.teman.minesweeper_legacy.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.production.teman.minesweeper_legacy.R

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

private lateinit var buttonDone: FloatingActionButton

private var sendParamsText: String = ""

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

        buttonWidthAdd.setOnClickListener { view ->
            fieldWidth++
            textWidth.text = "" + fieldWidth

            fieldParamsCheck()
        }
        buttonWidthSub.setOnClickListener { view ->
            fieldWidth--
            textWidth.text = "" + fieldWidth

            fieldParamsCheck()
        }
        buttonHeightAdd.setOnClickListener { view ->
            fieldHeight++
            textHeight.text = "" + fieldHeight

            fieldParamsCheck()
        }
        buttonHeightSub.setOnClickListener { view ->
            fieldHeight--
            textHeight.text = "" + fieldHeight

            fieldParamsCheck()
        }

        buttonDone = rootView.findViewById(R.id.floatingButtonSandbox)
        buttonDone.setOnClickListener { view ->
            sendParamsText = "" + fieldWidth + "x" + fieldHeight + ";"
            sendParamsText += when {
                checkBoxSquare.isChecked -> "Square"
                checkBoxHex.isChecked -> "Hex"
                else -> "Triangle"
            }

            Toast.makeText(activity, sendParamsText, Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    fun fieldParamsCheck() {
        if (fieldWidth != 30) buttonWidthAdd.visibility = View.VISIBLE
        else buttonWidthAdd.visibility = View.INVISIBLE

        if (fieldWidth != 5) buttonWidthSub.visibility = View.VISIBLE
        else buttonWidthSub.visibility = View.INVISIBLE

        if (fieldHeight != 30) buttonHeightAdd.visibility = View.VISIBLE
        else buttonHeightAdd.visibility = View.INVISIBLE

        if (fieldHeight != 5) buttonHeightSub.visibility = View.VISIBLE
        else buttonHeightSub.visibility = View.INVISIBLE
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
}
