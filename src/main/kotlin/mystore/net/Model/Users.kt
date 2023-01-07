package mystore.net.Model

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
