package com.example.floodfill;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TableLayout table;
    private int columns = 6;
    /**
     * Number of rows on the canvas, i.e. the 'y' coordinate
     * Note: Can be changed at compile time
     */
    private int rows = 8;

    private int colors[] = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    private int[][] canvas = new int[columns][rows];
    Button button;

    EditText x_coordinate,y_coordinate;
    private RadioGroup radioGroup;

    public void floodFillUtil(int x, int y, int prevC,int newC)
    {
        if (x < 0 || x >= columns || y < 0 || y >= rows)
            return;

        if(canvas[x][y] == newC){
            return ;
        }

        if (canvas[x][y] == prevC)
        {
            canvas[x][y] = newC;

            floodFillUtil(x+1, y, prevC,newC);
            floodFillUtil(x-1,y-1,prevC,newC);
            floodFillUtil(x-1, y, prevC,newC);
            floodFillUtil(x-1,y+1,prevC,newC);
            floodFillUtil( x, y+1, prevC,newC);
            floodFillUtil(x+1,y+1,prevC,newC);
            floodFillUtil( x, y-1, prevC,newC);
            floodFillUtil(x+1,y-1,prevC,newC);
        }else {
            return;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table = findViewById(R.id.mytable);
        initializeCanvas();
        displayCanvas();
        button = findViewById(R.id.flood_fill);

        radioGroup = findViewById(R.id.color_picker);
        radioGroup.clearCheck();

        x_coordinate = findViewById(R.id.x_coordinate);
        y_coordinate = findViewById(R.id.y_coordinate);
    }

    private void initializeCanvas() {
        // Create the random object
        Random random = new Random();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                // Choose a random number from the list of colors
                canvas[i][j] = colors[random.nextInt(colors.length)];
            }
        }
    }

    public void displayCanvas() {

        table.removeAllViews();

        for (int i = 0; i < columns; i++) {

            TableRow tr = new TableRow(this);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            for (int j = 0; j < rows; j++) {
                View txtGeneric = new TextView(this);
                txtGeneric.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                txtGeneric.setBackgroundColor(canvas[i][j]);
                tr.addView(txtGeneric);
            }
            table.addView(tr);
        }
    }

    private void floodFill(int x, int y, int color) {
        int oldColor = canvas[x][y];
        floodFillUtil(x,y,oldColor,color);
    }

    private int getColorCode(String colorName) {
        switch (colorName) {
            case "Red":
                return Color.RED;
            case "Green":
                return Color.GREEN;
            case "Blue":
                return Color.BLUE;
            case "Yellow":
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }

    public void floodOnClick(View view) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(MainActivity.this,
                    "No answer has been selected",
                    Toast.LENGTH_SHORT)
                    .show();
        } else {
            RadioButton radioButton = radioGroup.findViewById(selectedId);
            final String colorCode = radioButton.getText().toString();
            if (UtilClass.nullCheckEditTexr(x_coordinate) && UtilClass.nullCheckEditTexr(y_coordinate)) {

                Bundle bundle = new Bundle();
                bundle.putString(Constant.Y_COORDINALTE, x_coordinate.getText().toString());
                bundle.putString(Constant.Y_COORDINALTE, y_coordinate.getText().toString());
                bundle.putString(Constant.COLOR_CODE,colorCode);

                new FloodFillAcyncTask().execute(bundle);
            }
        }
    }

    public class FloodFillAcyncTask extends AsyncTask<Bundle,Void,Void>{

        @Override
        protected Void doInBackground(Bundle... bundles) {

            floodFill(
                    Integer.parseInt(bundles[0].getString(Constant.x_COORDINALTE)),
                    Integer.parseInt(bundles[0].getString(Constant.Y_COORDINALTE)),
                    getColorCode(bundles[0].getString(Constant.COLOR_CODE)));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            displayCanvas();
        }
    }
}
