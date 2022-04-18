package com.end.endclothing.model

import com.google.gson.annotations.SerializedName

/**
 * Created by GandeepanS on 18/04/2022.
 */
class ProductsJSONObject (@SerializedName("products") var productObject: List<Products>)