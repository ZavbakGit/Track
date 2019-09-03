package `fun`.gladkikh.app.track.model

import com.google.gson.annotations.SerializedName

data class ResponseAvionicus(
    @SerializedName("aPoints") val points:List<List<String>>
)

