package com.spring.springBootKafkaMockitoProject.InterviewQuestion.coding.ArrayCodingQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SecondLargestElementInArray {
    public static void main(String[] args) {
        int [] arr = {12,21,98,13,73,892,23,222};
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.stream(arr).boxed().toList());
        int secondHighest=  arrayList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        int fifthHighest =arrayList.stream().sorted(Comparator.reverseOrder()).skip(4).findFirst().get();
        System.out.println("secondHighest->"+secondHighest);
        System.out.println("fifthHighest->"+fifthHighest);

        int [] arr1 ={1,23,3,89,892,233,9092,2902,23};
        int largest = Integer.MIN_VALUE;
        int secondLargest =Integer.MIN_VALUE;

        for(int num : arr1){
            if(num>largest){
                secondLargest=largest;
                largest=num;
            } else if(num > secondLargest && num < largest){
                secondLargest=num;
            }
        }
        System.out.println("largest Number "+largest);
        System.out.println("SecondLargest Number "+secondLargest);


        //nth largest
        int [] arr2 ={1,23,3,89,892,233,9092,2902,23};
        int n = 5; // 5th largest
        int nthLargest=Integer.MIN_VALUE;


        //sorting in desc
        for(int i=0; i<arr2.length-1;i++){
            for(int j=i+1;j<arr2.length;j++){
                if(arr2[i]<arr2[j]){
                    int temp=arr2[i];
                    arr2[i]=arr2[j];
                    arr2[j]=temp;

                }            }
        }

        System.out.println("sorted array"+ Arrays.toString(arr2));
        int count =1;
        for(int i=1;i<arr2.length;i++){
            if(arr2[i] !=arr2[i-1]){
               count++;
            }
            if(count==n){
                nthLargest=arr2[i];
            }
        }
        System.out.println(nthLargest);
    }
}
