package br.com.pipa.web.open.usuario;

import br.com.pipa.common.mapper.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "Post score.")
public class PostScoreDto implements BaseDTO {
    private Long userId;
    private Long points;

}
