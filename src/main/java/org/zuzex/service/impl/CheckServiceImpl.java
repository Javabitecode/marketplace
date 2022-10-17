package org.zuzex.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.zuzex.model.Check;
import org.zuzex.model.Product;
import org.zuzex.model.ProductImprint;
import org.zuzex.repository.CheckRepository;
import org.zuzex.service.CheckService;
import org.zuzex.util.mapper.ProductImprintMapper;
import org.zuzex.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.zuzex.constant.Constants.SHOP_NAME;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;
    private final ProductService productService;
    private final ProductImprintMapper imprintMapper;

    @Override
    public Check createCheck(Check check) {
        checkRepository.persist(check);
        log.info("IN createCheckByListProduct - check: {} successfully created check", check);
        return check;
    }

    @Transactional
    @Override
    public Check createCheckByListProduct(List<Long> productIdList) {
        List<ProductImprint> products = new ArrayList<>();
        for (var productId : productIdList) {
            Product product = productService.sellProduct(productId);
            products.add(imprintMapper.toProductImprint(product));
        }
        Check check = Check.builder()
                .productImprints(products)
                .name(UUID.randomUUID().toString())
                .shopName(SHOP_NAME)
                .build();
        checkRepository.persist(check);
        log.info("IN createCheckByListProduct - check: {} successfully created check", check);
        return check;
    }
}
