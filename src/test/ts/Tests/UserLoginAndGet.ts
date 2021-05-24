import UserInstance from "../lib/UserInstance.ts";
import * as Models from "../lib/Models.ts";

export default function UserLoginAndGet(username:string, password:string) {
    let r:Models.AuthRequest=new Models.AuthRequest();
    r.username=username;
    r.password=password;
    let user:UserInstance=new UserInstance();
    user.Login(r)
    .then(()=>user.GetUser())
    .then((val)=>{console.log(val);console.log("yay")})
    .catch((err)=>{console.log(err);console.log("error")});
}