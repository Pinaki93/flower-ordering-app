package com.pinaki.example.rxjavatest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Flower implements Parcelable{
    private String category;
    private double price;
    private String instructions;
    private String photo;
    private String name;
    private int productId;
    private String imgUrl;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Flower(String category, double price, String instructions, String photo, String name, int productId, String imgUrl) {
        this.category = category;
        this.price = price;
        this.instructions = instructions;
        this.photo = photo;
        this.name = name;
        this.productId = productId;
        this.imgUrl = imgUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category);
        dest.writeDouble(this.price);
        dest.writeString(this.instructions);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeInt(this.productId);
        dest.writeString(this.imgUrl);
    }

    protected Flower(Parcel in) {
        this.category = in.readString();
        this.price = in.readDouble();
        this.instructions = in.readString();
        this.photo = in.readString();
        this.name = in.readString();
        this.productId = in.readInt();
        this.imgUrl = in.readString();
    }

    public static final Creator<Flower> CREATOR = new Creator<Flower>() {
        @Override
        public Flower createFromParcel(Parcel source) {
            return new Flower(source);
        }

        @Override
        public Flower[] newArray(int size) {
            return new Flower[size];
        }
    };
}
