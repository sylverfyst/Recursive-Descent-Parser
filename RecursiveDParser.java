import java.util.*;
import java.io.*;

/*
UCID: bfm9
Name: Bryan Metzger
Date: 3/23/14
Parses a file to see if individual lines are in a language
*/

public class RecursiveDParser {

	public static void main(String[] args) {
		//read all lines into an ArrayList
		ArrayList<String> input = new ArrayList<String>();
		try{
			input = FileR();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		//remove whitespace from all lines
		for (int i = 0; i < input.size(); i++){
			String temp = input.get(i);
			input.set(i, RemoveWhite(temp));
		}
		
		for(int j = 0; j< input.size(); j++){
		char [] Stream = input.get(j).toCharArray();
		LinkedList<Character> Token = new LinkedList<Character>();
		for(int k = 0; k<Stream.length;k++){
			Token.add(Stream[k]);
		}
		Parse(Token);
		}
	}
	//main parse method
	public static void Parse (LinkedList<Character> input) {
		//before calling Aparse, parse up to P 
		String x = ToString(input);
		System.out.println("String read from file: " + x);
		LinkedList<Character> temp = CDparse(input);
		temp = Lparse(temp);
		temp = Iparse(temp);
		boolean flag = Aparse(temp);
		if(flag == true)
			System.out.println("The string " + x + " is in the language");
		else
			System.out.println("The sentence " + x + " is not in the language");		
	}
	//Assignment Parse method
	public static Boolean Aparse (LinkedList<Character> input) {
		boolean TFlag = false;  //truth flag, found = sign
		boolean Eflag = false; //Expression Parse Flag
		boolean pFlag = false; //return flag
		//System.out.println("Starting " + "Aparse:");
		//printList(input);
		//iterate through list, check for =, if found stop and split list call Iparse on "before"
		//and Eparse on "after"
		for(int i = 0; i < input.size(); i++){
			char equalSign = '=';
			if(input.get(i).equals(equalSign)){
				int flag = i;
				TFlag = true;
				//LinkedList<Character> before = SplitBefore(flag, input);
				LinkedList<Character> after = SplitAfter(flag, input);
				//IFlag = Iparse(before);
				Eflag = Eparse(after);
				break;
			}
		}
		//if no equal sign is found, call Eparse on whole input
		if(TFlag == false)
			Eflag = Eparse(input);
		//return statements
		if(Eflag == true)
			pFlag = true;
			
		if (input == null)
			pFlag = false;
		//System.out.print("pFlag: " + pFlag);
		if(pFlag == true)
			return true;
		else
			return false;
	}
	public static Boolean Eparse (LinkedList<Character> input) {
		boolean AFlag = false;
		boolean TFlag = false;
		boolean Eflag = false;
		boolean pFlag = false;
		//System.out.println("Starting " + "Eparse:");
		//printList(input);
		for(int i = 0; i < input.size(); i++){
			char plusSign = '+';
			char minusSign = '-';
			if(input.get(i).equals(plusSign) | input.get(i).equals(minusSign) && input.size()%2 != 0){
				int flag = i;
				AFlag = true;
				LinkedList<Character> before = SplitBefore(flag, input);
				LinkedList<Character> after = SplitAfter(flag, input);
				//System.out.println("before: " + ToString(before));
				//System.out.println("after: " + ToString(after));
				TFlag = Tparse(before);
				Eflag = Eparse(after);
				break;
			}
		}
		if(AFlag == false){
			TFlag = Tparse(input);
			Eflag = true;
		}
		if(TFlag & Eflag == true)
			pFlag = true;
			
		if (input == null)
			pFlag = false;
		//System.out.print("pFlag: " + pFlag);
		if(pFlag == true)
			return true;
		else
			return false;
	}
	public static Boolean Tparse (LinkedList<Character> input) {
		boolean AFlag = false;
		boolean FFlag = false;
		boolean Tflag = false;
		boolean pFlag = false;
		//System.out.println("Starting " + "Tparse:");
		//printList(input);
		for(int i = 0; i < input.size(); i++){
			char timesSign = '*';
			char divideSign = '/';
			if(input.size()%2 != 0){
				if(input.get(i).equals(timesSign) | input.get(i).equals(divideSign)){
					int flag = i;
					AFlag = true;
					LinkedList<Character> before = SplitBefore(flag, input);
					LinkedList<Character> after = SplitAfter(flag, input);
					//System.out.println("before: " + ToString(before));
					//System.out.println("after: " + ToString(after));
					FFlag = Fparse(before);
					Tflag = Tparse(after);
					break;
			}
			else
				AFlag = false;
		}
		}
		if(AFlag == false){
			FFlag = Fparse(input);
			Tflag = true;	
		}
		if(FFlag & Tflag == true)
			pFlag = true;
		if (input == null)
			pFlag = false;
		//System.out.print("pFlag: " + pFlag);
		if(pFlag == true)
			return true;
		else
			return false;
	}
	public static Boolean Fparse (LinkedList<Character> input) {
		boolean AFlag = false;
		boolean PFlag = false;
		boolean Fflag = false;
		boolean XFlag = false;
		//System.out.println("Starting " + "Fparse:");
		//printList(input);
		for(int i = 0; i < input.size(); i++){
			char exponentSign = '^';
			if(input.get(i).equals(exponentSign)){
				int flag = i;
				AFlag = true;
				LinkedList<Character> before = SplitBefore(flag, input);
				LinkedList<Character> after = SplitAfter(flag, input);
				PFlag = Pparse(before);
				Fflag = Fparse(after);
				break;
			}
		}
		//System.out.println("AFlag: " + AFlag);
		if(AFlag == false){
			PFlag = Pparse(input);
			Fflag = true;	
		}
		if(PFlag & Fflag == true)
			XFlag = true;
		if (input == null)
			XFlag = false;
		//System.out.print("pFlag: " + XFlag);
		if(XFlag == true)
			return true;
		else
			return false;
	}
	public static Boolean Pparse (LinkedList<Character> input) {
		boolean Flag = false;
		boolean UFlag = false;
		boolean AFlag = false;
		boolean PFlag = false;
		char plusSign = '+';
		char minusSign = '-';
		char exclamation = '!';
		//System.out.println("Starting " + "Pparse:");
		//printList(input);
		boolean test;
		if (input != null)
			test = true;
		else
			test = false;
		//System.out.println("input is not null: " + test);
		if(input.get(0) != null){
			if(input.peekFirst().equals(plusSign) | input.peekFirst().equals(minusSign) | input.peekFirst().equals(exclamation)){
				PFlag = true;
				UFlag = Uparse(input);
			}
			else if(input.peekFirst().equals('I') | input.peekFirst().equals('L')  && input.size() == 1){
				PFlag = true;
				UFlag = true;
				AFlag = true;
			}
			else if (input.peekFirst().equals('('))
			{
				for(int j = 0; j < input.size(); j++){
					if(input.get(j).equals('=')){
						PFlag = true;
						AFlag = Aparse(input);
						break;
					}
				}
			}
			else
				PFlag = false;
		}
		
		if (input == null)
			PFlag = false;
		if (UFlag == true)
			AFlag = true;
		if (AFlag == true)
			UFlag = true;
		//System.out.print("pFlag: " + PFlag + "AFlag: " + AFlag + "UFlag: " + UFlag);
		if (AFlag == true & UFlag == true & PFlag == true)
			return true;
		else
			return false;
	}
	public static Boolean Uparse (LinkedList<Character> input) {
		char plusSign = '+';
		char minusSign = '-';
		char exclamation = '!';
		boolean flag = false;
		if(input.size() == 2){
			if(input.peekFirst().equals(plusSign) | input.peekFirst().equals(minusSign) | input.peekFirst().equals(exclamation)){
				if(input.get(1).equals('I') | input.get(1).equals('L'))
					flag = true;
				else
					flag = false;
			}
			else 
				flag = false;
		}
		else
			flag = false;
				
		//System.out.println("pFlag: " + flag);
		return flag;
	}
	public static LinkedList<Character> Iparse (LinkedList<Character> input) {
		for(int i = 0; i < input.size(); i++)
		{
			if(input.get(i).equals('C'))
			{
				input.set(i,'I');
			}
			else if(input.get(i).equals('I'))
			{
				if(input.get(i+1).equals('C')){
					input.set(i+1, 'I');
				}
				else if (input.get(i+1).equals('I')){
					input.remove(i);
				}
			}
			else{
				continue;
				}
		}
		//System.out.println("Starting " + "Iparse:");
		//printList(input);
		return input;
	
	}
	public static LinkedList<Character> Lparse (LinkedList<Character> input) {
		for(int i = 0; i < input.size()-1; i++)
		{
			if(input.get(i).equals('D'))
			{
				if(input.get(i+1).equals('D'))
				{
					input.set(i,'L');
					input.remove(i+1);
				}
				else if(input.get(i+1).equals('L'))
				{
					input.remove(i);
				}
				else
				{
					input.set(i,'L');
				}
			}
			else if(input.get(i).equals('L'))
			{
				if(input.get(i+1).equals('D')){
					input.remove(i+1);
				}
				else if (input.get(i+1).equals('L')){
					input.remove(i+1);
				}
			}
			else{
				continue;
				}
		}
		//System.out.println("Starting " + "Lparse:");
		//printList(input);
		return input;
	}
	public static LinkedList<Character> CDparse (LinkedList<Character> input) {
		for(int i = 0; i < input.size(); i++){
			if(input.get(i).equals('0')|input.get(i).equals('1')|input.get(i).equals('2')|input.get(i).equals('3')|input.get(i).equals('4')|input.get(i).equals('5')|input.get(i).equals('6')|input.get(i).equals('7')|input.get(i).equals('8')|input.get(i).equals('9'))
				input.set(i, 'D');
			else if(input.get(i).equals('a')|input.get(i).equals('b')|input.get(i).equals('c')|input.get(i).equals('d')|input.get(i).equals('e')|input.get(i).equals('f')|input.get(i).equals('g')|input.get(i).equals('h')|input.get(i).equals('i')|input.get(i).equals('j')|input.get(i).equals('k')|input.get(i).equals('l')|input.get(i).equals('m')|input.get(i).equals('n')|input.get(i).equals('o')|input.get(i).equals('p')|input.get(i).equals('q')
			|input.get(i).equals('r')|input.get(i).equals('s')|input.get(i).equals('t')|input.get(i).equals('u')|input.get(i).equals('v')|input.get(i).equals('w')|input.get(i).equals('x')|input.get(i).equals('y')|input.get(i).equals('z'))
				input.set(i, 'C');
			else
				continue;
		}
		//System.out.println("Starting " + "CDparse:");
		//printList(input);
		return input;
	
	}
	public static ArrayList<String> FileR() throws FileNotFoundException {
		ArrayList<String> list = new ArrayList<String>();
		FileReader fileReader = new FileReader(new File("input.txt"));
 		BufferedReader br = new BufferedReader(fileReader);
 		String line = null;
 		// if no more lines the readLine() returns null
 		try{
 		while ((line = br.readLine()) != null) {
      		list.add(line);
 		}
 		} catch (IOException e) {
 			System.out.println(e.getMessage());
 		}
		return list;
	}
	public static String RemoveWhite(String s){
		String s2 = s.replaceAll("\\s+","");
		return s2;
	}
	public static LinkedList<Character> SplitBefore(int flag, LinkedList<Character> input){
		LinkedList<Character> before = new LinkedList<Character>();
		for(int i = 0; i < flag; i++)
		{
			before.add(i, input.get(i));
		}
		//System.out.println("SplitBefore:");
		//printList(before);
		return before;
	}
	public static LinkedList<Character> SplitAfter(int flag, LinkedList<Character> input){
		LinkedList<Character> after = new LinkedList<Character>();
		//System.out.println("Start Split After");
		//System.out.println("input size: " + input.size());
		//System.out.println("flag size: " + flag);
		flag = flag + 1;
		//System.out.println("flag size: " + flag);
		for(int i = flag; i < input.size(); i++)
		{
			
			after.addLast(input.get(i));
			//printList(after);
		}
		//System.out.println("SplitAfter:");
		//printList(after);
		return after;
	}
	public static String ToString(LinkedList<Character> input){
		Character [] x = input.toArray(new Character [0]);
		char[] a1 = new char[x.length];
    	for(int i=0; i<x.length; i++) {
        	a1[i] = x[i].charValue();
    	}
    	String text = String.valueOf(a1);
		//System.out.println("to String: " + text);
		return text;
	}
	public static void printList(LinkedList<Character> input){
		Character [] x = input.toArray(new Character [0]);
		char[] a1 = new char[x.length];
    	for(int i=0; i<x.length; i++) {
        	a1[i] = x[i].charValue();
    	}
    	String text = String.valueOf(a1);
    	System.out.println(text);
	}

}