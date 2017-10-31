package rosenich.beatbox

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val NUM_COLUMNS = 3

class BeatBoxFragment : Fragment() {
    private val beatBox: BeatBox by lazy { BeatBox(activity) }

    companion object {
        fun newInstance() = BeatBoxFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_beat_box, container, false)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.fragment_beat_box_recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(activity, NUM_COLUMNS)
        recyclerView?.adapter = SoundAdapter(beatBox.sounds)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    private inner class SoundHolder(inflater: LayoutInflater?, container: ViewGroup?)
        : RecyclerView.ViewHolder(inflater?.inflate(R.layout.list_item_sound, container, false))
        , View.OnClickListener {

        private val button = itemView.findViewById<Button>(R.id.list_item_sound_button)
        private lateinit var sound: Sound

        init {
            button.setOnClickListener(this)
        }

        fun bindSound(sound: Sound) {
            this.sound = sound
            button.text = this.sound.name
        }

        override fun onClick(v: View?) {
            beatBox.play(sound)
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