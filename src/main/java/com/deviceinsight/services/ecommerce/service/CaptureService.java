package com.deviceinsight.services.ecommerce.service;

import com.deviceinsight.services.ecommerce.controller.CaptureBodyDto;
import com.deviceinsight.services.ecommerce.dto.cart.CartDto;

public interface CaptureService {



    CaptureResultDto complete(String sessionId, CaptureBodyDto captureBodyDto);
}
