package com.dream.orderService.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderProductVO {
	private int proNo;	//��ǰ ��ȣ
	private String proName;	//��ǰ �̸�
	private String detail;
	private String need;
	private int term;	//���� �Ⱓ
	private double proLimit;	// ���� �ѵ�
	private double rate;	// ���� �ѵ�
}
