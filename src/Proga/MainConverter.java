package Proga;

import java.util.Scanner;

public class MainConverter {
    static Scanner scanner = new Scanner(System.in);
    static boolean IsNegative;
    static boolean IsInt;

    public static void main(String[] args) {

        String input_number;
        System.out.println("I'm waiting for your number:");
        input_number = scanner.nextLine();

        if(input_number.contains("-")) IsNegative = true;
        else IsNegative = false;

        if(input_number.contains(".") || input_number.contains(",")) IsInt = false;
        else IsInt = true;

        konverter(input_number.replace(",","."));
    }


    public static void konverter(String number) {
        int decimalplaces=0;

        long LongIntPart=0;
        double LongDouble=0d;
        double Tail=0d;
        String AFOaZ_IntPart = ""; //Array for Ones and Zeroes
        String ResultOfBinary = "";



        if (!IsInt) {
            LongDouble=Double.parseDouble(number);
            System.out.println("How much decimal places(знаков после запятой) do you need?");
            decimalplaces = scanner.nextInt();
            if (IsNegative) {
                LongIntPart=(long)Math.ceil(LongDouble);
                LongIntPart = LongIntPart * (-1) - 1;
                Tail=(LongDouble+LongIntPart+1)*(-1);
            }else{
                LongIntPart=(long)Math.floor(LongDouble);
                Tail=LongDouble-LongIntPart;
            }


        } else {
            LongIntPart=Long.parseLong(number);
            if (IsNegative) {
                LongIntPart = LongIntPart * (-1) - 1;
            }
        }

//=========================/ part for integer part of number \===============================
        while(LongIntPart!=0) {
            if(LongIntPart % 2 == 1) {
                if(!IsNegative) AFOaZ_IntPart += 1;
                else AFOaZ_IntPart += 0;
            }
            else {
               if(!IsNegative) AFOaZ_IntPart += 0;
               else AFOaZ_IntPart += 1;
            }
            LongIntPart /= 2;
        }

        System.out.println("\n Your number type can be: ");


        if(AFOaZ_IntPart.length()<=64){
            if(IsNegative) System.out.print(" (negative) ");
            if(IsInt)System.out.print("Long"); else System.out.println("Double");
            if(AFOaZ_IntPart.length()<=32){
                if(IsInt)System.out.print(", Int");
                if(AFOaZ_IntPart.length()<=16){
                    if(IsInt)System.out.print(", Short");
                    if(AFOaZ_IntPart.length()<=8) {
                        if(IsInt)System.out.print(", Byte");
                        if(IsNegative) {
                            int a=8-AFOaZ_IntPart.length();
                            for(int I = 0; I< a;I++) ResultOfBinary+=1;
                        }
                    }else{ if(IsNegative){
                        int a=16-AFOaZ_IntPart.length();
                        for(int I = 0; I< a;I++) ResultOfBinary+=1;
                    }}
                }else{ if(IsNegative) {
                    int a=32-AFOaZ_IntPart.length();
                    for(int I = 0; I< a;I++) ResultOfBinary+=1;
                }}
            }else {if(IsNegative) {
                int a=64-AFOaZ_IntPart.length();
                for(int I = 0; I< a;I++) ResultOfBinary+=1;
            }}
        }
        StringBuffer BackWord= new StringBuffer(AFOaZ_IntPart);
        BackWord.reverse();
        ResultOfBinary += BackWord.toString();

//=============================/ part for tail \===================================

        String BinaryTail="";
        if(!IsInt) {
            BinaryTail = ".";
            for (int g = 0; g < decimalplaces; g++) {
                Tail *= 2;
                if (Tail >= 1) {
                    if (!IsNegative) BinaryTail += 1;
                    else BinaryTail += 0;
                    Tail -= 1;
                } else {
                    if (!IsNegative) BinaryTail += 0;
                    else BinaryTail += 1;
                }
                if (Tail == 0d) {
                    System.out.println("it's not neccesery to write all " + decimalplaces + " decimal places");
                    break;
                }
            }
        }
//=============================/ part for result \===================================

        System.out.println("\n\n Binary type of " + number+ " is : \n" + ResultOfBinary + BinaryTail );

    }

}
