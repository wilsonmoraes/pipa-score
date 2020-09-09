package br.com.pipa.web.open.accesLog;

import br.com.pipa.common.pagination.SearchParams;
import lombok.Data;

import java.time.LocalDateTime;

@Data
class AccessLogFindPaginatedRequest extends SearchParams {

    private LocalDateTime dateTimeAudit;

    private String clientIP;

    private String methodRequest;

    private Integer statusCodeResponde;

    private String client;


    @Override
    public void setSize(int size) {
        super.setSize(size < 0 ? Integer.MAX_VALUE : size);
    }


}
