package com.karas.testtask

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.karas.testtask.db.data.BootsEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var bootEventsTextView: TextView
    private lateinit var totalDismissalsEditText: EditText
    private lateinit var intervalEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        // Observe boot events
        mainViewModel.bootEvents.observe(this, Observer { bootEvents ->
            displayBootEvents(bootEvents)
        })

        // Observe dismiss count
        mainViewModel.dismissCount.observe(this, Observer { dismissCount ->
            totalDismissalsEditText.setText(dismissCount.toString())
        })

        mainViewModel.dismissInterval.observe(this, Observer {
            intervalEditText.setText(it.toString())
        })

        // Load data
        mainViewModel.loadDismissInterval()
        mainViewModel.loadBootEvents()
        mainViewModel.loadDismissCount()

//        saveButton.setOnClickListener {
//            val count = totalDismissalsEditText.text.toString().toIntOrNull() ?: 0
//            mainViewModel.saveDismissCount(count)
//        }
    }

    private fun initViews() {
        totalDismissalsEditText = findViewById(R.id.totalDismissalsEditText)
        intervalEditText = findViewById(R.id.intervalEditText)
        bootEventsTextView = findViewById(R.id.bootEventsTextView)
    }

    private fun displayBootEvents(bootEvents: List<BootsEntity>) {
        if (bootEvents.isEmpty()) {
            bootEventsTextView.text = "No boots detected"
        } else {
            bootEventsTextView.text = bootEvents.joinToString("\n") { event ->
                "${formatDate(event.timeStamp)} - ${event.timeStamp}"
            }
        }
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        return sdf.format(java.util.Date(timestamp))
    }
}