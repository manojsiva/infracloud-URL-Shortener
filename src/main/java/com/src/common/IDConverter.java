package com.src.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IDConverter {

	public static final IDConverter INSTANCE = new IDConverter();

	private IDConverter() {
		initializeCharToIndexTable();
		initializeIndextoCharTable();
	}

	private static HashMap<Character, Integer> charToIndexTable;
	private static List<Character> indexToCharTable;

	private void initializeCharToIndexTable() {
		charToIndexTable = new HashMap<>();

		for (int i = 0; i < 26; i++) {
			char c = 'a';
			c += i;
			charToIndexTable.put(c, i);
		}
		for (int i = 26; i < 52; i++) {
			char c = 'A';
			c += (i - 26);
			charToIndexTable.put(c, i);
		}
		for (int i = 52; i < 62; i++) {
			char c = '0';
			c += (i - 52);
			charToIndexTable.put(c, i);
		}
	}

	private void initializeIndextoCharTable() {
		indexToCharTable = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			char c = 'a';
			c += i;
			indexToCharTable.add(c);
		}
		for (int i = 26; i < 52; i++) {
			char c = 'A';
			c += (i - 26);
			indexToCharTable.add(c);
		}
		for (int i = 52; i < 62; i++) {
			char c = '0';
			c += (i - 52);
			indexToCharTable.add(c);
		}

	}
	public static String createUniqueId(Long id) {
		List<Integer>uniqueId = convertBase10toBase62ID(id);
		
		StringBuilder uniqueURLId = new StringBuilder();
		for(int each: uniqueId) {
			uniqueURLId.append(indexToCharTable.get(each));
		}
		return uniqueURLId.toString();
	}

	private static List<Integer> convertBase10toBase62ID(Long id) {
		LinkedList<Integer> digits = new LinkedList<>();
		while (id > 0) {
			int remainder = (int) (id % 62);
			digits.addFirst(remainder);
			id /= 62;
		}
		return digits;
	}
	
}
