package tuandxph21037.nhom3.gara.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tuandxph21037.nhom3.gara.DATABASE.XeHelper;
import tuandxph21037.nhom3.gara.Model.HoaDon;

public class HoaDonDAO {

    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonDAO(Context context){
        this.context = context;
        XeHelper dbHelper = new XeHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(HoaDon obj){
        ContentValues values = new ContentValues();
//        values.put("maNv",obj.maNv);
        values.put("maNv",obj.maNv);
        values.put("maKhachHang",obj.maKhachHang);
        values.put("maXe",obj.maXe);
        values.put("ngay",sdf.format(obj.ngay));
        values.put("giaTien",obj.giaTien);
        return db.insert("HoaDon",null, values);

    }
    public int update(HoaDon obj){
        ContentValues values = new ContentValues();
//        values.put("maHoaDon",obj.maHoaDon);
        values.put("maNv",obj.maNv);
        values.put("maKhachHang",obj.maKhachHang);
        values.put("maXe",obj.maXe);
        values.put("ngay",sdf.format(obj.ngay));
        values.put("giaTien",obj.giaTien);
        return db.update("HoaDon",values,"maHoaDon=?",new String[]{String.valueOf(obj.maHoaDon)});
    }
    public int delete(String id){
        return db.delete("HoaDon","maHoaDon=?",new String[]{id});
    }
    //get tat ca data
    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }
    //get data theo id
    public HoaDon getID(String id){
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String...selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            HoaDon obj = new HoaDon();
            obj.maHoaDon = Integer.parseInt(c.getString(c.getColumnIndex("maHoaDon")));
            obj.maNv = c.getString(c.getColumnIndex("maNv"));
            obj.maKhachHang = Integer.parseInt(c.getString(c.getColumnIndex("maKhachHang")));
            obj.maXe = Integer.parseInt(c.getString(c.getColumnIndex("maXe")));
            obj.giaTien = Integer.parseInt(c.getString(c.getColumnIndex("giaTien")));
            try {
                obj.ngay = sdf.parse(c.getString(c.getColumnIndex("ngay")));
            }catch (ParseException e){
                e.printStackTrace();
            }
            list.add(obj);
        }
        return list;
    }
}
