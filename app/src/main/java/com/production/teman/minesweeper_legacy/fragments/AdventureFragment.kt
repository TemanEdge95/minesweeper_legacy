package com.production.teman.minesweeper_legacy.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.fourthLayer.GameFieldActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var buttonDone: FloatingActionButton

private var gameFieldActivity: GameFieldActivity = GameFieldActivity()

class AdventureFragment : Fragment() {
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
        var rootView: View = inflater.inflate(R.layout.fragment_adventure, container, false)

        buttonDone = rootView.findViewById(R.id.floatingButtonAdventure)
        buttonDone.setOnClickListener { view ->
            gameFieldActivity.setFieldParams(
                    "Adventure",
                    "None",
                    0,
                    0)

            context!!.startActivity(Intent(context, GameFieldActivity::class.java))
        }

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
