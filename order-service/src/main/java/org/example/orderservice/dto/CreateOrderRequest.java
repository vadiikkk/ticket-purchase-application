package org.example.orderservice.dto;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private Integer fromStationId;
    private Integer toStationId;
}
