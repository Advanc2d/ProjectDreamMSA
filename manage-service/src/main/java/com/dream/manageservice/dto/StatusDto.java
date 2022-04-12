package com.dream.manageservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
   private int orderNo;   // ֹ   ȣ
   private int proNo;      //  ǰ  ȣ
   private String userId;   //       ̵ 
   private double orderPrice;   // ֹ 
   private String purpose;
   private double amount;
   private int period;
   private int status;
   private Date orderDate;   // ֹ   ¥
   private Date endDate;   //      
   private String proName;
   private String need;
//   private String status2;
}