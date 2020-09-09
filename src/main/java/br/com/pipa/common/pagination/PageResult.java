package br.com.pipa.common.pagination;

import br.com.pipa.common.mapper.BaseDTO;
import lombok.Data;

import java.util.List;


@Data
public class PageResult<DTO extends BaseDTO> {

    private long totalElements;
    private int totalPages;
    private int size;
    private List<DTO> content;
}
