package com.allen.demo.model;

/**
 * DO类，对应数据库表
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
public class DemoDO implements java.io.Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 4404701789369107774L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
