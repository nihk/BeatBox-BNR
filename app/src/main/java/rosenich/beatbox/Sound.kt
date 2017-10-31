package rosenich.beatbox

class Sound(val assetPath: String) {
    val name: String

    init {
        val components = assetPath.split("/")
        val filename = components.last()
        name = filename.replace(".wav", "")
    }
}