package org.example.common;

import org.example.constant.Constant;
import org.example.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author AI
 */
@Getter
@Setter
@ToString
@ApiModel(description = "返回信息")
@NoArgsConstructor
public class R<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "状态码", required = true)
	private Integer code;
	@ApiModelProperty(value = "承载数据")
	private T data;
	@ApiModelProperty(value = "返回消息", required = true)
	private String msg;

	private R(ErrorCode resultCode) {
		this(resultCode, null, resultCode.getErrorMessage());
	}

	private R(ErrorCode resultCode, String msg) {
		this(resultCode, null, msg);
	}

	private R(ErrorCode resultCode, T data) {
		this(resultCode, data, resultCode.getErrorMessage());
	}

	private R(ErrorCode resultCode, T data, String msg) {
		this(resultCode.getErrorCode(), data, msg);
	}

	private R(Integer code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(T data) {
		return data(data, Constant.DEFAULT_SUCCESS_MESSAGE);
	}

	/**
	 * 返回R
	 *
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(T data, String msg) {
		return data(0, data, msg);
	}

	/**
	 * 返回R
	 *
	 * @param code 状态码
	 * @param data 数据
	 * @param msg  消息
	 * @param <T>  T 泛型标记
	 * @return R
	 */
	public static <T> R<T> data(Integer code, T data, String msg) {
		return new R<T>(code, data, data == null ? Constant.DEFAULT_NULL_MESSAGE : msg);
	}

}
