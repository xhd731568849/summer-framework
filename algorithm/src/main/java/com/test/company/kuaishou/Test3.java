package com.test.company.kuaishou;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

    public static void main(String[] args) {
        System.out.println(getRes(1,3));
    }

    public static String getRes(int a , int b) {
        if(a%b == 0) {
            return String.valueOf(a/b);
        }
        String decimal = "";
        a = a%b;
        List<Integer> tmp = new ArrayList<>();
        loop :while(a!=0){
            if(!tmp.contains(a)) {
                tmp.add(a);
            }else {
                for(int i = 0 ; i < tmp.size() ; i ++ ) {
                    if(tmp.get(i) == a) {
                        decimal = decimal.substring(0,i)+"(" + decimal.substring(i,decimal.length()) +")";
                        break loop;
                    }
                }
            }
            decimal += a*10/b;
            a=a*10%b;
        }
        return String.valueOf(a/b) + "." + decimal;

    }

}
