package getbang.config;

public enum RoomStatus {
    REQ("REQ", "예약 요청"), REJ("REJ", "예약거절"), APR("APR", "예약승인"), CAN("CAN", "예약취소");

    private final String code;
    private final String desc;

    private RoomStatus(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
