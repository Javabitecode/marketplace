package org.zuzex.service;


import org.zuzex.model.Check;

import java.util.List;

public interface CheckService {

    Check createCheck(Check check);

    Check createCheckByListProduct(List<Long> productIdList);
}
