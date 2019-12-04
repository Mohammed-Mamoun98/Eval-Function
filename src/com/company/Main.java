package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static StringBuilder statemant = new StringBuilder("(1+23+15*(30-(16/3)-1))") ;
    static int firstIndex = 0 , secondIndex = 0;
    //just look for braces when you find two pairs slice from left brac to right and swap this slice to the retured value

    public static final String[] priority = {"+","-","*","/"};
    public static final String priorityString = "+-*/";
    static int[] validNums = {0,1,2,3,4,5,6,7,8,9};


    public static void main(String[] args) {
        // write your code here
        //1- find right brac
        //2- start searching reversly to the first left brack
        //3- return the indexes of both of them
        //4- slice it as a separetae statement
        //5- handle this statment by priority
        //6- return a single value at where you find left brac and delete until right brack index until there is no brackets.

        //dont forget to add 1 to the end index
//            a.delete(1,4);
//        System.out.println(Arrays.toString(getSliceOfArray(statemant.toString().toCharArray(),0,statemant.toString().length())));

//        System.out.println(statemant.substring(0,5));




        getRidOfSpaces();
        WholeParse();


//        System.out.println((getNumricParams(statemant.toString())));


//            statmentProcessing(statemant.toString());
//        System.out.println("->"+statmentProcessing(statemant.toString()));
//        findRightBrack();
//        System.out.println("first " + firstIndex + "second " + secondIndex);
//        System.out.println(statemant.substring(firstIndex+1,secondIndex));
//

//        System.out.println(priorityString.indexOf('*'));

//            StringBuilder A = null;
//            A.append('A');
//        System.out.println(A);
    }

    public static void WholeParse(){
        getRidOfSpaces();

        while(statemant.toString().contains("(") || statemant.toString().contains(")")){
            findRightBrack();
            //now you got the first and second index
            //now you should trim by first and second
            //now handle the trimmed statement and return a single token insted
            //now delete from (first + 1) index to the second
            //replce the first index with the single token and so on...
            String trimmedStatement = statemant.substring(firstIndex ,secondIndex+1);
            System.out.println("First index :" + firstIndex + " second index: " + secondIndex);
            System.out.println("this is the Trommed value : " + trimmedStatement);


            //<Fullworking>
            //trimmed -> single value -> insert int the statemant so you need to convert to list
            ArrayList<String> procesedValue = statmentProcessing(trimmedStatement.toString());
            String returnValue = statmentProcessing(trimmedStatement.toString()).get(0).toString();
            System.out.println("returned Value :" + returnValue);
            ArrayList<String> statementList = StringToArrayList(statemant.toString());
            System.out.println("first :" + firstIndex  + " second : " + secondIndex);
            System.out.println("Statement List :" + statementList);
            statementList =  handleModify(statementList,procesedValue.get(0), firstIndex , secondIndex);
            statemant = ArrayListToStringBuilder(statementList);

            //</FullWorking>


            //<Test>
//            statemant = statemant.replace(firstIndex,firstIndex , returnValue);
//
//            statemant = statemant.delete(firstIndex+1 , secondIndex);
//            System.out.println();
            System.out.println("Final Statement :" + statemant);





            //</Test>

            /*
            * now you got the returnde value as string now you should replace the first index with the return value and delete from (first index  + 1)to the secondIndex
            *
            *
            *
            *
            *
            * */

        }
    }

    public static ArrayList<String> StringToArrayList(String string){
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < string.length(); i++) {
            list.add(string.charAt(i) + "");
        }
        return list;
    }

    public static StringBuilder ArrayListToStringBuilder(ArrayList<String> string){
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < string.size(); i++) {
            list.append(string.get(i));
        }
        return list;
    }

    public static String selectedStatment(){
       String handledStatement =  statemant.substring(firstIndex,secondIndex);

        return handledStatement;

    }

    public static ArrayList<String> statmentProcessing(String handledStatment){

        //you got a statmant has a tokens some of theme more than one digit some of them are math ops
//        ArrayList<String> list = getNumricParams(statemant.toString());


          ArrayList<String> list = getNumricParams(handledStatment);

        System.out.println(list);
        int i = 0;



//        int highestOpsIndex = findHighstPriority(list);
//        System.out.println(doMath(list.get(highestOpsIndex),Integer.parseInt(list.get(highestOpsIndex - 1)) ,Integer.parseInt(list.get(highestOpsIndex + 1))));
//
//        String elementToReplce = doMath(list.get(highestOpsIndex),Integer.parseInt(list.get(highestOpsIndex - 1)) ,Integer.parseInt(list.get(highestOpsIndex + 1))) + "";
//
//       list =  handleModify(list,elementToReplce , highestOpsIndex -1);

        //now you got the index of highest ops so you will handle the next and prev delete
       while(list.size() > 1){

           int highestOpsIndex = findHighstPriority(list);

           String elementToReplce = doMath(list.get(highestOpsIndex),Integer.parseInt(list.get(highestOpsIndex - 1)) ,Integer.parseInt(list.get(highestOpsIndex + 1))) + "";

           list =  handleModify(list,elementToReplce , highestOpsIndex -1);

           //invoke delete

           //handle this ops

       }
       //list is returnd as a single list element
        return list;
    }

    public static int findHighstPriority(ArrayList<String> list){

        int highest = 0;

        for (int i = 0; i < list.size(); i++) {
            if(priorityString.indexOf(list.get(i)) > highest){
                highest = priorityString.indexOf(list.get(i));
            }
        }

        return list.indexOf(priority[highest]);
    }

    public static int findIndex(ArrayList<String> arr, String t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.size();
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr.get(i).equals(t)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }




    public static int findIndexForPriority(String[] arr, String t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        for (i = 0; i < len ; i++) {
            if(arr[i].equals(t)){
                return i;
            }
        }
        return -1;
    }



    public static int findIndexForPriorityForString(String arr, String t)
    {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length();
        int i = 0;

        // traverse in the array
        for (i = 0; i < len ; i++) {

            if(arr.charAt(i) == (t.charAt(0))){
                return i;
            }
        }
        return -1;
    }


public static boolean isMinus(char Char){
        return Char == '-';
}


    public static ArrayList<String> getNumricParams(String statment){


        ArrayList<String> newParams = new ArrayList<String>();



        int j = 0;
        for (int i = 1; i < statment.length()-1; i++) {
            boolean specialCase = (i!= 1 && !isNumber(statment.charAt(i-1)) && isMinus(statment.charAt(i)) && isNumber(statment.charAt(i+1))    );
           if (isNumber(statment.charAt(i)) && isNumber(statment.charAt(i+1))){
               newParams.add(statment.charAt(i) + "" + statment.charAt(i+1) );
//               System.out.println("theres is a more than digit :" + newParams);
               i++;
           }
           else{
               newParams.add(statment.charAt(i) + "");

           }
//            System.out.println(newParams);
        }

        return newParams;

    }

    public static boolean isNumber(char ParesdNumber){

        for (int i = 0; i < validNums.length; i++) {
            if(Character.getNumericValue(ParesdNumber) == validNums[i]){
                return true;
            }
        }
        return false;

    }


    public static boolean isNumber(String ParesdNumber){

        for (int i = 0; i < validNums.length; i++) {
            if(Integer.parseInt(ParesdNumber) == validNums[i]){
                return true;
            }
        }
        return false;

    }

    public static ArrayList<String> handleModify(ArrayList<String> list , String elementToReplace , int initIndex){

        list.set(initIndex,elementToReplace);
        list.remove(initIndex + 1);
        list.remove(initIndex + 1);
        return list;
    }


    public static ArrayList<String> handleModify(ArrayList<String> list , String elementToReplace , int initIndex , int  finalIndex){

        System.out.println( "removing from : "+list.get(initIndex) + " in index  "  + initIndex + "to index  " + finalIndex+ ""+ list.get(finalIndex));
        list.set(initIndex,elementToReplace);
        for (int i = 0; i < finalIndex - initIndex ; i++) {
            list.remove(initIndex + 1);
            System.out.println("Current List :" + list);

        }
        return list;
    }



    public static int ConvertToInt(char Char){
        return Integer.parseInt(String.valueOf(Char));
    }

    public static void findRightBrack(){
        for (int i = 0; i < statemant.toString().length(); i++) {
            if(statemant.toString().charAt(i) == ')'){
                secondIndex = i;
                findKLeftBrack();
                break;
            }
        }
    }



    public static void findKLeftBrack(){
        for (int i = secondIndex; i >= 0; i--) {
            if(statemant.toString().charAt(i) == '('){
                firstIndex = i;
                break;
            }
        }
    }


    public void findPairs(String statment){

    }

    public static void StatementEnterd(){

    }

    public  static int fact(int n)
    {
        int result;
        if(n==0 || n==1)
            return 1;

        result = fact(n-1) * n;
        return result;
    }


    public static void Solver(Exp exp) {

        getRidOfSpaces(exp);
        System.out.println(exp.express);

    }

    public static void getRidOfSpaces(Exp exp) {

        exp.express = exp.express.replaceAll("\\s", "");

    }

    public static void getRidOfSpaces() {
        String TrimmedString = statemant.toString().replaceAll("\\s", "");
        statemant = new StringBuilder(TrimmedString);
    }

    public static int doMath(String op , int first , int second ){
        switch (op){
            case "+" :
                return first + second;
            case "-":
                return first -second;
            case "/":
                return first / second;
            case "*" :
                return first * second;
            default:
                return 0;

        }
    }

    public static char[] getSliceOfArray(char[] arr,
                                        int start, int end)
    {

        // Get the slice of the Array
        char[] slice = new char[end - start];

        // Copy elements of arr to slice
        for (int i = 0; i < slice.length; i++) {
            slice[i] = arr[start + i];
        }

        // return the slice
        return slice;
    }

}
