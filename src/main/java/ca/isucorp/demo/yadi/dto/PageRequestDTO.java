package ca.isucorp.demo.yadi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;


@Data
public class PageRequestDTO {
	
	private static int DEFAULT_PAGE_NUMBER = 1;
	private static int DEFAULT_PAGE_LIMIT = 10;
	private static String DEFAULT_ORDER_BY = "id";
	private static String DEFAULT_ORDER_TYPE = "DESC";
	private static String ASC = "ASC";
	private static String DESC = "DESC";
	
	@Min(value=1, message="Please input an positive number")
	private Integer pageNumber;
	
	@Min(value=1, message="Please input an positive number")
	private Integer pageLimit;
	
	private String orderBy;
	
	@Pattern(regexp="ASC|DESC", message="Please input ASC or DESC")
	private String orderType;
	
	/**
	 * Set the default page number and limit
	 */
	public PageRequestDTO() {
		this.setPageNumber(PageRequestDTO.DEFAULT_PAGE_NUMBER);
		this.setPageLimit(PageRequestDTO.DEFAULT_PAGE_LIMIT);
		this.setOrderBy(PageRequestDTO.DEFAULT_ORDER_BY);
		this.setOrderType(PageRequestDTO.DEFAULT_ORDER_TYPE);
	}
	
	public PageRequestDTO(Integer pageNumber, Integer pageLimit, String orderBy, String orderType) {
		if (pageNumber != null) {
			this.setPageNumber(pageNumber);
		} else {
			this.setPageNumber(PageRequestDTO.DEFAULT_PAGE_NUMBER);
		}
		
		if (pageLimit != null) {
			this.setPageLimit(pageLimit);
		} else {
			this.setPageLimit(PageRequestDTO.DEFAULT_PAGE_LIMIT);
		}
		
		if (orderBy != null) {
			this.setOrderBy(orderBy);
		}
		
		if (orderType != null) {
			this.setOrderType(orderType);	
		}
	}
	
	/**
	 * Convert page request DTO to page request
	 * 
	 * @return PageRequest
	 */
	public Pageable toPageable() {
		if (this.getOrderBy() != null) {
			if (this.getOrderType().equals(PageRequestDTO.ASC)) {
				return PageRequest.of(this.getPageNumber() - 1, this.getPageLimit(), Sort.by(this.getOrderBy()).ascending());
			} else if (this.getOrderType().equals(PageRequestDTO.DESC)) {
				return PageRequest.of(this.getPageNumber() - 1, this.getPageLimit(), Sort.by(this.getOrderBy()).descending());
			}
		}
		return PageRequest.of(this.getPageNumber() - 1, this.getPageLimit());
	}

}
