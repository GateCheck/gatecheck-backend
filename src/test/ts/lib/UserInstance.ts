console.log();

import Paths from "./Paths.ts";
import * as http from "./FetchMethods.ts";
import * as UUID from "https://deno.land/std@0.93.0/uuid/mod.ts";
import * as Models from "./Models.ts";
class UserInstance{
    public id: string=UUID.NIL_UUID;
    public token:string="";
    public Register(name:string,username:string,password:string,email:string):Promise<void>{
        let user:Models.Student=new Models.Student();
        user.name=name;
        user.username=username;
        user.password=password;
        user.email=email;
        return http.Post(Paths.AuthRegister,user)
        .then(res=>res.json())
        .then(json=>{
            console.log(json);
            this.token=json.token
        });
    }
    public Login(request:Models.AuthRequest):Promise<void>{
        return http.Post(Paths.AuthLogin,request)
        .then(res=>res.json())
        .then(json=>{this.token=json.token});
    }
    public GetUser():Promise<Models.User>{
        console.log("token");
        console.log(this.token);
        
        
        return fetch(
            Paths.User,
            {
                headers:{
                    "Authorization":" Bearer "+this.token
                }
            })
        .then(res=>res.json());
    }
}

export default UserInstance;