package uk.co.alt236.bluetoothconnectionlog.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import uk.co.alt236.bluetoothconnectionlog.R
import uk.co.alt236.bluetoothconnectionlog.db.entities.LogEntry
import uk.co.alt236.bluetoothconnectionlog.ui.LogEntryViewModel

class DeviceDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_device_detail, container, false)
        val recycler = rootView.findViewById<RecyclerView>(R.id.item_list)
        val adapter = LogRecyclerAdapter(activity!!)

        recycler.adapter = adapter

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                val macAddress = it.getString(ARG_ITEM_ID)!!
                val deviceViewModel = ViewModelProviders.of(this).get(LogEntryViewModel::class.java)

                deviceViewModel.getLogForDevice(macAddress).observe(this,
                    Observer<List<LogEntry>> { data -> adapter.setData(data) })
            }
        } ?: throw IllegalArgumentException("No $ARG_ITEM_ID was provided.")

        return rootView
    }

    private fun dumpData(
        data: List<LogEntry>,
        textView: TextView
    ) {
        val sb = StringBuilder()
        for (item in data) {
            sb.append(item)
            sb.append('\n')
        }

        textView.text = sb.toString()
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
