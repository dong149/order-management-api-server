package com.order.api.products;

import com.order.api.errors.NotFoundException;
import com.order.api.utils.ApiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
    @GetMapping(path = "{id}")
    public ApiUtils.ApiResult<ProductDto> findById(@PathVariable Long id) {
        return ApiUtils.success(productService.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id)));
    }

    // FIXME `요건 1` 정의에 맞게 응답 타입 수정이 필요합니다.
    @GetMapping
    public ApiUtils.ApiResult<List<ProductDto>> findAll() {
        return ApiUtils.success(productService.findAll().stream()
                .map(ProductDto::new)
                .collect(toList()));
    }

}