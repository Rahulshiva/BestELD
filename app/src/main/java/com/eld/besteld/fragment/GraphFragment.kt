package com.eld.besteld.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.eld.besteld.R
import com.eld.besteld.paint.DataPoint
import com.eld.besteld.paint.DrawView
import kotlinx.android.synthetic.main.fragment_graph.*
import java.util.*


class GraphFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        graph.setData(generateRandomDataPoints())

        /* drawView = DrawView(context);
         drawView!!.setBackgroundColor(Color.WHITE);
 */
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context

    }
    private fun generateRandomDataPoints(): List<DataPoint> {
      /*  val random = Random()
        return (0..1).map {
            DataPoint(it, random.nextInt(2) + 4)
        }*/
        return listOf(DataPoint(0, 0),DataPoint(0, 0),DataPoint(30, 30),DataPoint(0, 30))

    }
}


