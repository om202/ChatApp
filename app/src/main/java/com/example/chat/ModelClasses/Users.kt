package com.example.chat.ModelClasses

class Users {
    private var link1:String = ""
    private var link2:String = ""
    private var name:String = ""
    private var profile:String = ""
    private var search:String = ""
    private var status:String = ""
    private var uid:String = ""

    public constructor()

    public constructor(link1: String, link2: String, name: String, profile: String, search: String, status: String, uid: String) {
        this.link1 = link1
        this.link2 = link2
        this.name = name
        this.profile = profile
        this.search = search
        this.status = status
        this.uid = uid
    }

    fun getUID():String?{
        return uid
    }

    fun setUID(uid:String){
        this.uid = uid
    }

    fun getlink1():String?{
        return link1
    }

    fun setlink1(link1:String){
        this.link1 = link1
    }

    fun getlink2():String?{
        return link2
    }

    fun setlink2(link2:String){
        this.link2 = link2
    }

    fun  getname():String?{
        return name
    }

    fun setname(name:String){
        this.name = name
    }

    fun getprofile():String?{
        return profile
    }

    fun setprofile(profile:String){
        this.profile = profile
    }

    fun getsearch():String?{
        return search
    }

    fun setsearch(search:String){
        this.search = search
    }

    fun getstatus():String?{
        return status
    }

    fun setstatus(status:String){
        this.status = status
    }

}