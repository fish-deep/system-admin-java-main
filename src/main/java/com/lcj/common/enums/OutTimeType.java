package com.lcj.common.enums;

/**
 * 判断请假类型枚举类
 * @author admin
 */

public enum OutTimeType {
	//   "判断请假状态 1：请假未开始  2：请假中  3：未归  4：完成请假")
		WAIT_LEAVE(1,"请假未开始"),

		ON_LEAVE(2,"请假中"),

		OUT_LEAVE(3,"超时未归"),

		FINISH_LEAVE(4,"完成请假");



		private int code;
		private String desc;

	OutTimeType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
