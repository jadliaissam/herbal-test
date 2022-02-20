package com.herbal.ws.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 6540152202691607844L;

    private long id;

    private String idCategoryClient;

    private String nomCategory;
}
