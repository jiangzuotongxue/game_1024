package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView group[][] = new TextView[4][4];

    public static int[][] arr = new int[4][4];
    public static int[][] tempArr = new int[4][4];
    private static int two = 2;
    private static int four = 4;
    private float touchDownX, touchUpX;//控制手指左右移动的两次x坐标
    private float touchDownY, touchUpY;//控制手指左右移动的两次Y坐标
    private move moveAct;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        group[0][0] = findViewById(R.id.n1);
        group[0][1] = findViewById(R.id.n2);
        group[0][2] = findViewById(R.id.n3);
        group[0][3] = findViewById(R.id.n4);
        group[1][0] = findViewById(R.id.n5);
        group[1][1] = findViewById(R.id.n6);
        group[1][2] = findViewById(R.id.n7);
        group[1][3] = findViewById(R.id.n8);
        group[2][0] = findViewById(R.id.n9);
        group[2][1] = findViewById(R.id.n10);
        group[2][2] = findViewById(R.id.n11);
        group[2][3] = findViewById(R.id.n12);
        group[3][0] = findViewById(R.id.n13);
        group[3][1] = findViewById(R.id.n14);
        group[3][2] = findViewById(R.id.n15);
        group[3][3] = findViewById(R.id.n16);
        score = findViewById(R.id.score);
        moveAct = new move();
        ArrayList<Integer> Infor = moveAct.returnRandomSite(arr);
        int row = Infor.get(0);
        int col = Infor.get(1);
        Random random = new Random();
        double randomNum = random.nextDouble();
        arr[row][col] = ((randomNum < 0.9) ? two : four);
        // arr[0][0]=512;arr[0][1]=512;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                tempArr[i][j] = arr[i][j];
        }
        String text = "" + arr[row][col];
        group[row][col].setText(text);
        group[row][col].setBackgroundColor(getResources().getColor(R.color.nuL));
        TableLayout tableLayout = findViewById(R.id.table);
        tableLayout.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {//监听到手指放下来
                    touchDownX = event.getX();
                    touchDownY = event.getY();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    int counts = 0;
                    boolean isSameToLast = true;
                    touchUpX = event.getX();
                    touchUpY = event.getY();
                    float Xdistance = touchUpX - touchDownX;
                    float Ydistance = touchUpY - touchDownY;

                    boolean ishorizontal = false;
                    if (Math.abs(Xdistance) > Math.abs(Ydistance)) {
                        ishorizontal = true;
                    }
                    if (Xdistance > 100 && ishorizontal) {//从左往右滑动)
                        moveAct.moveToRight(arr);
                    } else if (Xdistance < -100 && ishorizontal) {//右->左
                        moveAct.moveToLeft(arr);
                    } else if (Ydistance > 100 && !ishorizontal) {//↓
                        moveAct.moveToDown(arr);
                    } else if (Ydistance < -100 && !ishorizontal) {//↑
                        moveAct.moveToUp(arr);
                    }
                    int max = returnMax();
                    score.setText("SCORE\n" + max);
                    if (!isSameArray(arr, tempArr)) {
                        isSameToLast = false;
                        System.out.println("一样");
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (arr[i][j] == 0) {
                                group[i][j].setText("");
                                group[i][j].setBackgroundColor(getResources().getColor(R.color.nuL));
                                continue;
                            } else {
                                counts++;
                            }
                            group[i][j].setText("" + arr[i][j]);
                            group[i][j].setTextColor(getResources().getColor(R.color.blackFront));
                            setCol(i, j);
                            if (arr[i][j] == 1024) {
                                Intent intent = new Intent(GameActivity.this, EndActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putCharSequence("result", "win");
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    }

                    if (!isSameToLast) {
                        ArrayList<Integer> Infor2 = moveAct.returnRandomSite(arr);
                        int row = Infor2.get(0);
                        int col = Infor2.get(1);
                        Random random = new Random();
                        double randomNum = random.nextDouble();
                        arr[row][col] = ((randomNum < 0.9) ? two : four);
                        String text = "" + arr[row][col];
                        group[row][col].setText(text);
                        group[row][col].setTextColor(getResources().getColor(R.color.whiteFront));
                        group[row][col].setBackgroundColor(getResources().getColor(R.color.nuL));
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++)
                            tempArr[i][j] = arr[i][j];
                    }
                    if (isDead(arr)) {
                        Intent intent = new Intent(GameActivity.this, EndActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("result", "lose");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    public void setCol(int i, int j) {
        if (arr[i][j] == 2) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c2));
        } else if (arr[i][j] == 4) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c4));
        } else if (arr[i][j] == 8) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c8));
        } else if (arr[i][j] == 16) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c16));
        } else if (arr[i][j] == 32) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c32));
        } else if (arr[i][j] == 64) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c64));
        } else if (arr[i][j] == 128) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c128));
        } else if (arr[i][j] == 256) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c256));
        } else if (arr[i][j] == 512) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c512));
        } else if (arr[i][j] == 1024) {
            group[i][j].setBackgroundColor(getResources().getColor(R.color.c1024));
        }
    }

    public boolean isSameArray(int[][] a, int[][] b) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //System.out.println(arr[i][j]+"一样?"+tempArr[i][j]);
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDead(int[][] arr) {
        int[][] temp1 = new int[4][4];
        int[][] temp2 = new int[4][4];
        int[][] temp3 = new int[4][4];
        int[][] temp4 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp1[i][j] = arr[i][j];
                temp2[i][j] = arr[i][j];
                temp3[i][j] = arr[i][j];
                temp4[i][j] = arr[i][j];
            }
        }
        moveAct.moveToDown(temp1);
        moveAct.moveToUp(temp2);
        moveAct.moveToLeft(temp3);
        moveAct.moveToRight(temp4);
        if (isSameArray(arr, temp1) && isSameArray(arr, temp2) && isSameArray(arr, temp3) && isSameArray(arr, temp4)) {
            return true;
        }
        return false;
    }

    public int returnMax() {
        int max = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
        }
        return max;
    }
}
