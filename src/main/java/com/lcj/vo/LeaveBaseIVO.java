package com.lcj.vo;

import com.lcj.common.enums.OutTimeType;
import io.swagger.annotations.ApiModel;
import java.util.Date;
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
		private String startTime;
		/**
		 * 请假结束时间
		 */
		private String endTime;

		/**
		 * 请假状态枚举类型
		 */
		private OutTimeType outTimeType;
	}

}
