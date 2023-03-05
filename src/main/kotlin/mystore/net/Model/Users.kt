package mystore.net.Model

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Users(
    val userid:Int,
    val username:String,
    val email:String,
    val phone:String,
    val city:String,
    val createdAt:String,
    val is_superuser:Boolean,
    var authtoken:String?=null


)
