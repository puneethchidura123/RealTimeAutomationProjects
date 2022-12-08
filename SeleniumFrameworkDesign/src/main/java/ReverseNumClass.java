import java.util.stream.IntStream;

import org.apache.commons.compress.harmony.pack200.NewAttribute;

public class ReverseNumClass {

	
	
	public long reverseNumber(long input)
	{
		long output = 0,reminder = 0;
		while(input/10 !=0)
		{
			reminder = input%10;
			input = input/10;
			output = output*10+reminder;
		}
		output = output*10+input;
		return output;
	}
	
	public long reverseNumberWithStrings(long input)
	{
		return Long.parseLong(new StringBuffer(Long.toString(input)).reverse().toString());	 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//System.out.println(new ReverseNumClass().reverseNumberWithStrings(1000002));

String s = "abc";
System.out.println(s.chars());
IntStream intstream =       s.chars().map(x->(char)x);
intstream.forEach(x->System.out.println(x));
	}

}
