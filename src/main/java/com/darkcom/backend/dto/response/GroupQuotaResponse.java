package com.darkcom.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupQuotaResponse {
    private String groupId;
    private Integer quotaBalance;
    private Integer quotaLimit;

}
