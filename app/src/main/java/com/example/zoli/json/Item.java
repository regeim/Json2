package com.example.zoli.json;

/**
 * Created by Zoli on 2015.05.10..
 */
public class Item {
    private String id;
    private String owner;
    private String auction_number;
    private Boolean isSelected=true;
   /* private String buyout;
    private String time_left;*/

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAuction_number() {
        return auction_number;
    }

    public void setAuction_number(String auction_number) {
        this.auction_number = auction_number;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    /*    public String getBuyout() {
        return buyout;
    }

    public void setBuyout(String buyout) {
        this.buyout = buyout;
    }

    public String getTime_left() {
        return time_left;
    }

    public void setTime_left(String time_left) {
        this.time_left = time_left;
    }*/

    /*public Item(String owner, Integer auction_number, Long buyout, String time_left){
        this.owner=owner;
        this.auction_number=auction_number;
        this.buyout=buyout;
        this.time_left=time_left;
    }*/

    @Override
    public String toString() {
        return this.auction_number + "-" + this.owner;
    }


}
