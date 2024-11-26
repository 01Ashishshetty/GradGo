package com.example.gradgo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.ChipGroup

class ConnectionsActivity : AppCompatActivity() {

    data class Connection(
        val id: Int,
        val name: String,
        val title: String,
        val date: String,
        val imageUrl: String,
        val type: String
    )

    private val dummyConnections = listOf(
        Connection(1, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image1.jpg", "Programmer"),
        Connection(2, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image2.jpg", "Developer"),
        Connection(3, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image3.jpg", "Programmer"),
        Connection(4, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image4.jpg", "Developer"),
        Connection(5, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image5.jpg", "Programmer"),
        Connection(6, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image6.jpg", "Developer"),
        Connection(7, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image7.jpg", "Programmer"),
        Connection(8, "Oracle shreekara", "associate engineer", "MAR 2023",
            "https://example.com/image8.jpg", "Developer")
    )

    private lateinit var adapter: ConnectionsAdapter
    private var currentFilter = "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_people)

        setupRecyclerView()
        setupChipGroup()
    }

    private fun setupRecyclerView() {
        adapter = ConnectionsAdapter(getFilteredConnections())
        findViewById<RecyclerView>(R.id.connectionsRecyclerView).apply {
            layoutManager = GridLayoutManager(this@ConnectionsActivity, 2)
            adapter = this@ConnectionsActivity.adapter
        }
    }

    private fun setupChipGroup() {
        findViewById<ChipGroup>(R.id.filterChipGroup).setOnCheckedChangeListener { _, checkedId ->
            currentFilter = when (checkedId) {
                R.id.chipConnections -> "my connections"
                R.id.chipProgrammer -> "Programmer"
                R.id.chipDeveloper -> "Developer"
                else -> "All"
            }
            adapter.updateConnections(getFilteredConnections())
        }
    }

    private fun getFilteredConnections(): List<Connection> {
        return when (currentFilter) {
            "All" -> dummyConnections
            "my connections" -> dummyConnections // In a real app, this would filter for user's connections
            else -> dummyConnections.filter { it.type == currentFilter }
        }
    }

    inner class ConnectionsAdapter(
        private var connections: List<Connection>
    ) : RecyclerView.Adapter<ConnectionsAdapter.ViewHolder>() {

        fun updateConnections(newConnections: List<Connection>) {
            connections = newConnections
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
            val nameText: TextView = itemView.findViewById(R.id.nameText)
            val titleText: TextView = itemView.findViewById(R.id.titleText)
            val dateText: TextView = itemView.findViewById(R.id.dateText)
            val connectButton: MaterialButton = itemView.findViewById(R.id.connectButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_connection, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val connection = connections[position]

            Glide.with(holder.itemView.context)
                .load(connection.imageUrl)
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder)
                .into(holder.profileImage)

            holder.nameText.text = connection.name
            holder.titleText.text = connection.title
            holder.dateText.text = connection.date

            holder.connectButton.setOnClickListener {
                // Handle connect button click
                Toast.makeText(
                    holder.itemView.context,
                    "Connected with ${connection.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun getItemCount() = connections.size
    }
}