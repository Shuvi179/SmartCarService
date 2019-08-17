package ua.ita.smartcarservice.dto.sales;

/**
 * Created by 1 on 03.03.2019.
 */
public class ApplyToDealerDto {

    public ApplyToDealerDto() {
    }

    String dealerEdr;
    Long stoId;

    public ApplyToDealerDto(String dealerEdr, Long stoId) {
        this.dealerEdr = dealerEdr;
        this.stoId = stoId;
    }

    public String getDealerEdr() {
        return dealerEdr;
    }

    public void setDealerEdr(String dealerEdr) {
        this.dealerEdr = dealerEdr;
    }

    public Long getStoId() {
        return stoId;
    }

    public void setStoId(Long stoId) {
        this.stoId = stoId;
    }

    @Override
    public String toString() {
        return "ApplyToDealerDto{" +
                "dealerEdr='" + dealerEdr + '\'' +
                ", stoId=" + stoId +
                '}';
    }
}
