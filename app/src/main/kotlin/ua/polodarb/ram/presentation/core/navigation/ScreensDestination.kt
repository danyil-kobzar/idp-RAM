package ua.polodarb.ram.presentation.core.navigation

internal sealed class ScreensDestination(
    private val root: String,
    vararg val params: String,
) {

    val route: String
        get() = buildString {
            append(root)
            for (p in params) {
                append("/{$p}")
            }
        }

    fun getNavDirection(vararg params: String) = buildString {
        append(root)
        for (p in params) {
            append("/$p")
        }
    }

    data object Root : ScreensDestination("root")
    data object CharacterDetails : ScreensDestination("character_details", "characterId")
    data object EpisodeDetails : ScreensDestination("episode_details", "episodeId")
}
