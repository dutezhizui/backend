package com.darkcom.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupQuotaRequest {
    private String groupId;
    private Integer quotaBalance;
    private Integer quotaLimit;

}
