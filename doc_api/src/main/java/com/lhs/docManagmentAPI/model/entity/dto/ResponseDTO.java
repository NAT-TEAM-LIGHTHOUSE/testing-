/**
 * 
 */
package com.lhs.docManagmentAPI.model.entity.dto;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lavanya.badham
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

	private String result;
	private byte[] file;
	private BufferedImage obj;
}
