package mystore.net.Repository

import mystore.net.Model.BaseResponse
import mystore.net.Security.JwtConfig
import mystore.net.Service.CreateuserParams
import mystore.net.Service.UserService

class UserRepositoryImpl(private val userService: UserService) : UserRepository {
    override suspend fun Registeruser(params: CreateuserParams): BaseResponse<Any> {
       return if (isEmailexist(params.email)){
            BaseResponse.ErrorResponse(error = true, message = "This Email Already Exist")
        }
        else if (isPhoneExist(params.phone)){
            BaseResponse.ErrorResponse(error = true, message = "This phone Number Already Exist")
       }
       else{
            val user=userService.Createuser(params)
           if (user !=null){
             val token= JwtConfig.instance.createAccessToken(user.userid)
               user.authtoken=token
               BaseResponse.SuccessResponse(error = false, message = "Register success", userid = user.userid, username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser = false,authtoken = token)
           }
           else{
               BaseResponse.ErrorResponse(error = true, message = "something went wrong")
           }
       }
    }

    override suspend fun Loginuser(email: String, password: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isEmailexist(email: String):Boolean{

        return userService.findUserByEmail(email)!=null
    }
private suspend fun isPhoneExist(phone:String):Boolean{
   return userService.findUserByEmail(phone)!=null
}
}