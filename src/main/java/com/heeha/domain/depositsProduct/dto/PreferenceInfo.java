package com.heeha.domain.depositsProduct.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@ToString
public class PreferenceInfo implements Comparable<PreferenceInfo> {
    private String productName;
    private Integer viewCount;

    @Override
    public int compareTo(@NotNull PreferenceInfo o) {
        return Integer.compare(o.viewCount, this.viewCount);
    }
}
