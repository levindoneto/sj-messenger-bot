package we.poc.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Delivery {

    private String[] mids;
    private Long watermark;
    private Integer seq;

    public String[] getMids() {
        return mids;
    }

    public Delivery setMids(String[] mids) {
        this.mids = mids;
        return this;
    }

    public Long getWatermark() {
        return watermark;
    }

    public Delivery setWatermark(Long watermark) {
        this.watermark = watermark;
        return this;
    }

    public Integer getSeq() {
        return seq;
    }

    public Delivery setSeq(Integer seq) {
        this.seq = seq;
        return this;
    }
}
