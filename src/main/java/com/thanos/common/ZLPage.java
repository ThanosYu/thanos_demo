package com.thanos.common;

import java.util.List;


public class ZLPage<E> {
	private List<E> list;
	private Long total;


	public ZLPage() {
		super();
	}

	public ZLPage(List<E> list, Long total) {
		super();
		this.list = list;
		this.total=total;
	}

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

	public static <E> ZLPage<E> get(List<E> list,Long total){
		return new ZLPage<E>(list,total);
	}
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
