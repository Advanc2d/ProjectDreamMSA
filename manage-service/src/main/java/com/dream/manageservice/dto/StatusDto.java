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
   private Date orderDate;   // ֹ   ¥
   private Date endDate;   //      
   private int status;
   private String proName;
//   private String status2;
}