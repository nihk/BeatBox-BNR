package rosenich.beatbox

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class BeatBoxFragment : Fragment() {
    private lateinit var beatBox: BeatBox

    companion object {
        fun newInstance() = BeatBoxFragment()
        const val NUM_COLUMNS = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_beat_box, container, false)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.fragment_beat_box_recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(activity, NUM_COLUMNS)
        recyclerView?.adapter = SoundAdapter(beatBox.sounds)

        return view
    }

    private class SoundHolder(inflater: LayoutInflater?, container: ViewGroup?)
        : RecyclerView.ViewHolder(inflater?.inflate(R.layout.list_item_sound, container, false)) {

        private val button = itemView.findViewById<Button>(R.id.list_item_sound_button)
        private lateinit var sound: Sound

        fun bindSound(sound: Sound) {
            this.sound = sound
            button.text = this.sound.name
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
                SoundHolder(LayoutInflater.from(activity), parent)

        override fun onBindViewHolder(holder: SoundHolder?, position: Int) {
            holder?.bindSound(sounds[position])
        }

        override fun getItemCount() = sounds.size
    }
}