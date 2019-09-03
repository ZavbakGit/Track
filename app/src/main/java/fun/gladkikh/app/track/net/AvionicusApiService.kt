package `fun`.gladkikh.app.track.net


import `fun`.gladkikh.app.track.model.ResponseAvionicus
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AvionicusApiService {

    @GET("track_v0649.php?avkey=1M1TE9oeWTDK6gFME9JYWXqpAGc%3D&hash=58ecdea2a91f32aa4c9a1d2ea010adcf2348166a04&track_id=36131&user_id=22")
    fun getDataFromServer(
    ): Flowable<ResponseAvionicus>


    companion object Factory {
        fun create(): AvionicusApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://avionicus.com/android/")
                .build()

            return retrofit.create(AvionicusApiService::class.java)
        }
    }

}