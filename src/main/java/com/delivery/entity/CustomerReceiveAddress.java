package com.delivery.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * generated by Generate POJOs.groovy 
 * @Date 2020-11-07 14:40
 *
 * @author Yvan
 */

@Data
@Entity
@Table ( name ="customer_receive_address" )
public class CustomerReceiveAddress implements Serializable {

	private static final long serialVersionUID = 7859070302391544407L;

	/**
	 * 自增id

	 */
 	@Column(name = "id" )
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 关联的用户id
	 */
 	@Column(name = "customer_id" )
	private Integer customerId;

	/**
	 * 电话
	 */
 	@Column(name = "phone" )
	private String phone;

	/**
	 * 收货人姓名

	 */
 	@Column(name = "receive_name" )
	private String receiveName;

	/**
	 * 省id
	 */
 	@Column(name = "receive_province_id" )
	private String receiveProvinceId;

	/**
	 * 市id
	 */
 	@Column(name = "receive_city_id" )
	private String receiveCityId;

	/**
	 * 区id
	 */
 	@Column(name = "receive_area_id" )
	private String receiveAreaId;

	/**
	 * 收件详细地址
	 */
 	@Column(name = "receive_detailed_address" )
	private String receiveDetailedAddress;

	/**
	 * 状态

	 */
 	@Column(name = "del" )
	private Integer del;

}
