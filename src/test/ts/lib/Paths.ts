class Paths{
    static local:string="http://127.0.0.1:8080"
    static version: number=1;
    static base:string=`${this.local}/api/v${this.version}`;
    static User:string=this.base+"/user";
    static UserDeletion:string=this.base+"/userDeletion";
    static CancelUserDeletion:string=this.UserDeletion+"/cancel";
    static Auth:string=this.base+"/auth";
    static AuthLogin:string= this.Auth+"/login";
    static AuthRegister:string= this.Auth+"/register";
    static Request:string=this.base+"/request";
    static Message:string="/message";
    static status:string="/status";
    static Assets:string=this.base+"/assets";
    static AssetsLanguge:string=this.Assets+"/language";
}
export default Paths;