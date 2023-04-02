package com.lcj.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

public interface LeaveBaseIVO {

	@Data
	@Accessors(chain = true)
	@ApiModel("出行记录查询ivo")
	class LeaveQueryIVO{

		/**
		 * 姓名
		 */
		private String name;
		/**
		 * 出入类型（1：出校 0：入校）
		 */
		private Integer type;
		/**
		 * 请假开始时间
		 */
		private String start;
		/**
		 * 请假结束时间
		 */
		private String end;
	}

}
