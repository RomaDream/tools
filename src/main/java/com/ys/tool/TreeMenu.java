package com.ys.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TreeMenu {
	public static List<Menu> init() {
		List<Menu> list = new ArrayList<Menu>();
		list.add(new Menu("001", "1", "", "/home/url1", "icon-1", 1));
		list.add(new Menu("002", "2", "", "/home/url2", "icon-2", 2));
		list.add(new Menu("003", "3", "", "/home/url3", "icon-3", 3));
		list.add(new Menu("004", "1-1", "001", "/home/url1-1", "icon-1-1", 4));
		list.add(new Menu("005", "1-2", "001", "/home/url1-2", "icon-1-2", 5));
		list.add(new Menu("006", "2-1", "002", "/home/url2-1", "icon-2-1", 6));
		list.add(new Menu("007", "3-1", "003", "/home/url3-1", "icon-3-1", 7));
		list.add(new Menu("008", "2-1-1", "006", "/home/url2-1-1", "icon-2-1-1", 8));
		System.out.println("原始数据-------");
		list.forEach(item -> System.out.println(item.toString()));
		return list;
	}

	public static List<Menu> findTree() { 
		List<Menu> menus = init();

		List<Menu> rootMenus = menus.stream().filter(item -> "".equals(item.getParentId()))
				.collect(Collectors.toList());

		Collections.sort(rootMenus);

		for (Menu menu : rootMenus) {
			List<Menu> children = getChilder(menu.getId(), menus);
			menu.setChildren(children);
		}

		System.out.println("递归后的数据-------");
		System.out.println(JSON.toJSONStringWithDateFormat(rootMenus, "yyyy-MM-dd HH:mm:ss",
				SerializerFeature.DisableCircularReferenceDetect));
		return menus;
	}

	/**
	 * 递归
	 * core-code
	 * @param id
	 * @param menus
	 * @return
	 */
	private static List<Menu> getChilder(String id, List<Menu> menus) {
		// 子菜单
		List<Menu> childList = new ArrayList<Menu>();
		for (Menu nav : menus) {
			// 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
			// 相等说明：为该根节点的子节点。
			if (nav.getParentId().equals(id)) {
				childList.add(nav);
			}
		}
		// 递归
		for (Menu nav : childList) {
			nav.setChildren(getChilder(nav.getId(), menus));
		}
		Collections.sort(childList);// 排序
		// 如果节点下没有子节点，返回一个空List（递归退出）
		if (childList.size() == 0) {
			return new ArrayList<Menu>();
		}
		return childList;
	}
}

class Menu implements Comparable<Menu> {
	private String id;

	private String name;

	private String parentId;//根节点上级id定义为""

	private String url;

	private String icon;

	private int order;

	private java.util.List<Menu> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public java.util.List<Menu> getChildren() {
		return children;
	}

	public void setChildren(java.util.List<Menu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", parentId=" + parentId + ", url=" + url + ", icon=" + icon
				+ ", order=" + order + ", children=" + children + "]";
	}

	public Menu(String id, String name, String parentId, String url, String icon, int order) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.url = url;
		this.icon = icon;
		this.order = order;
	}

	public Menu(String id, String name, String parentId, String url, String icon, int order, List<Menu> children) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.url = url;
		this.icon = icon;
		this.order = order;
		this.children = children;
	}

	public Menu() {
		super();
	}

	public int compareTo(Menu o) {
		return this.order - o.getOrder();// 升序
	}

}
