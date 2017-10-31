package rosenich.beatbox

import android.content.Context
import android.util.Log
import java.io.IOException

class BeatBox(context: Context) {

    private val assets = context.assets!!
    val sounds = mutableListOf<Sound>()

    init {
        loadSounds()
    }

    companion object {
        private const val TAG: String = "BeatBox"
        private const val SOUNDS_FOLDER: String = "sample_sounds"
    }

    private fun loadSounds() {
        try {
            assets.list(SOUNDS_FOLDER)!!.let {
                Log.i(TAG, "Found ${it.size} sounds")
                it.forEach {
                    val assetPath = "$SOUNDS_FOLDER / $it"
                    sounds.add(Sound(assetPath))
                }
            }
        } catch (ioe: IOException) {
            Log.e(TAG, "Could not list assets", ioe)
        }
    }
}