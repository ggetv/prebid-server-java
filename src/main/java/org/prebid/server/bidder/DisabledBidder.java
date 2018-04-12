package org.prebid.server.bidder;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iab.openrtb.request.BidRequest;
import org.prebid.server.bidder.model.BidderBid;
import org.prebid.server.bidder.model.BidderError;
import org.prebid.server.bidder.model.HttpCall;
import org.prebid.server.bidder.model.HttpRequest;
import org.prebid.server.bidder.model.Result;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Used to indicate disabled bidder. First method call to this bidder should return empty bids and error in result.
 */
public class DisabledBidder implements Bidder<BidRequest> {

    private String errorMessage;

    public DisabledBidder(String errorMessage) {
        this.errorMessage = Objects.requireNonNull(errorMessage);
    }

    @Override
    public Result<List<HttpRequest<BidRequest>>> makeHttpRequests(BidRequest request) {
        return Result.of(Collections.emptyList(), Collections.singletonList(BidderError.create(errorMessage)));
    }

    @Override
    public Result<List<BidderBid>> makeBids(HttpCall<BidRequest> httpCall, BidRequest bidRequest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<String, String> extractTargeting(ObjectNode ext) {
        throw new UnsupportedOperationException();
    }
}