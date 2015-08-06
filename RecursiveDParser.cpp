#include<stdio.h>
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

void Parse (std::vector<char> input){
	string x = ToString(input);
	printf("String read from file: %s", x);
	std::vector<char> temp = CDparse(input);
	temp = Lparse(temp);
	temp = Iparse(temp);
	bool flag = Aparse(temp);
	if(flag == true)
		printf("The string  %s  is in the language", x);
	else
		printf("The sentence %s is not in the language", x);
}

bool Aparse (std::vector<char> input){
	bool TFlag = false;  //truth flag, found = sign
	bool Eflag = false; //Expression Parse Flag
	bool pFlag = false; //return flag
	int i;
	std::vector<char> after;
	for(i = 0; i < input.size(); i++){
		if(input.at(i) == '='){
			int flag = i;
			TFlag = true;
			after = SplitAfter(flag, input);
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
	if(pFlag == true)
		return true;
	else
		return false;
}

bool Eparse (std::vector<char> input){
	bool AFlag = false;
	bool TFlag = false;
	bool Eflag = false;
	bool pFlag = false;
	int i, flag;
	std::vector<char> before;
	std::vector<char> after;
	for(i = 0; i < input.size(); i++){
		if(input.at(i) == '+' || input.at(i) == '-' && input.size()%2 != 0 && i != 0){
			flag = i;
			AFlag = true;
			before = SplitBefore(flag, input);
			after = SplitAfter(flag, input);
			TFlag = Tparse(before);
			Eflag = Eparse(after);
			break;
		}
	}
	if(AFlag == false){
		TFlag = Tparse(input);
		Eflag = true;
	}
	if(TFlag && Eflag == true)
		pFlag = true;
			
	if (input == null)
		pFlag = false;
	if(pFlag == true)
		return true;
	else
		return false;
}
bool Tparse (std::vector<char> input){
	bool AFlag = false;
	bool FFlag = false;
	bool Tflag = false;
	bool pFlag = false;
	int i, flag;
	std::vector<char> before;
	std::vector<char> after;
	for(i = 0; i < input.size(); i++){
		if(input.size()%2 != 0){
			if(input.at(i) == '*' || input.at(i) == '/'){
				flag = i;
				AFlag = true;
				before = SplitBefore(flag, input);
				after = SplitAfter(flag, input);
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
	if(FFlag && Tflag == true)
		pFlag = true;
	if (input == null)
		pFlag = false;
	if(pFlag == true)
		return true;
	else
		return false;
}
bool Fparse (std::vector<char> input){
	bool AFlag = false;
	bool PFlag = false;
	bool Fflag = false;
	bool XFlag = false;
	int i, flag;
	std::vector<char> before;
	std::vector<char> after;
	for(i = 0; i < input.size(); i++){
		if(input.at(i) == '^'){
			flag = i;
			AFlag = true;
			before = SplitBefore(flag, input);
			after = SplitAfter(flag, input);
			PFlag = Pparse(before);
			Fflag = Fparse(after);
			break;
		}
	}
	if(AFlag == false){
		PFlag = Pparse(input);
		Fflag = true;	
	}
	if(PFlag && Fflag == true)
		XFlag = true;
	if (input == null)
		XFlag = false;
	if(XFlag == true)
		return true;
	else
		return false;
}
bool Pparse (std::vector<char> input){
	boolean Flag = false;
	boolean UFlag = false;
	boolean AFlag = false;
	boolean PFlag = false;
	int j, flag;
	std::vector<char> before;
	std::vector<char> after;
	if(input.at(0) != null){
		if(input.at(0) == '+' || input.at(0) == '-' || input.at(0) == '!'){
			PFlag = true;
			UFlag = Uparse(input);
		}
		else if(input.at(0) == 'I' || input.at(0) == 'L'  && input.size() == 1){
			PFlag = true;
			UFlag = true;
			AFlag = true;
		}
		else if (input.at(0) == '(')
		{
			for(j = 0; j < input.size(); j++){
				if(input.at(j) == '='){
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
	if (AFlag == true && UFlag == true && PFlag == true)
		return true;
	else
		return false;
}
bool Uparse (std::vector<char> input){
	bool flag = false;
	if(input.size() == 2){
		if(input.at(0) == '+' || input.at(0) == '-' || input.at(0) == '!' ){
			if(input.at(1). == 'I' || input.at(1) == 'L')
				flag = true;
			else
				flag = false;
		}
		else 
			flag = false;
	}
	else
		flag = false;
			
	return flag;
}
std::vector<char> Iparse (std::vector<char> input){
	for(int i = 0; i < input.size(); i++)
	{
		if(input.at(i) == 'C')
		{
			input.at(i) = 'I';
		}
		else if(input.at(i) == 'I')
		{
			if(input.at(i+1) == 'C'){
				input.at(i+1) = 'I';
			}
			else if (input.at(i+1) == 'I'){
				input.erase(input.begin()+i);
			}
		}
		else{
			continue;
		}
	}
	return input;
}
std::vector<char> Lparse (std::vector<char> input){
	for(int i = 0; i < input.size(); i++)
	{
		if(input.at(i) == 'D')
		{
			input.at(i) = 'L';
		}
		else if(input.at(i) == 'L')
		{
			if(input.at(i+1) == 'D'){
				input.at(i+1) = 'L';
			}
			else if (input.at(i+1) == 'L'){
				input.erase(input.begin()+i);
			}
		}
		else{
			continue;
		}
	}
	return input;
}
std::vector<char> CDparse (std::vector<char> input){
	for(int i = 0; i < input.size(); i++){
		if(input.at(i) == '0' ||input.at(i) == '1'||input.at(i) == '2'||input.at(i) == '3'||input.at(i) == '4'||input.at(i) == '5'||input.at(i) == '6'||input.at(i) == '7'||input.at(i) == '8'||input.at(i) == '9')
			input.at(i) = 'D';
		else if(input.at(i) == 'a'||input.at(i) == 'b'||input.at(i) == 'c'||input.at(i) == 'd'||input.at(i) == 'e'||input.at(i) == 'f'||input.at(i) == 'g'||input.at(i) == 'h'||input.at(i) == 'i'||input.at(i) == 'j'||input.at(i) == 'k'||input.at(i) == 'l'||input.at(i) == 'm'||input.at(i) == 'n'||input.at(i) == 'o'||input.at(i) == 'p'||input.at(i) == 'q'
		||input.at(i) == 'r'||input.at(i) == 's'||input.at(i) == 't'||input.at(i) == 'u'||input.at(i) == 'v'||input.at(i) == 'w'||input.at(i) == 'x'||input.at(i) == 'y'||input.at(i) == 'z')
			input.at(i) = 'C';
		else
			continue;
	}
	
	return input;
}
std::vector<string> FileIORead(){
  string line;
  std::vector<string> list;
  ifstream myfile ("input.txt");
  if (myfile.is_open())
  {
    while ( getline (myfile,line) )
    {
      list.at(i) = line
      i++;
    }
    myfile.close();
  }

  else 
  	printf("Unable to open file"); 
  
  return list;
}
string RemoveWhite (string &str){
	str.erase(std::remove(str.begin(), str.end(), ' '), str.end());
   		return str;
}
std::vector<char> splitBefore (int flag, std::vector<char> input){
	std::vector<char> before;
	int i;
	for(i = 0; i < flag; i++)
	{
		before.push_back(input.at(i));
	}
	return before;
}
std::vector<char> splitAfter (int flag, std::vector<char> input){
	std::vector<char> after;
	int i;
	flag = flag + 1;
	for(i = flag; i < input.size(); i++)
	{		
		after.push_back(input.at(i));
	}
	return after;
}
string ToString (std::vector<char> input);
	std::string str(input.begin(), input.end());
	return str;

int main(){
		//read all lines into an ArrayList
		std::vector<string> input;
		int i, j, k;
		string temp;
		int length;
		char Stream[1024];
			input = FileIORead();
		//remove whitespace from all lines
		for (i = 0; i < input.size(); i++){
			temp = input.at(i);
			input.at(i) = RemoveWhite(temp);
		}
		for(j = 0; j< input.size(); j++){
	
			strncpy(Stream, input.at(i).c_str(), sizeof(Stream));
			Stream[sizeof(Stream) - 1] = 0;
			std::vector<char> Token;
			length =sizeof(Stream)/sizeof(*Stream);
				for(k = 0; k<length;k++){
					Token.at(i) = Stream[k]ß;
				}
			Parse(Token);
		}
}