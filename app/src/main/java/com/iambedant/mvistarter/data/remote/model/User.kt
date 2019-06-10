package com.iambedant.mvistarter.data.remote.model

import com.google.gson.annotations.SerializedName

data class User (

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("username")
        val username: String? = null,

        @field:SerializedName("email")
        val email: String? = null,

        @field:SerializedName("address")
        val address: Address,

        @field:SerializedName("phone")
        val phone: String? = null,

        @field:SerializedName("website")
        val website: String? = null,

        @field:SerializedName("company")
        val company: Company
        )

data class Address (
        @field:SerializedName("street")
        val street: Int? = null,

        @field:SerializedName("suite")
        val suite: String? = null,

        @field:SerializedName("city")
        val city: String? = null,

        @field:SerializedName("zipcode")
        val zipcode: Int? = null,

        @field:SerializedName("geo")
        val geo: Geo
)
data class Geo(
        @field:SerializedName("lat")
        val lat: Int? = null,

        @field:SerializedName("lng")
        val lng: String? = null
)
data class Company(
        @field:SerializedName("name")
        val name: Int? = null,

        @field:SerializedName("catchPhrase")
        val catchPhrase: String? = null,

        @field:SerializedName("bs")
        val bs: String? = null
)