package com.code.imgpicker.ui.Model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;

public class AddPhotosModel {
    int image;
    String title;
    Bitmap bitmap;
    String str_base64;
    Uri uri;
    String encode_string;
    ArrayList<String> Arraylist_image_encode ;
    String image_url;
    String dummy1;
    String dummy2;
    String dummy3;

    public AddPhotosModel(String image_url, String dummy1, String dummy2, String dummy3) {
        this.image_url = image_url;
        this.dummy1 = dummy1;
        this.dummy2 = dummy2;
        this.dummy3 = dummy3;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDummy1() {
        return dummy1;
    }

    public void setDummy1(String dummy1) {
        this.dummy1 = dummy1;
    }

    public String getDummy2() {
        return dummy2;
    }

    public void setDummy2(String dummy2) {
        this.dummy2 = dummy2;
    }

    public String getDummy3() {
        return dummy3;
    }

    public AddPhotosModel(String encode_string, String dummy1, String dummy2) {
        this.encode_string = encode_string;
        this.dummy1 = dummy1;
        this.dummy2 = dummy2;
    }

    public void setDummy3(String dummy3) {
        this.dummy3 = dummy3;
    }

    public AddPhotosModel(ArrayList<String> arraylist_image_encode) {
        Arraylist_image_encode = arraylist_image_encode;
    }

    public ArrayList<String> getArraylist_image_encode() {
        return Arraylist_image_encode;
    }

    public void setArraylist_image_encode(ArrayList<String> arraylist_image_encode) {
        Arraylist_image_encode = arraylist_image_encode;
    }

    public String getEncode_string() {
        return encode_string;
    }

    public void setEncode_string(String encode_string) {
        this.encode_string = encode_string;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public AddPhotosModel(Uri uri) {

        this.uri = uri;
    }

    public AddPhotosModel(Uri uri, String encode_string) {
        this.uri = uri;
        this.encode_string = encode_string;
    }


    public String getStr_base64() {
        return str_base64;
    }

    public void setStr_base64(String str_base64) {
        this.str_base64 = str_base64;
    }

    public AddPhotosModel() {
    }


    public AddPhotosModel(Bitmap bitmap , String encode_string) {
        this.bitmap = bitmap;
        this.encode_string = encode_string;
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public AddPhotosModel(String title, String encode_string) {
        this.title = title;
        this.encode_string = encode_string;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddPhotosModel(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public AddPhotosModel(int image) {
        this.image = image;
    }

}
