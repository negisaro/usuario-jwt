package com.nelson.usario.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int numElementosPorPagina;
	
	private int paginaActual;
	private List<ItemPage> paginas;
	
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		
		numElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;
		this.paginas = new ArrayList<ItemPage>();
		
		int desde, hasta;
		
		if (totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		}
		else {
			if (paginaActual <= numElementosPorPagina / 2) {
				desde = 1;
				hasta = numElementosPorPagina;
			}
			else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			}
			else {
				desde = paginaActual - numElementosPorPagina / 2;
				hasta = numElementosPorPagina;
			}
		}
		
		for (int i = 0; i < hasta; i++) {
			paginas.add(new ItemPage(desde + i, paginaActual == desde + i));
		}
	}


	public String getUrl() {
		return url;
	}

	public Page<T> getPage() {
		return page;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getNumElementosPorPagina() {
		return numElementosPorPagina;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<ItemPage> getPaginas() {
		return paginas;
	}

	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHasPrevious() {
		return page.hasPrevious();
	}
}
