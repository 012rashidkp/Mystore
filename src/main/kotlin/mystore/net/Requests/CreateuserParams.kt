package mystore.net.Requests

data class CreateuserParams(
    val username:String,
    val email:String,
    val phone:String,
    val city:String,
    val password:String,
    val is_superuser:Boolean=false
)
