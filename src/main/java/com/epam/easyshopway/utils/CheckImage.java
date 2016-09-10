package com.epam.easyshopway.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CheckImage {

	public static boolean checkSignature(InputStream in, String type) throws IOException{
		boolean result = false;
		byte[] bytes = new byte[4];
		
		byte[] jpeg = {0xF, 0xF, 0xD, 0x8};
		byte[] bmp = {0x4, 0x2,  0x4, 0xD};
		byte[] gif = {0x4, 0x7, 0x4, 0x9};
		byte[] png = {0x8, 0x9,  0x5, 0x0};
		in.read(bytes, 0, bytes.length);
		
		switch(type){
			case "jpeg":
			case "jpg":{
				result = Arrays.equals(jpeg, bytes);
			}
				break;
			case "bmp":{
				result = Arrays.equals(bmp, bytes);
			}
				break;
			case "gif":{
				result = Arrays.equals(gif, bytes);
			}
				break;
			case "png":{
				result = Arrays.equals(png, bytes);
			}
				break;
		}
		return result;
	}
}

