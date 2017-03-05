package utils;

import java.util.ArrayList;

import preprocessamento.Cleaner;

public class TextUtils {
	
/*
 1 - ok - All ?! are EOS

 2 - ok - If " or ' appears before period, it is EOS
 3 - ok - If )}] before period, it is EOS

 4 - ok - If the following character is not white space, it is not EOS

 5 - ok - If the token to which the period is attached is capitalized and is < 5 characters 
and the next token begins uppercase, it is not EOS

 6 - If the token to which the period is attached has other periods, it is not EOS

 7 - If the token to which the period is attached has < 2 characters, it is not EOS

 8 - If the next token following whitespace begins with $({["' it is EOS 
Otherwise, the period is not EOS

 9 - If the token to which the period is attached begins with a lowercase letter 
and the next token following whitespace is uppercase, it is EOS



 * */	
	
	private static ArrayList<String> abrevs = new ArrayList<>();
	
	static {
		abrevs.clear();
		
		abrevs.add("Sr.");
		abrevs.add("Sra.");
		abrevs.add("Prof.");
		abrevs.add("Profa.");
		abrevs.add("profª.");
		abrevs.add("Dr.");
		abrevs.add("Dra.");
		abrevs.add("Drª.");
		abrevs.add("Rod.");
		
		
		for (int i=0; i<abrevs.size(); i++) {
			abrevs.set(i, Cleaner.clean(abrevs.get(i).toLowerCase()).trim()); // tudo minúsculo
		}
		
	}
	
	private static boolean isCapitalized(String s) {
		return (s.charAt(0) >= 'A') && (s.charAt(0) <= 'Z'); 
	} 
//	@SuppressWarnings("unused")
//	private static boolean isLowercase(String s) {
//		return (s.charAt(0) >= 'a') && (s.charAt(0) <= 'z'); 
//	} 
	
	public static String indentifyEndOfSentence(String txt, String eosMark) {

		txt = txt.replace("\n", "\n ");
		txt = txt.replace("\t", "\t ");
		txt = Cleaner.removeDoubleSpaces(txt);
		
//		[1] - ok - All ?! are EOS

//		[^ ]\?\ 
//		txt = txt.replaceAll("[^ ]\\?\\ ", "?"+eosMark);
		
		txt = txt.replace("!"  , "!"+eosMark); 
//		txt = txt.replace(": (", ": "+eosMark +"(");  // by OJF

		String[] tokens = txt.split(" ");
		
		
		for(int i=0; i<tokens.length-1; i++) {
			
			if (tokens[i].length() < 2) continue;     // deve permitrir finais de sentença que terminam em uma palavra de 1 letra seguida de '.' Por exemplo "Cálculo 3.", "Cálculo I." "Conceito B;"
			if (!( tokens[i].endsWith(".") || tokens[i].endsWith("?") || tokens[i].endsWith(";"))) continue;
			if (isAbreviation(tokens[i])) continue; 			

//			[5] If the token to which the period is attached is capitalized and is < 5 characters 
//			and the next token begins uppercase, it is not EOS
//			Contra exemplo : "Bla bla bla bla bla Ana. Bla bla bla bla bla."
/*
			if (isUppercase(tokens[i]) && (tokens[i].length() < 5) && isUppercase(tokens[i+1])) {
				System.out.println("5");
				continue;  
			}
*/
			
//			[6] If the token to which the period is attached has other periods, it is not EOS
//			Contra exemplo: "Bla bla bla bla bla no endereço ufscar.com.br. Bla bla bla bla bla."
/*
			if (tokens[i].substring(0, tokens[i].length()-1).contains(".")) {
				System.out.println(6);
				continue;
			}
 */
			
//			[7] If the token to which the period is attached has < 2 characters, it is not EOS
//			Contra exemplo : "Bla bla bla bla bla bloco A. Bla bla bla bla bla."
/*
			if (tokens[i].length()<3) { // 3 pq conto o '.' "s.".len = 2
//				System.out.println(7);
				continue;
			}
 */
			
//			[8] If the next token following whitespace begins with $({["' it is EOS 
			if ( "$({[\"'".contains(""+tokens[i+1].charAt(0))) {
				tokens[i] = tokens[i] + eosMark;
				continue;
			}
			
			
//			[9] If the token to which the period is attached begins with a lowercase letter 
//			and the next token following whitespace is uppercase, it is EOS
			if (/*isLowercase(tokens[i]) &&*/ isCapitalized(tokens[i+1])) {
				tokens[i] = tokens[i] + eosMark;
				continue;
			}
			
//			[2] - ok - If " or ' appears before period, it is EOS
//			[3] - ok - If )}] before period, it is EOS
			if ("\"')}]".contains(""+tokens[i].charAt(tokens[i].length()-2))) {
				tokens[i] = tokens[i] + eosMark;
				continue;
			}
			
			
			
			if(tokens[i].endsWith(";") && (isCapitalized(tokens[i+1]) || "({[".contains(""+tokens[i+1].charAt(0)))) {
				tokens[i] = tokens[i] + eosMark;
				continue;
			}
			
			if(tokens[i].endsWith(".") && " \n\t".contains(""+tokens[i+1].charAt(0))) {
				tokens[i] = tokens[i] + eosMark;
				continue;
			}
			

			
		}
		
		String result = "";

		for(String s : tokens) {
			result = result.concat(s).concat(" ");
		}
		
		return result;
	}

	
//	public static ArrayList<String> splitSentences(String txt) {
////		["')}]\.|[\!\?]
//		String regex = "[\"\')}]\\.|[\\!\\?]";
//		
//		String[] ss = txt.split(regex);
//		
//		ArrayList<String> result = new ArrayList<>();
//		
//		for(String s : ss) result.add(s);
//		
//		return result;
//	}

	public static ArrayList<String> splitSentences(String txt, String eosMark) {
		
		eosMark = eosMark.replace("|", "\\|");
		
		String[] sentences = txt.split(eosMark);
		
		ArrayList<String> result = new ArrayList<>();
		
		for(String s : sentences)
			result.add(s);
		
		return result;
	}
	
	public static boolean isAbreviation(String token) {
		return abrevs.contains(Cleaner.clean(token.toLowerCase()).trim()) || (token.length() <3 && isCapitalized(token));
	}
	
	
	public static String removePageNumbers(String text) {
		String lines[] = text.split("\n");
		
		int page = 1;
		
		for(int i=0; i<lines.length; i++) {
			String p = String.format("%d", page);
			
			if (lines[i].trim().endsWith(p)) {
				lines[i] = lines[i].trim().substring(0, lines[i].trim().length()-p.length());
				page++;
			}
		}

		
		String result = "";
		for(String l : lines) {
			result += l+"\n";
		}
		
		return result;

	}
	
	
	public static String restrictChar(String text, char charToRestric, int allow) {
		String allowed = "";
		for(int i=0; i<allow; i++) {
			allowed += charToRestric;
		}
		
		String toRemove = allowed + charToRestric;
		
		while(text.contains(toRemove)) {
			text = text.replace(toRemove, allowed);
		}
		
		return text;
		
	}
	
	
}

//https://regex101.com/


