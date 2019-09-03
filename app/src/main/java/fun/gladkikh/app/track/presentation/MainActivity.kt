package `fun`.gladkikh.app.track.presentation

import `fun`.gladkikh.app.track.R
import `fun`.gladkikh.app.track.interactor.RequestInteractor
import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint


class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        setContentView(R.layout.activity_main)
        map.setTileSource(TileSourceFactory.MAPNIK)

        RequestInteractor().getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.forEach { line ->
                    map.overlayManager.add(mapLineToPolyline(line))
                }

                val mapController = map.controller
                mapController.setZoom(16.5)

                it.take(1).forEach {
                    mapController.setCenter(GeoPoint(it.start.latitude, it.start.longitude))
                }

            }, {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            })
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}
