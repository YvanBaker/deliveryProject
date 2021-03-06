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
@Table ( name ="customer_address" )
public class CustomerAddress implements Serializable {

	private static final long serialVersionUID = 2303078248358263963L;

 	@Column(name = "id" )
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 省id
	 */
 	@Column(name = "province_id" )
	private String provinceId;

	/**
	 * 市id
	 */
 	@Column(name = "city_id" )
	private String cityId;

	/**
	 * 区id
	 */
 	@Column(name = "area_id" )
	private String areaId;

	/**
	 * 用户详细地址
	 */
 	@Column(name = "address_detail" )
	private String addressDetail;

	/**
	 * 用户id
	 */
 	@Column(name = "user_id" )
	private Integer userId;

	/**
	 * 0 为删除 1 删除
	 */
 	@Column(name = "del" )
	private Integer del;

 	@Column(name = "name" )
	private String name;

	/**
	 * 电话
	 */
 	@Column(name = "phone" )
	private String phone;

}
