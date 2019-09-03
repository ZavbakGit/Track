package `fun`.gladkikh.app.track.presentation

import `fun`.gladkikh.app.track.model.Line
import android.graphics.Color
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Polyline

fun mapLineToPolyline(line: Line): Polyline {
    val polyline = Polyline()
    polyline.width = 10.0f

    polyline.setPoints(
        listOf(
            GeoPoint(line.start.latitude, line.start.longitude),
            GeoPoint(line.finish.latitude, line.finish.longitude)
        )
    )

    polyline.color =
        when {
            line.speed <= 5 -> Color.parseColor("#40E0D0")
            line.speed > 5 && line.speed <= 10 -> Color.parseColor("#008000")
            else -> Color.parseColor("#FFFF00")
        }


    return polyline
}