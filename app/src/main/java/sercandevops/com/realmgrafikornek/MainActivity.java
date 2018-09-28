package sercandevops.com.realmgrafikornek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    Realm realm;
    EditText buyuktansiyon,kucuktansiyon;
    Button btnekle;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        tanimla();
        Listele();
        GrafikGoster();

        btnekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Tansiyon tns = realm.createObject(Tansiyon.class);
                        tns.setKucuktansiyon(kucuktansiyon.getText().toString());
                        tns.setBuyuktansiyon(buyuktansiyon.getText().toString());
                            GrafikGoster();
                    }
                });
            }
        });
    }

    public void tanimla(){

        buyuktansiyon = findViewById(R.id.edtbuyuk);
        kucuktansiyon = findViewById(R.id.edtkucuk);
        btnekle = findViewById(R.id.btnekle);
        barChart = findViewById(R.id.barchart);

    }
    public void Listele(){
        RealmResults<Tansiyon> list = realm.where(Tansiyon.class).findAll();

        for (Tansiyon t:list){
            Log.i("VERI",t.toString());
        }
    }//FNC

    public void GrafikGoster(){

        RealmResults<Tansiyon> kisi = realm.where(Tansiyon.class).findAll();
        Float byktns = 0.f;
        Float kcktns = 0.f;

        for(int i=0;i<kisi.size(); i++) {
            byktns += Float.parseFloat(kisi.get(i).getBuyuktansiyon());
            kcktns += Float.parseFloat(kisi.get(i).getKucuktansiyon());
        }
        ArrayList<BarEntry> arrayList = new ArrayList<>();
        arrayList.add(new BarEntry(byktns,0));
        arrayList.add(new BarEntry(kcktns,1));

        BarDataSet barDataSet = new BarDataSet(arrayList,"Toplam deger");

        ArrayList<String> sutunIsmi = new ArrayList<>();
        sutunIsmi.add("Büyük Tansiyon");
        sutunIsmi.add("Küçük Tansiyon");

        BarData barData = new BarData(sutunIsmi,barDataSet);
        barChart.setData(barData);
        barChart.setDescription("Tansiyon Degerlerini gosteren Graph Interface");

        barChart.invalidate();
    }

}
