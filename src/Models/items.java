package Models;

public class items {
    int itemid,itemprice,itemquantity;
    String itemname;

    public items(int itemquantity, int itemid, String itemname,int itemprice) {
        this.itemquantity = itemquantity;
        this.itemid=itemid;
        this.itemname=itemname;
        this.itemprice=itemprice;
    }
    public items(String itemname, int itemprice,int itemquantity) {
        this.itemquantity = itemquantity;
        this.itemname=itemname;
        this.itemprice=itemprice;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }

    public int getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(int itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
