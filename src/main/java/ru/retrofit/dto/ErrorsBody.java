package ru.retrofit.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsBody {
    private String timestamp;
    private Integer error;
    private Integer status;
    private String message;
    private String path;
}
