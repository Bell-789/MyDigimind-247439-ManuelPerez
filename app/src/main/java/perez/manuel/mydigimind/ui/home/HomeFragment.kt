package perez.manuel.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import perez.manuel.mydigimind.R
import perez.manuel.mydigimind.databinding.FragmentHomeBinding
import perez.manuel.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas? = null
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if (first) {
            fill()
            first = false
        }

        val gridView: GridView = root.findViewById(R.id.gridview)
        adaptador = AdaptadorTareas(root.context, tasks)
        gridView.adapter = adaptador

        return root
    }

    private fun fill() {
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday", "Sunday"), "17:00"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "10:00"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))


    }

    private class AdaptadorTareas(contexto: Context, task: ArrayList<Task>) : BaseAdapter() {
        private val tasks = task
        private val inflador: LayoutInflater = LayoutInflater.from(contexto)

        override fun getCount(): Int = tasks.size
        override fun getItem(position: Int): Any = tasks[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val vista = convertView ?: inflador.inflate(R.layout.task_view, parent, false)

            val tvTitle: TextView = vista.findViewById(R.id.tv_title)
            val tvTime: TextView = vista.findViewById(R.id.tv_time)
            val tvDays: TextView = vista.findViewById(R.id.tv_days)

            val task = tasks[position]
            tvTitle.text = task.title
            tvTime.text = task.time
            tvDays.text = task.days.joinToString(", ")

            return vista
        }
    }
}