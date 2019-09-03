package `fun`.gladkikh.app.track.interactor

import `fun`.gladkikh.app.track.model.Line
import `fun`.gladkikh.app.track.model.Point
import `fun`.gladkikh.app.track.net.AvionicusApiService
import io.reactivex.Flowable

class RequestInteractor {
    private val api: AvionicusApiService = AvionicusApiService.create()
    fun getData(): Flowable<List<Line>> {
        return api.getDataFromServer()
            .map {
                it.points.map { list ->
                    Point(
                        latitude = list[0].toDouble(),
                        longitude = list[1].toDouble(),
                        speed = list[5].toDouble()
                    )
                }.toList()
            }
            .map {
                it.drop(1)
                    .mapIndexed { index, point ->
                        Line(
                            start = it[index],
                            finish = point,
                            speed = point.speed
                        )
                    }
            }
    }
}