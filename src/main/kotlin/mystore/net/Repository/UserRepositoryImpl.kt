package mystore.net.Repository

import mystore.net.Model.BaseResponse
import mystore.net.Security.JwtConfig
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams
import mystore.net.Service.UserService

class UserRepositoryImpl(private val userService: UserService) : UserRepository {
    override suspend fun Registeruser(params: CreateuserParams): BaseResponse<Any> {
       return if (isEmailexist(params.email)){
            BaseResponse.ErrorResponse(error = true, message = "${params.email} Email Already Exist")
        }
       else if (isPhoneExist(params.phone)){
            BaseResponse.ErrorResponse(error = true, message = "This phone Number Already Exist")
       }
       else{
            val user=userService.Createuser(params)
           if (user !=null){
             val token= JwtConfig.instance.createAccessToken(user.userid)
               user.authtoken=token
               BaseResponse.SuccessResponse(error = false, message = "Register success", userid = user.userid, username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken = token)
           }
           else{
               BaseResponse.ErrorResponse(error = true, message = "something went wrong")
           }
       }
    }

    override suspend fun Loginuser(params: UserLoginParams): BaseResponse<Any> {
       val user=userService.Loginuser(params.email,params.password)
        return if (user!=null){
            val token=JwtConfig.instance.createAccessToken(user.userid)
            user.authtoken=token
            BaseResponse.SuccessResponse(error = false, message = "Login success", userid = user.userid, username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken =user.authtoken)
        }
        else{
            BaseResponse.ErrorResponse(error = true, message = "Login failed")
        }
    }

    private suspend fun isEmailexist(email: String):Boolean{

        return userService.findUserByEmail(email)!=null
    }
private suspend fun isPhoneExist(phone:String):Boolean{
   return userService.findUserphone(phone)!=null
}
}