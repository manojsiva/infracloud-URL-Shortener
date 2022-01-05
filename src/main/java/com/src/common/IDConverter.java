package com.src.common;

import java.util.HashMap;
import java.util.List;

public class IDConverter {

	public static final IDConverter INSTANCE = new IDConverter();
	
	private IDConverter() {
		initializeCharToIndexTable();
		initializeIndextoCharTable();
	}
	
	private static HashMap<Character, Integer> charToIndexTable;
	private static List<Character> indexToCharTable;
	
	public void initializeCharToIndexTable() {
		charToIndexTable= new HashMap<>();
		
		for(int i=0;i<26;i++) {
			char c='a';
			c+=i;
			charToIndexTable.put(c,i);
		}
		for(int i=26;i<52;i++) {
			char c='A';
			c+=(i-26);
			charToIndexTable.put(c, i);
		}
		for(int i=52;i<62;i++) {
			char c='0';
			c+=(i-52);
			charToIndexTable.put(c, i);
		}
	}
	
	 
}
