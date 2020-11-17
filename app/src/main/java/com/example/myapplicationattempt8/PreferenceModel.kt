package com.example.myapplicationattempt8

class PreferenceModel(val id: Int,var modelName:String){


    private var modelImage: Int = 0

    fun getPreference():String{
        return modelName.toString()
    }
    fun setPreference(name: String){
        this.modelName = name
    }


}