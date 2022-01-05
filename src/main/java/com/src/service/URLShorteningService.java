package com.src.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.common.IDConverter;
import com.src.repository.URLRepository;

@Service
public class URLShorteningService {

	private static final Logger LOGGER = LogManager.getLogger(URLShorteningService.class);

	private final URLRepository urlRepo;

	@Autowired
	public URLShorteningService(URLRepository urlRepo) {
		this.urlRepo = urlRepo;
	}

	public String shortenURL(String localURL, String longURL) {
		Long id = urlRepo.incrementId();
		String uniqueId = IDConverter.INSTANCE.createUniqueId(id);
		urlRepo.saveURL("URL: " + id, longURL);
		String baseString = formatLocalURLFromShorter(localURL);
		String shortenedURL = baseString + uniqueId;
		return longURL;

	}

	public String getURLFromId(String uniqueId) throws Exception {
		Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueId);
		String longURL = urlRepo.getURL(dictionaryKey);
		return longURL;
	}

	private String formatLocalURLFromShorter(String localURL) {
		String[] addressComponent = localURL.split("/");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < addressComponent.length - 1; i++) {
			str.append(addressComponent[i]);
		}
		str.append("/");
		return str.toString();
	}

}
