package com.dream.mainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainDto {
   private int proNo;
   private String proName;
   private double proLimit;
   private String description;
   private int term;
}