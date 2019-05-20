package br.com.altran.opendata.api;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author eudes.justino
 *
 * @param <T>
 */
public class RestPageImpl<T> implements Page<T> {

	private PageImpl<T> pageDelegate;
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public RestPageImpl(List<T> content, Pageable pageable) {
		int start = (int)pageable.getOffset()>content.size()?content.size():(int)pageable.getOffset();
	    int end = ((int)start + pageable.getPageSize()) > content.size() ? content.size() : ((int)start + pageable.getPageSize());
		this.pageDelegate = new PageImpl<T>(content.subList(start, end), pageable, content.size());
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<T> getContent() {
		return pageDelegate.getContent();
	}

	@Override
	public int getNumber() {
		return pageDelegate.getNumber();
	}

	@Override
	public int getNumberOfElements() {
		return pageDelegate.getNumberOfElements();
	}

	@Override
	public int getSize() {
		return pageDelegate.getSize();
	}

	@Override
	public Sort getSort() {
		return pageDelegate.getSort();
	}

	@Override
	public boolean hasContent() {
		return pageDelegate.getContent() != null;
	}

	@Override
	public boolean hasNext() {
		return pageDelegate.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return pageDelegate.hasPrevious();
	}

	@Override
	public boolean isFirst() {
		return pageDelegate.isFirst();
	}

	@Override
	public boolean isLast() {
		return pageDelegate.isLast();
	}

	@Override
	public Pageable nextPageable() {
		return pageDelegate.nextPageable();
	}

	@Override
	public Pageable previousPageable() {
		return pageDelegate.previousOrFirstPageable();
	}

	@Override
	public Iterator<T> iterator() {
		return pageDelegate.iterator();
	}

	@Override
	public long getTotalElements() {
		return pageDelegate.getTotalElements();
	}

	@Override
	public int getTotalPages() {
		return pageDelegate.getTotalPages();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Page<T> map(Function converter) {
		return pageDelegate.map(converter);
	}

}
