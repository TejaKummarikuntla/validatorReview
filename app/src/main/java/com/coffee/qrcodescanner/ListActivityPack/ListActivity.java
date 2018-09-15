package com.coffee.qrcodescanner.ListActivityPack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.coffee.qrcodescanner.MainActivity;
import com.coffee.qrcodescanner.QRScanActivity;
import com.coffee.qrcodescanner.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";

    public static String[] mNames = new String[] {"Teja Kummarikuntla","Manisai Prasad","Rafi Naqvi","someoneElse","Hello", "Dell","Hp","Divyanshu"," Charlotte Doyle "," Stephen Sherman "," Eugene Craig "," Nina Hicks "," Christie Romero "};
    public String[] mScanned = new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","0"};
    public int p = 0;



    RecyclerView rc;
    ListAdapter listAdapter;

    List<person> personList;
    final int CAMEAR_REQ_CODE = 5;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public ImageView mImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

      /*  RecyclerView rv= (RecyclerView)findViewById(R.id.mRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));*/

        personList = new ArrayList<>();

        rc = findViewById(R.id.mRecyclerView);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));




        for(int i = 0;i<ListActivity.mNames.length;i++) {
            personList.add(new person(ListActivity.mNames[i]));
        }


        listAdapter = new ListAdapter(this, personList);
        rc.setAdapter(listAdapter);

        Button camBtn = findViewById(R.id.openCamBtn);
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, QRScanActivity.class);
                startActivityForResult(intent, 1);
            }
        });



        // galleryAddPic();





    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int flag = 5;
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String qrCodeValue = data.getStringExtra("QR_CODE_VALUE");
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                for(int x =0;x< mNames.length;x++){
                    if (qrCodeValue.equals(mNames[x]) && mScanned[x].equals("0")) {
                        flag = 0;
                        mScanned[x]="1";
                        //p = x;
                        break;
                    } else if (qrCodeValue.equals(mNames[x]) && mScanned[x].equals("1")) {
                        flag =2;
                        break;
                    } else {
                        if (!qrCodeValue.equals(mNames[x])) {
                            flag = 1;
                        }
                    }
                }

                if (flag == 0) {
                    builder.setTitle("Access Granted");
                    builder.setMessage("ThankYou").setIcon(R.drawable.access_granted);

                } else if (flag == 1) {
                    builder.setTitle("Access Denied");
                    builder.setMessage("Sorry, You are not in the List").setIcon(R.drawable.denied);

                } else if(flag ==2  ){
                    builder.setTitle("Already Exists");
                    builder.setMessage("Let's Have some Fun!!").setIcon(R.drawable.alert);

                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
