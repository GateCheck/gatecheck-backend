import * as UUID from "https://deno.land/std@0.93.0/uuid/mod.ts";
class User{
    public id:string=UUID.NIL_UUID;
    public name:string="";
    public username:string="";
    public email:string="";
    public password:string="";//not bad security, this page is filled out only in register/login requests
    public profilePath?:string="";
    public language?:string="";
}

class Student extends User{
    public instructors:string[]=[];
    public parents:string[]=[];
    public school:string="";
}
class Instructor extends User{
    public students:string[]=[];
    public school:string[]=[];
}
class Parent extends User{
    public children:string[]=[];
}

class AuthRequest{
    public username:string="";
    public password:string="";
    public stayLoggedIn:boolean=true;
}
export{User,Student,Instructor,Parent,AuthRequest};