package com.codeschool.candycoded;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = DetailActivity.this.getIntent();

        if (intent != null && intent.hasExtra("position")) {
            int position = intent.getIntExtra("position", 0);

            CandyDbHelper dbHelper = new CandyDbHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM candy", null);
            cursor.moveToPosition(position);

            String candyName = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyContract.CandyEntry.COLUMN_NAME_NAME));
            String candyPrice = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyContract.CandyEntry.COLUMN_NAME_PRICE));
            String candyImage = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyContract.CandyEntry.COLUMN_NAME_IMAGE));
            String candyDesc = cursor.getString(cursor.getColumnIndexOrThrow(
                    CandyContract.CandyEntry.COLUMN_NAME_DESC));

            TextView textView = (TextView)this.findViewById(R.id.text_view_name);
            TextView textViewPrice = (TextView)this.findViewById(R.id.text_view_price);
            TextView textViewDesc = (TextView)this.findViewById(R.id.text_view_desc);
            ImageView imageView = (ImageView)this.findViewById(R.id.image_view_candy);
            textView.setText(candyName);
            textViewPrice.setText(candyPrice);
            textViewDesc.setText(candyDesc);
            Picasso.with(this).load(candyImage).into(imageView);
        }
    }
}
