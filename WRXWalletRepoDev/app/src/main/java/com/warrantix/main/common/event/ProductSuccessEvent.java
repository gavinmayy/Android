package com.warrantix.main.common.event;

import com.warrantix.main.common.rest.response.GetProductResponse;
import com.warrantix.main.common.rest.response.GetRelatedProductResponse;

public class ProductSuccessEvent
{
    private GetProductResponse mGetProductResponse = new GetProductResponse();

    public ProductSuccessEvent(GetProductResponse getProductResponse)
    {
        mGetProductResponse = getProductResponse;
    }

    public GetProductResponse getProductResponse()
    {
        return mGetProductResponse;
    }

}