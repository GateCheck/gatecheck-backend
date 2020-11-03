package com.gatecheck.gatecheck.api

//import com.gatecheck.gatecheck.api.template.DefaultUserResponse
import com.gatecheck.gatecheck.model.Request
import com.gatecheck.gatecheck.service.RequestService
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/request")
class RequestController @Autowired constructor(private val requestService:RequestService, private val userService: UserService){
    @GetMapping
    fun GetRequests(@RequestParam(required=false) messages:Boolean=false,
    @RequestParam(required=false) amount:Int=10,
    @RequestParam(required=false) index:Int=0): DefaultRequestResponse{
        val user:User=userService.getUser()
        if(user!=null){
            return DefaultRequestResponse(true,null,requestService.GetRequests(user.id,messages,amount,index))
        }
        TODO("not implemented yet")
    }

    @PostMapping AddRequest (@RequestBody request:Request):DefaultRequestResponse{
        val user:User=userService.getUser()
        if(user!=null){
            request.sender=user.id
            return requestService.AddRequest(request)
        }
        TODO("not yet implemented")
    }
}