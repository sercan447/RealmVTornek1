package sercandevops.com.realmgrafikornek;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class Tansiyon extends RealmObject {


    private String buyuktansiyon;
    private String kucuktansiyon;

    public String getBuyuktansiyon() {
        return buyuktansiyon;
    }

    public void setBuyuktansiyon(String buyuktansiyon) {
        this.buyuktansiyon = buyuktansiyon;
    }

    public String getKucuktansiyon() {
        return kucuktansiyon;
    }

    public void setKucuktansiyon(String kucuktansiyon) {
        this.kucuktansiyon = kucuktansiyon;
    }


    @Override
    public String toString() {
        return "Tansiyon{" +
                "buyuktansiyon='" + buyuktansiyon + '\'' +
                ", kucuktansiyon='" + kucuktansiyon + '\'' +
                '}';
    }
}
