package xyz.catuns.dscms.orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import xyz.catuns.dscms.orderservice.dto.requests.OrderLineRequest;
import xyz.catuns.dscms.orderservice.entity.OrderLine;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
    componentModel = SPRING,
    injectionStrategy = CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLineMapper {

    OrderLine toEntity(OrderLineRequest request);

    List<OrderLine> toEntityList(List<OrderLineRequest> lines);
}
