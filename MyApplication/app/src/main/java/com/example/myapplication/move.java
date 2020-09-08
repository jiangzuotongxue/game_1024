package com.example.myapplication;

import java.util.ArrayList;
import java.util.Random;

public class move {

    private static int two=2;
    private static int four=4;

    public  ArrayList<Integer> returnRandomSite(int [][]arr){
        int row,col,site;
        Random random=new Random();
        do {
            site=random.nextInt(16);
            row=site/4;
            col=site%4;
        }while(arr[row][col]!=0);
        ArrayList<Integer> infor=new ArrayList<Integer>();
        infor.add(row);infor.add(col);
        return infor;
    }
    public  void moveToRight(int [][]arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >=0; j--) {
                if(arr[i][j]==0) continue;
                if(arr[i][j]==arr[i][j+1]) {//和右边相邻的相等
                    arr[i][j+1]*=2;
                    arr[i][j]=0;
                }
                else {//和右边相邻的不相等
                    //1.右边畅通，直接到边界位置,或者某个不相同数字的位置,或者相同数字的位置
                    int temp=j+1;
                    if(arr[i][temp]!=0&&arr[i][j]!=arr[i][temp]) continue;
                    while(true) {
                        while(arr[i][temp]==0) {
                            if(temp<3) temp++;
                            else break;
                        }
                        if(arr[i][temp]==arr[i][j]) {
                            arr[i][temp]*=2;
                        }else {
                            if(arr[i][temp]!=0) temp--;
                            arr[i][temp]=arr[i][j];
                        }
                        arr[i][j]=0;break;
                    }
                    //2.右边不通畅，直接不处理
                }
            }
        }
    }
    public  void moveToLeft(int [][]arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <=3; j++) {
                if(arr[i][j]==0) continue;
                if(arr[i][j]==arr[i][j-1]) {//和左边相邻的相等
                    arr[i][j-1]*=2;
                    arr[i][j]=0;
                }
                else {//和左边相邻的不相等
                    //1.zuo边畅通，直接到边界位置,或者某个不相同数字的前一个位置,huohze
                    int temp=j-1;
                    if(arr[i][temp]!=0&&arr[i][j]!=arr[i][temp]) continue;//2.zuo边不通畅，直接不处理
                    while(true) {
                        while(arr[i][temp]==0) {
                            if(temp>0) temp--;
                            else break;
                        }
                        if(arr[i][temp]==arr[i][j]) {
                            arr[i][temp]*=2;
                        }else {
                            if(arr[i][temp]!=0) temp++;
                            arr[i][temp]=arr[i][j];
                        }
                        arr[i][j]=0;break;
                    }

                }
            }
        }
    }
    public  void moveToUp(int [][]arr) {
        for(int j=0;j<4;j++) {
            for (int i = 1; i <=3; i++) {
                if(arr[i][j]==0) continue;
                if(arr[i][j]==arr[i-1][j]) {
                    arr[i-1][j]*=2;
                    arr[i][j]=0;
                }else {
                    int temp=i-1;
                    if(arr[temp][j]!=0&&arr[i][j]!=arr[temp][j]) continue;
                    while(true) {
                        while(arr[temp][j]==0) {
                            if(temp>0) temp--;
                            else break;
                        }
                        if(arr[temp][j]==arr[i][j])
                            arr[temp][j]*=2;
                        else {
                            if(arr[temp][j]!=0) temp++;
                            arr[temp][j]=arr[i][j];
                        }
                        arr[i][j]=0;break;
                    }
                }
            }
        }
    }
    public  void moveToDown(int [][] arr) {
        for(int j=0;j<4;j++) {
            for (int i = 2; i >=0; i--) {
                if(arr[i][j]==0) continue;
                if(arr[i][j]==arr[i+1][j]) {
                    arr[i+1][j]*=2;
                    arr[i][j]=0;
                }else {
                    int temp=i+1;
                    if(arr[temp][j]!=0&&arr[i][j]!=arr[temp][j]) continue;
                    while(true) {
                        while(arr[temp][j]==0) {
                            if(temp<3) temp++;
                            else break;
                        }
                        if(arr[temp][j]==arr[i][j])
                            arr[temp][j]*=2;
                        else {
                            if(arr[temp][j]!=0) temp--;
                            arr[temp][j]=arr[i][j];
                        }
                        arr[i][j]=0;break;
                    }
                }
            }
        }

    }
}
