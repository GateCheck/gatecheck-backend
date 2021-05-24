import UserInstance from '../lib/UserInstance.ts'; 

export default function UserRegisterAndGet(name:string,userName:string,pass:string,email:string){
    let user:UserInstance= new UserInstance();
    user.Register(name,userName,pass,email)
    .then(()=>user.GetUser())
    .then((val)=>{console.log(val);console.log("yay")})
    .catch((err)=>{console.log(err);console.log("error")});
}

