package com.end.endclothing.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by GandeepanS on 18/04/2022.
 */
data class Products(

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("price")
    val price: String? = null,

    @Expose
    @SerializedName("image")
    val image: String? = null
)

