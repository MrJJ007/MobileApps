package com.example.myapplicationattempt8

class MyModel {
    var modelName: String? = null
    var modelText: String? = null
    private var modelImage: Int = 0

    fun getNames():String{
        return modelName.toString()
    }
    fun setNames(name: String){
        this.modelName = name
    }
    fun getImages(): Int{
        return modelImage
    }
    fun setImages(image_drawable: Int){
        this.modelImage=image_drawable
    }
    fun getText():String{
        return modelText.toString()
    }
    fun setText(text: String){
        this.modelText = text
    }
}