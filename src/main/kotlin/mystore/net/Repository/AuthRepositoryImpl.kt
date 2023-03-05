package mystore.net.Repository

import mystore.net.Response.AuthResponse
import mystore.net.Requests.CreateSuperUserParams
import mystore.net.Security.JwtConfig
import mystore.net.Requests.CreateuserParams
import mystore.net.Requests.UserLoginParams
import mystore.net.Service.AuthService

class AuthRepositoryImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun Registeruser(params: CreateuserParams): AuthResponse<Any> {
       return if (isEmailexist(params.email)){
            AuthResponse.ErrorResponse(error = true, message = "${params.email} Email Already Exist")
        }
       else if (isPhoneExist(params.phone)){
            AuthResponse.ErrorResponse(error = true, message = "This phone Number Already Exist")
       }
       else{
            val user=authService.Createuser(params)
           if (user !=null){
             val token= JwtConfig.instance.createAccessToken(user.userid)
               user.authtoken=token
               AuthResponse.SuccessResponse(error = false, message = "Register success", userid = user.userid.toString(), username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken = token)
           }
           else{
               AuthResponse.ErrorResponse(error = true, message = "something went wrong")
           }
       }
    }

    override suspend fun Loginuser(params: UserLoginParams): AuthResponse<Any> {
       val user=authService.Loginuser(params.email,params.password)


        return if (!isEmailexist(params.email)){
            AuthResponse.ErrorResponse(error = true, message = "Email ${params.email} doesnot exist")

        }


      else  if (user!=null){
            val token=JwtConfig.instance.createAccessToken(user.userid)
            user.authtoken=token
            AuthResponse.SuccessResponse(error = false, message = "Login success", userid = user.userid.toString(), username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken =user.authtoken)
        }
        else{
            AuthResponse.ErrorResponse(error = true, message = "Invalid password")
        }
    }

    override suspend fun Createsuperuser(params: CreateSuperUserParams): AuthResponse<Any> {
        return if (isEmailexist(params.email)){
            AuthResponse.ErrorResponse(error = true, message = "${params.email} Email Already Exist")
        }

        else{
            val user=authService.Createsuperusr(params)
            if (user !=null){
                val token= JwtConfig.instance.createAccessToken(user.userid)
                user.authtoken=token
                AuthResponse.SuccessResponse(error = false, message = "Register success", userid = user.userid.toString(), username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken = token)
            }
            else{
                AuthResponse.ErrorResponse(error = true, message = "something went wrong")
            }
        }
    }

    override suspend fun Loginsuperuser(params: UserLoginParams): AuthResponse<Any> {
        val user=authService.Loginsuperuser(params.email,params.password)


        return if (!isEmailexist(params.email)){
            AuthResponse.ErrorResponse(error = true, message = "Email ${params.email} for superuser doesnot exist")
        }

        else  if (user!=null){
            val token=JwtConfig.instance.createAccessToken(user.userid)
            user.authtoken=token
            AuthResponse.SuccessResponse(error = false, message = "superuser Login success", userid = user.userid.toString(), username = user.username, email = user.email, phone = user.phone, city = user.city, createdAt = user.createdAt,is_superuser =user.is_superuser,authtoken =user.authtoken)
        }
        else{
            AuthResponse.ErrorResponse(error = true, message = "Invalid password or you are not a superuser")
        }
    }

    private suspend fun isEmailexist(email: String):Boolean{

        return authService.findUserByEmail(email)!=null
    }
private suspend fun isPhoneExist(phone:String):Boolean{
   return authService.findUserphone(phone)!=null
}
}