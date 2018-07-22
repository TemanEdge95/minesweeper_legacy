package com.production.teman.minesweeper_legacy.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.adapters.ClassicModeAdapter

private lateinit var snapHelper: LinearSnapHelper
private lateinit var modeList: Array<String>
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ClassicFragment : Fragment() {
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

        val rootView: View = inflater.inflate(R.layout.fragment_classic, container, false)

        modeList = arrayOf("9x9", "12x12", "15x15", "20x20")

        val rv: RecyclerView = rootView.findViewById(R.id.classicRecycler)
        rv.layoutManager = GridLayoutManager(activity, 2)
        rv.adapter = ClassicModeAdapter(modeList, activity!!)

        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv)

        rv.scrollToPosition(0)

        return rootView
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
