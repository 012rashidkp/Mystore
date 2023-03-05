package mystore.net.Requests

data class CreateSuperUserParams(

    val email:String,
    val password:String,
    val username:String?="Admin",
    val phone:String?="Admin phone",
    val city:String?="Admin city",
    val is_superuser:Boolean=false
)
