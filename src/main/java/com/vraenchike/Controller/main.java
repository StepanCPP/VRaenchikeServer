package com.vraenchike.Controller;

import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            String text =  sc.nextLine();
            int count = 0;
            for(int i=0;i<text.length();i++)
            {

                char dig = text.charAt(i);

                if(dig=='.'){
                    count+=1;
                }else if (dig == ',') {
                    count+=2;
                }else if(dig=='!'){
                    count+=3;
                }else if(dig==' '){
                    count+=1;
                }else {
                    int min = (int) 'a';
                    int num = ((int) (dig - 'a')) % 3;
                    count += num + 1;
                }
            }
            System.out.println(count);
            return;
        }



    }
}
